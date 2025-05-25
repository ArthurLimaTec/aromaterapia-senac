package org.general.repository;

import org.general.model.OleoEssencialContraindicacao;
import org.general.model.OleoEssencialIndicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OleoEssencialContraindicacaoRepository extends JpaRepository<OleoEssencialContraindicacao, Long> {

    @Query(
            value = "select oec.*\n" +
                    "from oleos_essenciais_contraindicacoes oec \n" +
                    "where oleo_essencial_id = :oleoEssencialId and contraindicacao_id = :contraindicacaoId", nativeQuery = true
    )
    OleoEssencialContraindicacao findMatchByContraindicacaoId(Long oleoEssencialId, Long contraindicacaoId);

    @Query(
            value = "select oec.*\n" +
                    "from oleos_essenciais_contraindicacoes oec \n" +
                    "where oleo_essencial_id = :oleoEssencialId",nativeQuery = true
    )
    List<OleoEssencialContraindicacao> findAllByOleoId(Long oleoEssencialId);

    @Modifying
    @Transactional
    @Query(value = """
            delete from oleos_essenciais_contraindicacoes oec
            where oec.oleo_essencial_id = :oleoId
    """, nativeQuery = true)
    void excluirVinculo(
            @Param("oleoId") Long oleoId
    );
}
