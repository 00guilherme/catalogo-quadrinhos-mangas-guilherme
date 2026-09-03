package com.guilherme.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "obra")
// Entidade principal: reúne dados da obra e seus relacionamentos no catálogo.
public class Obra {

    // Identificador gerado pelo banco para cada obra.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título obrigatório persistido na coluna principal da obra.
    @NotBlank(message = "O título é obrigatório")
    @Column(nullable = false, length = 180)
    private String titulo;

    // Volume obrigatório; a validação impede números menores que um.
    @NotNull(message = "O volume é obrigatório")
    @Min(value = 1, message = "O volume deve ser maior que zero")
    @Column(nullable = false)
    private Integer volume;

    // Autor obrigatório da obra.
    @NotBlank(message = "O autor é obrigatório")
    @Column(nullable = false, length = 150)
    private String autor;

    // Nota do usuário, armazenada na escala de zero a dez.
    @NotNull(message = "A nota é obrigatória")
    @DecimalMin(value = "0.0", message = "A nota mínima é 0")
    @DecimalMax(value = "10.0", message = "A nota máxima é 10")
    @Column(nullable = false)
    private Double nota;

    // Enum persistido como texto para manter os valores legíveis no banco.
    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusColecao status;

    // Cada obra pertence obrigatoriamente a um gênero.
    @ManyToOne(optional = false)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    // Cada obra pertence obrigatoriamente a uma editora.
    @ManyToOne(optional = false)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    // Construtor vazio necessário para o JPA criar a entidade.
    public Obra() {
    }

    public Long getId() {
        // Retorna o identificador da obra.
        return id;
    }

    public String getTitulo() {
        // Retorna o título para regras de negócio e serialização JSON.
        return titulo;
    }

    public Integer getVolume() {
        // Retorna o número do volume.
        return volume;
    }

    public String getAutor() {
        // Retorna o autor da obra.
        return autor;
    }

    public Double getNota() {
        // Retorna a nota atribuída à obra.
        return nota;
    }

    public StatusColecao getStatus() {
        // Retorna o status atual da coleção.
        return status;
    }

    public Genero getGenero() {
        // Retorna o gênero associado.
        return genero;
    }

    public Editora getEditora() {
        // Retorna a editora associada.
        return editora;
    }

    public void setId(Long id) {
        // Define o identificador, principalmente usado pelo JPA.
        this.id = id;
    }

    public void setTitulo(String titulo) {
        // Define o título da obra.
        this.titulo = titulo;
    }

    public void setVolume(Integer volume) {
        // Define o número do volume.
        this.volume = volume;
    }

    public void setAutor(String autor) {
        // Define o autor da obra.
        this.autor = autor;
    }

    public void setNota(Double nota) {
        // Define a nota da obra.
        this.nota = nota;
    }

    public void setStatus(StatusColecao status) {
        // Define o status da coleção.
        this.status = status;
    }

    public void setGenero(Genero genero) {
        // Define o gênero relacionado.
        this.genero = genero;
    }

    public void setEditora(Editora editora) {
        // Define a editora relacionada.
        this.editora = editora;
    }

    // URL opcional da capa encontrada no serviço externo de imagens.
    @Column(length = 1000)
    private String imagemUrl;

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

}
