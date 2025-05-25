package org.general.repository;

import org.general.model.OleoEssencialContraindicacao;
import org.general.model.OleoEssencialIndicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OleoEssencialIndicacaoRepository extends JpaRepository<OleoEssencialIndicacao, Long> {

    @Query(
            value = "select oei.*\n" +
                    "from oleos_essenciais_indicacoes oei \n" +
                    "where oleo_essencial_id = :oleoEssencialId and indicacao_id = :indicacaoId",nativeQuery = true
    )
    OleoEssencialIndicacao findMatchByIndicacaoId(Long oleoEssencialId, Long indicacaoId);
}
