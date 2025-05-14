package org.general.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OLEOS_ESSENCIAIS")
public class OleoEssencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

}
