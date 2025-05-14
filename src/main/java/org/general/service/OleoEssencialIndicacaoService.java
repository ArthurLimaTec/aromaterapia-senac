package org.general.service;

import org.general.model.OleoEssencialIndicacao;
import org.general.repository.OleoEssencialIndicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OleoEssencialIndicacaoService {

    private final OleoEssencialIndicacaoRepository repository;

    public OleoEssencialIndicacaoService(OleoEssencialIndicacaoRepository repository) {
        this.repository = repository;
    }

    public List<OleoEssencialIndicacao> listAll() {
        return repository.findAll();
    }

    public Optional<OleoEssencialIndicacao> findById(Long id) {
        return repository.findById(id);
    }

    public OleoEssencialIndicacao save(OleoEssencialIndicacao oleoEssencialIndicacao) {
        return repository.save(oleoEssencialIndicacao);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
