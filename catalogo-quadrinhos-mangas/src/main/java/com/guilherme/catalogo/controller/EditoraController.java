package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.model.Editora;
import com.guilherme.catalogo.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoras")
// Expõe as operações HTTP de cadastro e manutenção de editoras.
public class EditoraController {

    // Serviço que contém as regras de negócio das editoras.
    private final EditoraService service;

    // Injeta o serviço gerenciado pelo Spring.
    public EditoraController(EditoraService service) {
        this.service = service;
    }

    @GetMapping
    // Lista todas as editoras cadastradas.
    public List<Editora> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    // Busca uma editora pelo identificador informado na URL.
    public Editora buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Valida e cria uma nova editora, respondendo com HTTP 201.
    public Editora salvar(@Valid @RequestBody Editora editora) {
        return service.salvar(editora);
    }

    @PutMapping("/{id}")
    // Atualiza os dados da editora existente.
    public Editora atualizar(@PathVariable Long id,
                             @Valid @RequestBody Editora editora) {
        return service.atualizar(id, editora);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // Exclui a editora e retorna HTTP 204 quando a operação termina.
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
