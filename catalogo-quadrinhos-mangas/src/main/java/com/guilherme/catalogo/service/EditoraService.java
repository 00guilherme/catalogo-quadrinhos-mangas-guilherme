package com.guilherme.catalogo.service;

import com.guilherme.catalogo.model.Editora;
import com.guilherme.catalogo.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    private final EditoraRepository repository;

    public EditoraService(EditoraRepository repository) {
        this.repository = repository;
    }

    public List<Editora> listar() {
        return repository.findAll();
    }

    public Editora buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada."));
    }

    public Editora salvar(Editora editora) {
        editora.setNome(editora.getNome().trim());
        if (repository.existsByNomeIgnoreCase(editora.getNome())) {
            throw new RuntimeException("Essa editora já existe.");
        }
        return repository.save(editora);
    }

    public Editora atualizar(Long id, Editora dados) {
        Editora editora = buscar(id);
        editora.setNome(dados.getNome().trim());
        return repository.save(editora);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Editora não encontrada.");
        }
        repository.deleteById(id);
    }
}
