package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.model.StatusColecao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
// Fornece os valores possíveis para o campo de status da coleção.
public class StatusController {

    @GetMapping("/api/status")
    // Converte todos os valores do enum em uma lista para o frontend.
    public List<StatusColecao> listar() {
        return Arrays.asList(StatusColecao.values());
    }
}
