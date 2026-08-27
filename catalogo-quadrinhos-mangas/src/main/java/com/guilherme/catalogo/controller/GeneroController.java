package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.model.Genero;
import com.guilherme.catalogo.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Genero> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Genero buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genero salvar(@Valid @RequestBody Genero genero) {
        return service.salvar(genero);
    }

    @PutMapping("/{id}")
    public Genero atualizar(@PathVariable Long id,
                            @Valid @RequestBody Genero genero) {
        return service.atualizar(id, genero);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
