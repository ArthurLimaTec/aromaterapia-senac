package org.general.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTRAINDICACOES")
public class Contraindicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTRAINDICACAO")
    private String contraindicacao;

}
