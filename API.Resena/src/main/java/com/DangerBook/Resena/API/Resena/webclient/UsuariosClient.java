package com.DangerBook.Resena.API.Resena.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class UsuariosClient {

    private final WebClient webClient;

    public UsuariosClient(@Value("${usuarios.service.url}") String usuariosServiceURL) {
        this.webClient = WebClient.builder()
                .baseUrl(usuariosServiceURL)
                .build();
    }

    public Map<String, Object> getUsuarioById(Long id) {
        try {
            return this.webClient.get()
                    .uri("/{id}", id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map> getAllUsuarios() {
        try {
            return this.webClient.get()
                    .retrieve()
                    .bodyToFlux(Map.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}