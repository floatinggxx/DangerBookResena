package com.DangerBook.Resena.API.Resena.service;

import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.repository.ResenaRepository;
import com.DangerBook.Resena.API.Resena.service.ResenaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    private Resena resena;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        resena = new Resena();
        resena.setIdResena(1);
        resena.setFPublicacion(LocalDate.now());
        resena.setComentario("Excelente servicio");
        resena.setCalificacion(5);
    }

    @Test
    void testFindAll() {
        when(resenaRepository.findAll()).thenReturn(List.of(resena));

        List<Resena> lista = resenaService.findAll();

        assertEquals(1, lista.size());
        verify(resenaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(resenaRepository.findById(1)).thenReturn(Optional.of(resena));

        Resena result = resenaService.findById(1);

        assertEquals(5, result.getCalificacion());
        assertEquals("Excelente servicio", result.getComentario());
    }

    @Test
    void testFindByIdNoEncontrado() {
        when(resenaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> resenaService.findById(99));
        
        assertEquals("Reseña no encontrada", exception.getMessage());
    }

    @Test
    void testSave() {
        when(resenaRepository.save(resena)).thenReturn(resena);

        Resena guardada = resenaService.save(resena);

        assertEquals("Excelente servicio", guardada.getComentario());
        verify(resenaRepository, times(1)).save(resena);
    }

    @Test
    void testDelete() {
        when(resenaRepository.existsById(1)).thenReturn(true);

        resenaService.delete(1);

        verify(resenaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteNoExiste() {
        when(resenaRepository.existsById(99)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> resenaService.delete(99));
        
        assertEquals("No existe la reseña", exception.getMessage());
    }
}