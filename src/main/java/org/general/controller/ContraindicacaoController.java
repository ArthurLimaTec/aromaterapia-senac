package org.general.controller;

import org.general.model.Contraindicacao;
import org.general.service.ContraindicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contraindicacao")
public class ContraindicacaoController {

    private final ContraindicacaoService service;

    public ContraindicacaoController(ContraindicacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Contraindicacao> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contraindicacao> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contraindicacao criar(@RequestBody Contraindicacao contraindicacao) {
        return service.save(contraindicacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contraindicacao> atualizar(@PathVariable Long id, @RequestBody Contraindicacao contraindicacao) {
        return service.findById(id)
                .map(p -> {
                    contraindicacao.setId(id);
                    return ResponseEntity.ok(service.save(contraindicacao));
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
