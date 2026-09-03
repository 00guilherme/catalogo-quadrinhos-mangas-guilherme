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
// Expõe as operações HTTP de consulta e manutenção das obras.
public class ObraController {

    // Serviço responsável pelas regras e persistência das obras.
    private final ObraService service;

    // Injeta o serviço gerenciado pelo Spring.
    public ObraController(ObraService service) {
        this.service = service;
    }

    @GetMapping
    // Lista obras aplicando, opcionalmente, busca textual e filtros relacionais.
    public List<Obra> listar(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Long generoId,
            @RequestParam(required = false) Long editoraId) {
        return service.listar(busca, generoId, editoraId);
    }

    @GetMapping("/{id}")
    // Busca uma obra pelo identificador informado na URL.
    public Obra buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Valida o DTO e cria uma nova obra, respondendo com HTTP 201.
    public Obra salvar(@Valid @RequestBody ObraRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    // Valida o DTO e atualiza uma obra existente.
    public Obra atualizar(@PathVariable Long id,
                          @Valid @RequestBody ObraRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // Exclui a obra e retorna HTTP 204 quando a operação termina.
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
