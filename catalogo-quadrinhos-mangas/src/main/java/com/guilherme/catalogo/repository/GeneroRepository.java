package com.guilherme.catalogo.repository;

import com.guilherme.catalogo.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
