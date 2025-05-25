package org.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TableView {

    private String oleoEssencial;
    private String indicacoes;
    private String contraindicacoes;
}
