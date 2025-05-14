package org.general.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OLEOS_ESSENCIAIS_CONTRAINDICACOES")
public class OleoEssencialContraindicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OleoEssencial oleoEssencialId;

    private Contraindicacao contraindicacaoId;

}
