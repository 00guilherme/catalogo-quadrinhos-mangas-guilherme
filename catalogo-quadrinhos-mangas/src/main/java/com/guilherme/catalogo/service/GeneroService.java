package com.guilherme.catalogo.service;

import com.guilherme.catalogo.model.Genero;
import com.guilherme.catalogo.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    private final GeneroRepository repository;

    public GeneroService(GeneroRepository repository) {
        this.repository = repository;
    }

    public List<Genero> listar() {
        return repository.findAll();
    }

    public Genero buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado."));
    }

    public Genero salvar(Genero genero) {
        genero.setNome(genero.getNome().trim());
        if (repository.existsByNomeIgnoreCase(genero.getNome())) {
            throw new RuntimeException("Esse gênero já existe.");
        }
        return repository.save(genero);
    }

    public Genero atualizar(Long id, Genero dados) {
        Genero genero = buscar(id);
        genero.setNome(dados.getNome().trim());
        return repository.save(genero);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Gênero não encontrado.");
        }
        repository.deleteById(id);
    }
}
