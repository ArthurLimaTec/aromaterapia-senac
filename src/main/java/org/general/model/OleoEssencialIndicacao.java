package org.general.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "OLEOS_ESSENCIAIS_INDICACOES")
public class OleoEssencialIndicacao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OLEO_ESSENCIAL_ID") // nome da coluna FK na tabela de junção
    private OleoEssencial oleoEssencialId;

    @ManyToOne
    @JoinColumn(name = "INDICACAO_ID") // nome da coluna FK na tabela de junção
    private Indicacao indicacaoId;

}
