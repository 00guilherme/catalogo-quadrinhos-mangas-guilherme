package com.guilherme.catalogo.dto;

import com.guilherme.catalogo.model.StatusColecao;
import jakarta.validation.constraints.*;

public class ObraRequest {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotNull(message = "O volume é obrigatório")
    @Min(value = 1, message = "O volume deve ser maior que zero")
    private Integer volume;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotNull(message = "A nota é obrigatória")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double nota;

    @NotNull(message = "O status é obrigatório")
    private StatusColecao status;

    @NotNull(message = "O gênero é obrigatório")
    private Long generoId;

    @NotNull(message = "A editora é obrigatória")
    private Long editoraId;

    public String getTitulo() { return titulo; }
    public Integer getVolume() { return volume; }
    public String getAutor() { return autor; }
    public Double getNota() { return nota; }
    public StatusColecao getStatus() { return status; }
    public Long getGeneroId() { return generoId; }
    public Long getEditoraId() { return editoraId; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setVolume(Integer volume) { this.volume = volume; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setNota(Double nota) { this.nota = nota; }
    public void setStatus(StatusColecao status) { this.status = status; }
    public void setGeneroId(Long generoId) { this.generoId = generoId; }
    public void setEditoraId(Long editoraId) { this.editoraId = editoraId; }
}
