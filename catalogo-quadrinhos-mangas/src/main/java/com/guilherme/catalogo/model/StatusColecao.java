package com.guilherme.catalogo.model;

// Estados permitidos para acompanhar a leitura de uma obra.
public enum StatusColecao {
    // Obra cadastrada, mas ainda não lida.
    NAO_LIDO,
    // Obra cuja leitura está em andamento.
    LENDO,
    // Obra cuja leitura foi concluída.
    LIDO
}
