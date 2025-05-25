package org.general.repository;

import org.general.model.OleoEssencialContraindicacao;
import org.general.model.OleoEssencialIndicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OleoEssencialContraindicacaoRepository extends JpaRepository<OleoEssencialContraindicacao, Long> {

    @Query(
            value = "select oec.*\n" +
                    "from oleos_essenciais_contraindicacoes oec \n" +
                    "where oleo_essencial_id = :oleoEssencialId and contraindicacao_id = :contraindicacaoId", nativeQuery = true
    )
    OleoEssencialContraindicacao findMatchByContraindicacaoId(Long oleoEssencialId, Long contraindicacaoId);

}
