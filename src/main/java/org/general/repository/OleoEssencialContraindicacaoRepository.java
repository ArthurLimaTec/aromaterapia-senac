package org.general.repository;

import org.general.model.OleoEssencialContraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OleoEssencialContraindicacaoRepository extends JpaRepository<OleoEssencialContraindicacao, Long> {

}
