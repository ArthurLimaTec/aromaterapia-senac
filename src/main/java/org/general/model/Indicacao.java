package org.general.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "INDICACOES")
public class Indicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SINTOMA")
    private String sintoma;
}
