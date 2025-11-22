package com.DangerBook.Resena.API.Resena.config;

import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.repository.ResenaRepository;
import com.DangerBook.Resena.API.Resena.webclient.UsuariosClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ResenaRepository resenaRepository;
    
    @Autowired
    private UsuariosClient usuariosClient;

    private String[] comentarios = {
        "Excelente servicio, muy profesional",
        "Muy buen corte, quedé satisfecho",
        "Buena atención, recomendado",
        "El barbero es muy hábil, volveré",
        "Corte perfecto, justo lo que pedí"
    };

    @Override
    public void run(String... args) throws Exception {
        
        if (resenaRepository.count() > 0) return;
        
        Random random = new Random();
        
        List<Map> usuarios = usuariosClient.getAllUsuarios();
        
        if (usuarios != null && !usuarios.isEmpty()) {
            System.out.println("✅ Conectado con API Usuarios");
        } else {
            System.out.println("⚠️ API Usuarios no disponible");
        }

        for (int i = 0; i < 12; i++) {
            Resena r = new Resena();
            r.setFPublicacion(LocalDate.now().minusDays(random.nextInt(60)));
            r.setComentario(comentarios[random.nextInt(comentarios.length)]);
            r.setCalificacion(random.nextInt(3) + 3); // 3, 4 o 5
            
            resenaRepository.save(r);
        }
        
        System.out.println("✅ Reseñas cargadas: " + resenaRepository.count());
    }
}