package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.model.Editora;
import com.guilherme.catalogo.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

    private final EditoraService service;

    public EditoraController(EditoraService service) {
        this.service = service;
    }

    @GetMapping
    public List<Editora> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Editora buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Editora salvar(@Valid @RequestBody Editora editora) {
        return service.salvar(editora);
    }

    @PutMapping("/{id}")
    public Editora atualizar(@PathVariable Long id,
                             @Valid @RequestBody Editora editora) {
        return service.atualizar(id, editora);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
