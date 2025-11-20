package com.DangerBook.Resena.API.Resena.repository;

import com.DangerBook.Resena.API.Resena.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {
}