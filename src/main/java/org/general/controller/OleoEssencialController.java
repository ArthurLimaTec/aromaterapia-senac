package org.general.controller;

import org.general.model.OleoEssencial;
import org.general.service.OleoEssencialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oleo-essencial")
public class OleoEssencialController {

    private final OleoEssencialService service;

    public OleoEssencialController(OleoEssencialService service) {
        this.service = service;
    }

    @GetMapping
    public List<OleoEssencial> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OleoEssencial> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OleoEssencial criar(@RequestBody OleoEssencial oleoEssencial) {
        return service.save(oleoEssencial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OleoEssencial> atualizar(@PathVariable Long id, @RequestBody OleoEssencial oleoEssencial) {
        return service.findById(id)
                .map(p -> {
                    oleoEssencial.setId(id);
                    return ResponseEntity.ok(service.save(oleoEssencial));
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
