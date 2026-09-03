package com.guilherme.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "genero")
// Entidade persistida que representa um gênero do catálogo.
public class Genero {

    // Chave primária gerada automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome obrigatório e único do gênero, limitado a 80 caracteres.
    @NotBlank(message = "O nome do gênero é obrigatório")
    @Column(nullable = false, unique = true, length = 80)
    private String nome;

    // Construtor exigido pelo JPA para materializar registros.
    public Genero() {
    }

    // Construtor auxiliar para criar um gênero já com seu nome.
    public Genero(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        // Retorna o identificador persistido do gênero.
        return id;
    }

    public String getNome() {
        // Retorna o nome exibido para o gênero.
        return nome;
    }

    public void setId(Long id) {
        // Permite ao framework ou ao código de teste definir o identificador.
        this.id = id;
    }

    public void setNome(String nome) {
        // Atualiza o nome antes da persistência ou da serialização.
        this.nome = nome;
    }
}
