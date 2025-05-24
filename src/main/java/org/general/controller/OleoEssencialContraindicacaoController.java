//package org.general.controller;
//
//import org.general.model.OleoEssencialContraindicacao;
//import org.general.service.OleoEssencialContraindicacaoService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/oleoessencial-contraindicacao")
//public class OleoEssencialContraindicacaoController {
//
//    private final OleoEssencialContraindicacaoService service;
//
//    public OleoEssencialContraindicacaoController(OleoEssencialContraindicacaoService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<OleoEssencialContraindicacao> listAll() {
//        return service.listAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OleoEssencialContraindicacao> buscarPorId(@PathVariable Long id) {
//        return service.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public OleoEssencialContraindicacao criar(@RequestBody OleoEssencialContraindicacao oleoEssencialContraindicacao) {
//        return service.save(oleoEssencialContraindicacao);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<OleoEssencialContraindicacao> atualizar(@PathVariable Long id, @RequestBody OleoEssencialContraindicacao oleoEssencialContraindicacao) {
//        return service.findById(id)
//                .map(p -> {
//                    oleoEssencialContraindicacao.setId(id);
//                    return ResponseEntity.ok(service.save(oleoEssencialContraindicacao));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable Long id) {
//        return service.findById(id)
//                .map(p -> {
//                    service.delete(id);
//                    return ResponseEntity.noContent().<Void>build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//}
