package org.general.controller;

import org.general.model.OleoEssencialIndicacao;
import org.general.service.OleoEssencialIndicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oleoessencial-indicacao")
public class OleoEssencialIndicacaoController {

    private final OleoEssencialIndicacaoService service;

    public OleoEssencialIndicacaoController(OleoEssencialIndicacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<OleoEssencialIndicacao> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OleoEssencialIndicacao> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OleoEssencialIndicacao criar(@RequestBody OleoEssencialIndicacao oleoEssencialIndicacao) {
        return service.save(oleoEssencialIndicacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OleoEssencialIndicacao> atualizar(@PathVariable Long id, @RequestBody OleoEssencialIndicacao oleoEssencialIndicacao) {
        return service.findById(id)
                .map(p -> {
                    oleoEssencialIndicacao.setId(id);
                    return ResponseEntity.ok(service.save(oleoEssencialIndicacao));
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
