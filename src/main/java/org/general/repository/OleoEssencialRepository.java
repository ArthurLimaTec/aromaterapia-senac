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
    WHERE (:oleos IS NULL OR FIND_IN_SET(oe.nome, :oleos))
      AND (:indicacoes IS NULL OR FIND_IN_SET(i.sintoma, :indicacoes))
      AND (:contraindicacoes IS NULL OR FIND_IN_SET(c.contraindicacao, :contraindicacoes))
    GROUP BY oe.nome
    """, nativeQuery = true)
    List<Object[]> buscarOleosComIndicacoesEContraindicacoes(
            @Param("oleos") String oleos,
            @Param("indicacoes") String indicacoes,
            @Param("contraindicacoes") String contraindicacoes
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
