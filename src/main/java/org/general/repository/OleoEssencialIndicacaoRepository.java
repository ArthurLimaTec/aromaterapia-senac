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
public interface OleoEssencialIndicacaoRepository extends JpaRepository<OleoEssencialIndicacao, Long> {

    @Query(
            value = "select oei.*\n" +
                    "from oleos_essenciais_indicacoes oei \n" +
                    "where oleo_essencial_id = :oleoEssencialId and indicacao_id = :indicacaoId",nativeQuery = true
    )
    OleoEssencialIndicacao findMatchByIndicacaoId(Long oleoEssencialId, Long indicacaoId);

    @Query(
            value = "select oei.*\n" +
                    "from oleos_essenciais_indicacoes oei \n" +
                    "where oleo_essencial_id = :oleoEssencialId",nativeQuery = true
    )
    List<OleoEssencialIndicacao> findAllByOleoId(Long oleoEssencialId);

    @Modifying
    @Transactional
    @Query(value = """
            delete from oleos_essenciais_indicacoes oei
            where oei.oleo_essencial_id = :oleoId
    """, nativeQuery = true)
    void excluirVinculo(
            @Param("oleoId") Long oleoId
    );



}
