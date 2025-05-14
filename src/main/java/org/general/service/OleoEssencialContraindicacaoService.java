package org.general.service;

import org.general.model.OleoEssencialContraindicacao;
import org.general.repository.OleoEssencialContraindicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OleoEssencialContraindicacaoService {

    private final OleoEssencialContraindicacaoRepository repository;

    public OleoEssencialContraindicacaoService(OleoEssencialContraindicacaoRepository repository) {
        this.repository = repository;
    }

    public List<OleoEssencialContraindicacao> listAll() {
        return repository.findAll();
    }

    public Optional<OleoEssencialContraindicacao> findById(Long id) {
        return repository.findById(id);
    }

    public OleoEssencialContraindicacao save(OleoEssencialContraindicacao oleoEssencialContraindicacao) {
        return repository.save(oleoEssencialContraindicacao);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
