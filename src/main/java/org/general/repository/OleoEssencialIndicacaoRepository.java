package org.general.repository;

import org.general.model.OleoEssencialIndicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OleoEssencialIndicacaoRepository extends JpaRepository<OleoEssencialIndicacao, Long> {

}
