package com.guilherme.catalogo.dto;

import com.guilherme.catalogo.model.StatusColecao;
import jakarta.validation.constraints.*;

// DTO usado para receber os dados necessários ao criar ou atualizar uma obra.
public class ObraRequest {

    // Título exibido no catálogo; não pode ser vazio.
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    // Número do volume, limitado a valores inteiros maiores que zero.
    @NotNull(message = "O volume é obrigatório")
    @Min(value = 1, message = "O volume deve ser maior que zero")
    private Integer volume;

    // Nome do autor responsável pela obra.
    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    // Nota atribuída pelo usuário, dentro da escala de zero a dez.
    @NotNull(message = "A nota é obrigatória")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double nota;

    // Situação atual da obra na coleção.
    @NotNull(message = "O status é obrigatório")
    private StatusColecao status;

    // Identificador do gênero relacionado à obra.
    @NotNull(message = "O gênero é obrigatório")
    private Long generoId;

    // Identificador da editora relacionada à obra.
    @NotNull(message = "A editora é obrigatória")
    private Long editoraId;

    // Getters usados pelo Spring, pela validação e pelo serviço de domínio.
    public String getTitulo() { return titulo; }
    public Integer getVolume() { return volume; }
    public String getAutor() { return autor; }
    public Double getNota() { return nota; }
    public StatusColecao getStatus() { return status; }
    public Long getGeneroId() { return generoId; }
    public Long getEditoraId() { return editoraId; }

    // Setters usados pelo Jackson para montar o DTO recebido no corpo JSON.
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setVolume(Integer volume) { this.volume = volume; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setNota(Double nota) { this.nota = nota; }
    public void setStatus(StatusColecao status) { this.status = status; }
    public void setGeneroId(Long generoId) { this.generoId = generoId; }
    public void setEditoraId(Long editoraId) { this.editoraId = editoraId; }
}
