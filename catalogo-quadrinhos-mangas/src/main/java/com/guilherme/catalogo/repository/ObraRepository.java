package com.guilherme.catalogo.repository;

import com.guilherme.catalogo.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Repositório das obras com operações CRUD e consultas derivadas ou personalizadas.
public interface ObraRepository extends JpaRepository<Obra, Long> {

    // Busca um texto no título ou no autor, sem diferenciar maiúsculas e minúsculas.
    List<Obra> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(
            String titulo, String autor
    );

    // Lista as obras pertencentes a um gênero específico.
    List<Obra> findByGeneroId(Long generoId);

    // Lista as obras publicadas por uma editora específica.
    List<Obra> findByEditoraId(Long editoraId);

    // Combina busca textual, filtros opcionais e ordenação para a tela do catálogo.
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
