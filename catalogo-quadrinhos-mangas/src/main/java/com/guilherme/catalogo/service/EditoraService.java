package com.guilherme.catalogo.service;

import com.guilherme.catalogo.model.Editora;
import com.guilherme.catalogo.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Implementa as regras de negócio e coordena o acesso ao repositório de editoras.
public class EditoraService {

    // Abstração do Spring Data usada para consultar e persistir editoras.
    private final EditoraRepository repository;

    // Recebe o repositório criado pelo Spring.
    public EditoraService(EditoraRepository repository) {
        this.repository = repository;
    }

    public List<Editora> listar() {
        // Retorna todos os registros para preencher listas e filtros da interface.
        return repository.findAll();
    }

    public Editora buscar(Long id) {
        // Interrompe a operação com uma mensagem de negócio quando o ID não existe.
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada."));
    }

    public Editora salvar(Editora editora) {
        // Normaliza espaços antes de verificar duplicidade e salvar o nome.
        editora.setNome(editora.getNome().trim());
        // Evita editoras duplicadas sem diferenciar letras maiúsculas de minúsculas.
        if (repository.existsByNomeIgnoreCase(editora.getNome())) {
            throw new RuntimeException("Essa editora já existe.");
        }
        return repository.save(editora);
    }

    public Editora atualizar(Long id, Editora dados) {
        // Busca a entidade gerenciada e altera somente o nome recebido.
        Editora editora = buscar(id);
        editora.setNome(dados.getNome().trim());
        return repository.save(editora);
    }

    public void excluir(Long id) {
        // Confirma a existência para devolver um erro de negócio claro.
        if (!repository.existsById(id)) {
            throw new RuntimeException("Editora não encontrada.");
        }
        // Remove o registro após a validação anterior.
        repository.deleteById(id);
    }
}
