package org.general.repository;

import org.general.model.Contraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraindicacaoRepository extends JpaRepository<Contraindicacao, Long> {

    Contraindicacao findByContraindicacao(String contraindicacao);
}
