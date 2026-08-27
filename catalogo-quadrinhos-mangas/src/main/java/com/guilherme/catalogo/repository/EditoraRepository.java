package com.guilherme.catalogo.repository;

import com.guilherme.catalogo.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
