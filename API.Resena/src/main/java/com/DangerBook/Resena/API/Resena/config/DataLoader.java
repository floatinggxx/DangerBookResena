package com.DangerBook.Resena.API.Resena.config;

import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ResenaRepository resenaRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // cargar reseñas si no hay datos
        if (resenaRepository.count() == 0) {
            
            Random random = new Random();
            
            // Lista de comentarios de ejemplo
            String[] comentarios = {
                "Excelente servicio, muy profesional",
                "Muy buen corte, quedé satisfecho",
                "Buena atención, recomendado",
                "El barbero es muy hábil, volveré",
                "Corte perfecto, justo lo que pedí",
                "Ambiente agradable y limpio",
                "Rapido y eficiente, me gustó",
                "Precio justo por el servicio"
            };
            
            // Generar 10 reseñas de prueba
            for (int i = 0; i < 10; i++) {
                Resena r = new Resena();
                
                // Fecha aleatoria en los últimos 30 días
                LocalDate fechaPublicacion = LocalDate.now()
                    .minusDays(random.nextInt(30));
                r.setFPublicacion(fechaPublicacion);
                
                // Comentario aleatorio de la lista
                r.setComentario(comentarios[random.nextInt(comentarios.length)]);
                
                // Calificación entre 3 y 5 (mayormente positivas)
                r.setCalificacion(random.nextInt(3) + 3);
                
                // 90% de probabilidad de NO estar baneada
                if (random.nextInt(10) > 8) {
                    r.setFBaneo(LocalDate.now());
                    r.setMotivoBaneo("Lenguaje inapropiado");
                }
                
                resenaRepository.save(r);
            }
            
            System.out.println("Reseñas cargadas: " + resenaRepository.count());
        }

        System.out.println("=== Datos de prueba de Reseñas cargados correctamente ===");
    }
}