package org.general.service;

import org.general.model.Contraindicacao;
import org.general.repository.ContraindicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContraindicacaoService {

    private final ContraindicacaoRepository repository;

    public ContraindicacaoService(ContraindicacaoRepository repository) {
        this.repository = repository;
    }

    public List<Contraindicacao> listAll() {
        return repository.findAll();
    }

    public Optional<Contraindicacao> findById(Long id) {
        return repository.findById(id);
    }

    public Contraindicacao save(Contraindicacao contraindicacao) {
        return repository.save(contraindicacao);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
