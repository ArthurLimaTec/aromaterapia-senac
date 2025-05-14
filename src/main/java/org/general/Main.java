package org.general;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Protótipo de endpoint: cadastra um óleo essencial
    @PostMapping("/api/prototipo-oleo")
    public String cadastrarOleoEssencial(@RequestParam String nome) {
        // Aqui seria uma chamada para o service de verdade, mas para protótipo:
        return "Óleo essencial cadastrado: " + nome;
    }

    // Protótipo de endpoint: lista todos (simulado)
    @GetMapping("/api/prototipo-oleos")
    public List<String> listarOleosEssenciais() {
        // Simulação de retorno de lista
        return Arrays.asList("Lavanda", "Alecrim", "Hortelã-pimenta");
    }

}