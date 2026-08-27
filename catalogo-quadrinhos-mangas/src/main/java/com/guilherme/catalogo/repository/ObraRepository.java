package com.guilherme.catalogo.repository;

import com.guilherme.catalogo.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObraRepository extends JpaRepository<Obra, Long> {

    List<Obra> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(
            String titulo, String autor
    );

    List<Obra> findByGeneroId(Long generoId);

    List<Obra> findByEditoraId(Long editoraId);

    @Query("""
        SELECT o FROM Obra o
        WHERE (:busca IS NULL OR :busca = ''
               OR LOWER(o.titulo) LIKE LOWER(CONCAT('%', :busca, '%'))
               OR LOWER(o.autor) LIKE LOWER(CONCAT('%', :busca, '%')))
          AND (:generoId IS NULL OR o.genero.id = :generoId)
          AND (:editoraId IS NULL OR o.editora.id = :editoraId)
        ORDER BY o.titulo ASC, o.volume ASC
    """)
    List<Obra> filtrar(
            @Param("busca") String busca,
            @Param("generoId") Long generoId,
            @Param("editoraId") Long editoraId
    );
}
