package com.guilherme.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
// Centraliza a conversão de exceções da API em respostas JSON padronizadas.
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // Retorna a primeira mensagem produzida pelas validações do corpo da requisição.
    public Map<String, Object> validacao(MethodArgumentNotValidException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("erro", ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(e -> e.getDefaultMessage())
                .orElse("Dados inválidos."));
        return resposta;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // Expõe a mensagem das regras de negócio como erro HTTP 400.
    public Map<String, Object> regra(RuntimeException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("erro", ex.getMessage());
        return resposta;
    }
}
