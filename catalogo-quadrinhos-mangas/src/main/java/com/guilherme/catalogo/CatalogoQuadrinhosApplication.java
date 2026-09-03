package com.guilherme.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Classe de entrada que inicializa o contexto do Spring Boot e o servidor embutido.
public class CatalogoQuadrinhosApplication {

    // Ponto de entrada utilizado pela JVM para iniciar a aplicação.
    public static void main(String[] args) {
        SpringApplication.run(CatalogoQuadrinhosApplication.class, args);
    }
}
