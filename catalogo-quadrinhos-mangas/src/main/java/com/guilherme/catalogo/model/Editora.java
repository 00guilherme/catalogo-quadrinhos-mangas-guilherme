package com.guilherme.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "editora")
// Entidade persistida que representa uma editora do catálogo.
public class Editora {

    // Chave primária gerada automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome obrigatório e único da editora, limitado a 120 caracteres.
    @NotBlank(message = "O nome da editora é obrigatório")
    @Column(nullable = false, unique = true, length = 120)
    private String nome;

    // Construtor exigido pelo JPA para materializar registros.
    public Editora() {
    }

    // Construtor auxiliar para criar uma editora já com seu nome.
    public Editora(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        // Retorna o identificador persistido da editora.
        return id;
    }

    public String getNome() {
        // Retorna o nome exibido para a editora.
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
