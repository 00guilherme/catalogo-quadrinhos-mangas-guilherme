package com.guilherme.catalogo.controller;

import com.guilherme.catalogo.model.StatusColecao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class StatusController {

    @GetMapping("/api/status")
    public List<StatusColecao> listar() {
        return Arrays.asList(StatusColecao.values());
    }
}
