package org.general.service;

import org.general.model.Indicacao;
import org.general.repository.IndicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndicacaoService {

    private final IndicacaoRepository repository;

    public IndicacaoService(IndicacaoRepository repository) {
        this.repository = repository;
    }

    public List<Indicacao> listAll() {
        return repository.findAll();
    }

    public Optional<Indicacao> findById(Long id) {
        return repository.findById(id);
    }

    public Indicacao save(Indicacao indicacao) {
        return repository.save(indicacao);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
