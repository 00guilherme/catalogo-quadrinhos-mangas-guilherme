package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.model.Genero;
import com.guilherme.catalogo.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
// Expõe as operações HTTP de cadastro e manutenção de gêneros.
public class GeneroController {

    // Serviço que contém as regras de negócio dos gêneros.
    private final GeneroService service;

    // Injeta o serviço gerenciado pelo Spring.
    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    // Lista todos os gêneros cadastrados.
    public List<Genero> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    // Busca um gênero pelo identificador informado na URL.
    public Genero buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Valida e cria um novo gênero, respondendo com HTTP 201.
    public Genero salvar(@Valid @RequestBody Genero genero) {
        return service.salvar(genero);
    }

    @PutMapping("/{id}")
    // Atualiza os dados do gênero existente.
    public Genero atualizar(@PathVariable Long id,
                            @Valid @RequestBody Genero genero) {
        return service.atualizar(id, genero);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // Exclui o gênero e retorna HTTP 204 quando a operação termina.
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
