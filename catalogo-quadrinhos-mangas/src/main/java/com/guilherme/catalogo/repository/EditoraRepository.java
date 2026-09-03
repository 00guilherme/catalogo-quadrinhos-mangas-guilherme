package com.guilherme.catalogo.repository;

import com.guilherme.catalogo.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório CRUD; o Spring Data gera a implementação em tempo de execução.
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    // Procura duplicidades ignorando diferenças entre maiúsculas e minúsculas.
    boolean existsByNomeIgnoreCase(String nome);
}
