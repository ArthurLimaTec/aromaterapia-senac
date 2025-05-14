package org.general.service;

import org.general.model.OleoEssencial;
import org.general.repository.OleoEssencialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OleoEssencialService {

    private final OleoEssencialRepository repository;

    public OleoEssencialService(OleoEssencialRepository repository) {
        this.repository = repository;
    }

    public List<OleoEssencial> listAll() {
        return repository.findAll();
    }

    public Optional<OleoEssencial> findById(Long id) {
        return repository.findById(id);
    }

    public OleoEssencial save(OleoEssencial oleoEssencial) {
        return repository.save(oleoEssencial);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
