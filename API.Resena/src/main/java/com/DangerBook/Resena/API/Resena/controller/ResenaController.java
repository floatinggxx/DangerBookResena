package com.DangerBook.Resena.API.Resena.controller;

import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.service.ResenaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> getAll() {
        List<Resena> lista = resenaService.findAll();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(resenaService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Resena> create(@RequestBody Resena resena) {
        return ResponseEntity.status(201).body(resenaService.save(resena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            resenaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}