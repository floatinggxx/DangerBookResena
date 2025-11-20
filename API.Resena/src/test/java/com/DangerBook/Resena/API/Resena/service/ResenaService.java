package com.DangerBook.Resena.API.Resena.service;

import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.repository.ResenaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ResenaService {

    @Autowired
    private ResenaServiceTest resenaService;

    @MockBean
    private ResenaRepository resenaRepository;

    @Test
    void testFindAll() {
        Resena r = Resena.builder().id_reseña(1).comentario("Excelente").calificacion(5).build();
        when(resenaRepository.findAll()).thenReturn(List.of(r));

        var lista = resenaService.findAll();
        assertEquals(1, lista.size());
        verify(resenaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Resena r = Resena.builder().id_reseña(1).comentario("Todo bien").calificacion(4).build();
        when(resenaRepository.findById(1)).thenReturn(Optional.of(r));

        Resena result = resenaService.findById(1);
        assertEquals(4, result.getCalificacion());
    }

    @Test
    void testSave() {
        Resena r = Resena.builder().id_reseña(1).comentario("Buen corte").calificacion(5).build();
        when(resenaRepository.save(r)).thenReturn(r);

        Resena guardada = resenaService.save(r);
        assertEquals("Buen corte", guardada.getComentario());
    }
}
