package com.guilherme.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "obra")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Column(nullable = false, length = 180)
    private String titulo;

    @NotNull(message = "O volume é obrigatório")
    @Min(value = 1, message = "O volume deve ser maior que zero")
    @Column(nullable = false)
    private Integer volume;

    @NotBlank(message = "O autor é obrigatório")
    @Column(nullable = false, length = 150)
    private String autor;

    @NotNull(message = "A nota é obrigatória")
    @DecimalMin(value = "0.0", message = "A nota mínima é 0")
    @DecimalMax(value = "10.0", message = "A nota máxima é 10")
    @Column(nullable = false)
    private Double nota;

    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusColecao status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    public Obra() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getAutor() {
        return autor;
    }

    public Double getNota() {
        return nota;
    }

    public StatusColecao getStatus() {
        return status;
    }

    public Genero getGenero() {
        return genero;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setStatus(StatusColecao status) {
        this.status = status;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Column(length = 1000)
    private String imagemUrl;

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

}
