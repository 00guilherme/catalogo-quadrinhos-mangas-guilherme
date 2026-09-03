package com.guilherme.catalogo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
// Consulta a Open Library para localizar automaticamente a capa de uma obra.
public class ImagemObraService {

    // Cliente HTTP usado para chamar a API externa.
    private final RestTemplate restTemplate;
    // Conversor que transforma o JSON recebido em uma árvore navegável.
    private final ObjectMapper objectMapper;

    // Cria as dependências simples utilizadas pela consulta externa.
    public ImagemObraService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String buscarImagem(String titulo) {

        try {
            // A API recebe o título na query string, por isso os espaços são codificados.

            // Remove espaços desnecessários do título
            String tituloFormatado = titulo.trim().replace(" ", "%20");

            // URL da API Open Library
            String url = "https://openlibrary.org/search.json"
                    + "?title=" + tituloFormatado
                    + "&limit=1";

            // Faz a requisição para a API
            String resposta = restTemplate.getForObject(url, String.class);

            // Transforma a resposta JSON em uma árvore de dados
            JsonNode raiz = objectMapper.readTree(resposta);

            // Pega a lista de obras encontradas
            JsonNode documentos = raiz.get("docs");

            // Verifica se encontrou alguma obra
            if (documentos != null
                    && documentos.isArray()
                    && documentos.size() > 0) {

                // Pega a primeira obra encontrada
                JsonNode primeiraObra = documentos.get(0);

                // Pega o ID da capa
                JsonNode coverId = primeiraObra.get("cover_i");

                // Verifica se existe uma capa
                if (coverId != null
                        && !coverId.isNull()) {

                    // Monta a URL da imagem
                    return "https://covers.openlibrary.org/b/id/"
                            + coverId.asLong()
                            + "-L.jpg";
                }
            }

            // Caso não encontre capa
            return null;

        } catch (Exception e) {

            // A falha externa não impede o cadastro; a obra apenas fica sem capa.
            System.out.println(
                    "Erro ao buscar imagem da obra: "
                            + e.getMessage());

            return null;
        }
    }
}