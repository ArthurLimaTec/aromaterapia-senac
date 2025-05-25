package org.general.repository;

import org.general.model.OleoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OleoEssencialRepository extends JpaRepository<OleoEssencial, Long> {

    OleoEssencial findByNome(String nome);

    @Query(value = """
    SELECT 
        oe.nome,
        GROUP_CONCAT(DISTINCT i.sintoma SEPARATOR '; ') AS indicacoes,
        GROUP_CONCAT(DISTINCT c.contraindicacao SEPARATOR '; ') AS contraindicacoes
    FROM oleos_essenciais oe
    LEFT JOIN oleos_essenciais_indicacoes oei ON oe.id = oei.oleo_essencial_id
    LEFT JOIN indicacoes i ON i.id = oei.indicacao_id
    LEFT JOIN oleos_essenciais_contraindicacoes oec ON oec.oleo_essencial_id = oe.id
    LEFT JOIN contraindicacoes c ON c.id = oec.contraindicacao_id
    WHERE (:oleos IS NULL OR oe.nome IN (:oleos))
      AND (:indicacoes IS NULL OR i.sintoma IN (:indicacoes))
      AND (:contraindicacoes IS NULL OR c.contraindicacao IN (:contraindicacoes))
    GROUP BY oe.nome
    """, nativeQuery = true)
    List<Object[]> buscarOleosComIndicacoesEContraindicacoes(
            @Param("oleos") List<String> oleos,
            @Param("indicacoes") List<String> indicacoes,
            @Param("contraindicacoes") List<String> contraindicacoes
    );


    @Query(value = """
    SELECT 
        oe.nome,
        GROUP_CONCAT(DISTINCT i.sintoma SEPARATOR '; ') AS indicacoes,
        GROUP_CONCAT(DISTINCT c.contraindicacao SEPARATOR '; ') AS contraindicacoes
    FROM oleos_essenciais oe
    LEFT JOIN oleos_essenciais_indicacoes oei ON oe.id = oei.oleo_essencial_id
    LEFT JOIN indicacoes i ON i.id = oei.indicacao_id
    LEFT JOIN oleos_essenciais_contraindicacoes oec ON oec.oleo_essencial_id = oe.id
    LEFT JOIN contraindicacoes c ON c.id = oec.contraindicacao_id
    GROUP BY oe.nome
    """, nativeQuery = true)
    List<Object[]> buscarTodosOleosComIndicacoesEContraindicacoes();
}
