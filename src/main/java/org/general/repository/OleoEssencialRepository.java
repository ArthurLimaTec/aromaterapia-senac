package org.general.repository;

import org.general.model.OleoEssencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OleoEssencialRepository extends JpaRepository<OleoEssencial, Long> {

}
