package com.guilherme.catalogo.service;

import com.guilherme.catalogo.dto.ObraRequest;
import com.guilherme.catalogo.model.Editora;
import com.guilherme.catalogo.model.Genero;
import com.guilherme.catalogo.model.Obra;
import com.guilherme.catalogo.repository.EditoraRepository;
import com.guilherme.catalogo.repository.GeneroRepository;
import com.guilherme.catalogo.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Coordena as regras de negócio, relacionamentos e persistência das obras.
public class ObraService {

        // Serviço que consulta a capa da obra em uma API externa.
    private final ImagemObraService imagemObraService;
        // Repositório principal das obras.
    private final ObraRepository obraRepository;
        // Repositórios usados para validar os relacionamentos recebidos no DTO.
    private final GeneroRepository generoRepository;
    private final EditoraRepository editoraRepository;

    public ObraService(
            ImagemObraService imagemObraService,
            ObraRepository obraRepository,
            GeneroRepository generoRepository,
            EditoraRepository editoraRepository) {

                // Dependências injetadas pelo Spring para manter o serviço testável.
        this.imagemObraService = imagemObraService;
        this.obraRepository = obraRepository;
        this.generoRepository = generoRepository;
        this.editoraRepository = editoraRepository;
    }

    // =========================================================
    // LISTAR OBRAS
    // =========================================================

    public List<Obra> listar(
            String busca,
            Long generoId,
            Long editoraId) {

        return obraRepository.filtrar(
                busca == null ? "" : busca.trim(),
                generoId,
                editoraId);
    }

    // =========================================================
    // BUSCAR UMA OBRA PELO ID
    // =========================================================

    public Obra buscar(Long id) {

        return obraRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Obra não encontrada."));
    }

    // =========================================================
    // SALVAR NOVA OBRA
    // =========================================================

    public Obra salvar(ObraRequest request) {

        Obra obra = new Obra();

        preencher(obra, request);

        return obraRepository.save(obra);
    }

    // =========================================================
    // ATUALIZAR OBRA
    // =========================================================

    public Obra atualizar(
            Long id,
            ObraRequest request) {

        Obra obra = buscar(id);

        preencher(obra, request);

        return obraRepository.save(obra);
    }

    // =========================================================
    // EXCLUIR OBRA
    // =========================================================

    public void excluir(Long id) {

        if (!obraRepository.existsById(id)) {

            throw new RuntimeException(
                    "Obra não encontrada.");
        }

        obraRepository.deleteById(id);
    }

    // =========================================================
    // PREENCHER DADOS DA OBRA
    // =========================================================

    private void preencher(
            Obra obra,
            ObraRequest request) {

        // -----------------------------------------------------
        // Busca o gênero no banco
        // -----------------------------------------------------

        Genero genero = generoRepository
                .findById(request.getGeneroId())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Gênero não encontrado."));

        // -----------------------------------------------------
        // Busca a editora no banco
        // -----------------------------------------------------

        Editora editora = editoraRepository
                .findById(request.getEditoraId())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Editora não encontrada."));

        // -----------------------------------------------------
        // Preenche os dados normais da obra
        // -----------------------------------------------------

        obra.setTitulo(
                request.getTitulo().trim());

        obra.setVolume(
                request.getVolume());

        obra.setAutor(
                request.getAutor().trim());

        obra.setNota(
                request.getNota());

        obra.setStatus(
                request.getStatus());

        obra.setGenero(
                genero);

        obra.setEditora(
                editora);

        // -----------------------------------------------------
        // BUSCA AUTOMATICAMENTE A CAPA
        // -----------------------------------------------------

        String imagemUrl = imagemObraService.buscarImagem(
                request.getTitulo().trim());

        // -----------------------------------------------------
        // Só salva a imagem se uma capa foi encontrada
        // -----------------------------------------------------

        if (imagemUrl != null
                && !imagemUrl.isBlank()) {

            obra.setImagemUrl(imagemUrl);
        }
    }
}