package com.DangerBook.Resena.API.Resena.service;

import com.DangerBook.Resena.API.Resena.model.Resena;
import com.DangerBook.Resena.API.Resena.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> findAll() {
        return resenaRepository.findAll();
    }

    public Resena findById(Integer id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    public Resena save(Resena resena) {
        return resenaRepository.save(resena);
    }

    public void delete(Integer id) {
        if (!resenaRepository.existsById(id)) {
            throw new RuntimeException("No existe la reseña");
        }
        resenaRepository.deleteById(id);
    }
}