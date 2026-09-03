package com.guilherme.catalogo.service;

import com.guilherme.catalogo.model.Genero;
import com.guilherme.catalogo.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Implementa as regras de negócio e coordena o acesso ao repositório de gêneros.
public class GeneroService {

    // Abstração do Spring Data usada para consultar e persistir gêneros.
    private final GeneroRepository repository;

    // Recebe o repositório criado pelo Spring.
    public GeneroService(GeneroRepository repository) {
        this.repository = repository;
    }

    public List<Genero> listar() {
        // Retorna todos os registros para preencher listas e filtros da interface.
        return repository.findAll();
    }

    public Genero buscar(Long id) {
        // Interrompe a operação com uma mensagem de negócio quando o ID não existe.
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado."));
    }

    public Genero salvar(Genero genero) {
        // Normaliza espaços antes de verificar duplicidade e salvar o nome.
        genero.setNome(genero.getNome().trim());
        // Evita gêneros duplicados sem diferenciar letras maiúsculas de minúsculas.
        if (repository.existsByNomeIgnoreCase(genero.getNome())) {
            throw new RuntimeException("Esse gênero já existe.");
        }
        return repository.save(genero);
    }

    public Genero atualizar(Long id, Genero dados) {
        // Busca a entidade gerenciada e altera somente o nome recebido.
        Genero genero = buscar(id);
        genero.setNome(dados.getNome().trim());
        return repository.save(genero);
    }

    public void excluir(Long id) {
        // Confirma a existência para devolver um erro de negócio claro.
        if (!repository.existsById(id)) {
            throw new RuntimeException("Gênero não encontrado.");
        }
        // Remove o registro após a validação anterior.
        repository.deleteById(id);
    }
}
