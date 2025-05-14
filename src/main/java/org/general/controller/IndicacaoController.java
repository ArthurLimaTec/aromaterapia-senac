package org.general.controller;

import org.general.model.Indicacao;
import org.general.service.IndicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicacao")
public class IndicacaoController {

    private final IndicacaoService service;

    public IndicacaoController(IndicacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Indicacao> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indicacao> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Indicacao criar(@RequestBody Indicacao indicacao) {
        return service.save(indicacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Indicacao> atualizar(@PathVariable Long id, @RequestBody Indicacao indicacao) {
        return service.findById(id)
                .map(p -> {
                    indicacao.setId(id);
                    return ResponseEntity.ok(service.save(indicacao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.findById(id)
                .map(p -> {
                    service.delete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
