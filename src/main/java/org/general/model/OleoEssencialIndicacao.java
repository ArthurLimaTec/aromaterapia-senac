package org.general.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OLEOS_ESSENCIAIS_INDICACOES")
public class OleoEssencialIndicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OleoEssencial oleoEssencialId;

    private Indicacao indicacaoId;

}
