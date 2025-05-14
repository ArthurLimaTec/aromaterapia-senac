package org.general.repository;

import org.general.model.Indicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicacaoRepository extends JpaRepository<Indicacao, Long> {

}
