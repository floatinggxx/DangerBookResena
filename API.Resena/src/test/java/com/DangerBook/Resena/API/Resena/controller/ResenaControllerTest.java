package com.DangerBook.Resena.API.Resena.Controller;

import com.DangerBook.Resena.API.Resena.controller.ResenaController;
import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.service.ResenaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResenaController.class)
public class ResenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResenaService resenaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Resena resena;

    @BeforeEach
    void setUp() {
        resena = new Resena();
        resena.setIdResena(1);
        resena.setFPublicacion(LocalDate.now());
        resena.setComentario("Muy buen servicio");
        resena.setCalificacion(5);
    }

    @Test
    void testListarResenas() throws Exception {
        when(resenaService.findAll()).thenReturn(List.of(resena));

        mockMvc.perform(get("/api/v1/resenas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idResena").value(1))
                .andExpect(jsonPath("$[0].comentario").value("Muy buen servicio"))
                .andExpect(jsonPath("$[0].calificacion").value(5));
    }

    @Test
    void testListarResenasVacio() throws Exception {
        when(resenaService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/resenas"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(resenaService.findById(1)).thenReturn(resena);

        mockMvc.perform(get("/api/v1/resenas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idResena").value(1))
                .andExpect(jsonPath("$.comentario").value("Muy buen servicio"));
    }

    @Test
    void testBuscarPorIdNoEncontrado() throws Exception {
        when(resenaService.findById(99)).thenThrow(new RuntimeException("Reseña no encontrada"));

        mockMvc.perform(get("/api/v1/resenas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearResena() throws Exception {
        when(resenaService.save(any(Resena.class))).thenReturn(resena);

        mockMvc.perform(post("/api/v1/resenas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resena)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idResena").value(1));
    }

    @Test
    void testEliminarResena() throws Exception {
        doNothing().when(resenaService).delete(1);

        mockMvc.perform(delete("/api/v1/resenas/1"))
                .andExpect(status().isNoContent());
    }
}