package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.dto.ObraRequest;
import com.guilherme.catalogo.model.Obra;
import com.guilherme.catalogo.service.ObraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    private final ObraService service;

    public ObraController(ObraService service) {
        this.service = service;
    }

    @GetMapping
    public List<Obra> listar(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Long generoId,
            @RequestParam(required = false) Long editoraId) {
        return service.listar(busca, generoId, editoraId);
    }

    @GetMapping("/{id}")
    public Obra buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Obra salvar(@Valid @RequestBody ObraRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public Obra atualizar(@PathVariable Long id,
                          @Valid @RequestBody ObraRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
