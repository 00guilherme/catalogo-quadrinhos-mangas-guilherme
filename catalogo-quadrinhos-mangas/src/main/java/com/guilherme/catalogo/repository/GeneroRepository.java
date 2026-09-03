package com.guilherme.catalogo.repository;

import com.guilherme.catalogo.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório CRUD; o Spring Data gera a implementação em tempo de execução.
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    // Procura duplicidades ignorando diferenças entre maiúsculas e minúsculas.
    boolean existsByNomeIgnoreCase(String nome);
}
