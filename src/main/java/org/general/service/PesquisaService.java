package org.general.service;

import org.general.model.*;
import org.general.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PesquisaService {

    @Autowired
    private OleoEssencialRepository oleoEssencialRepository;
    @Autowired
    private IndicacaoRepository indicacaoRepository;
    @Autowired
    private ContraindicacaoRepository contraindicacaoRepository;
    @Autowired
    private OleoEssencialIndicacaoRepository oleoEssencialIndicacaoRepository;
    @Autowired
    private OleoEssencialContraindicacaoRepository oleoEssencialContraindicacaoRepository;

    private List<Indicacao> indicacoesList;
    private List<Contraindicacao> contraindicacoesList;

    public DefaultTableModel buscarTodos(){

        List<Object[]> resultados = oleoEssencialRepository.buscarTodosOleosComIndicacoesEContraindicacoes();

        List<TableView> tableViews = new ArrayList<>();
        for (Object[] linha : resultados) {
            String nome = (String) linha[0];
            String indicacoesStr = (String) linha[1];
            String contraindicacoesStr = (String) linha[2];
            tableViews.add(new TableView(nome, indicacoesStr, contraindicacoesStr));
        }

        return criarModeloTabela(tableViews);
    }

    public List<TableView> tratarCampos(String oleosTxt, String indicacoesTxt, String contraindicacoesTxt){

        List<String> oleos = oleosTxt == null || oleosTxt.isBlank()
                ? null
                : Arrays.stream(oleosTxt.split(";")).map(String::trim).toList();

        List<String> indicacoes = indicacoesTxt == null || indicacoesTxt.isBlank()
                ? null
                : Arrays.stream(indicacoesTxt.split(";")).map(String::trim).toList();

        List<String> contraindicacoes = contraindicacoesTxt == null || contraindicacoesTxt.isBlank()
                ? null
                : Arrays.stream(contraindicacoesTxt.split(";")).map(String::trim).toList();

        return buscarOleos(oleos, indicacoes, contraindicacoes);
    }

    public List<TableView> buscarOleos(List<String> oleos, List<String> indicacoes, List<String> contraindicacoes) {

        List<Object[]> resultados = oleoEssencialRepository.buscarOleosComIndicacoesEContraindicacoes(
                oleos, indicacoes, contraindicacoes
        );

        List<TableView> tableViews = new ArrayList<>();
        for (Object[] linha : resultados) {
            String nome = (String) linha[0];
            String indicacoesStr = (String) linha[1];
            String contraindicacoesStr = (String) linha[2];
            tableViews.add(new TableView(nome, indicacoesStr, contraindicacoesStr));
        }
        return tableViews;
    }


    public DefaultTableModel criarModeloTabela(List<TableView> tableViews) {
        // Define os nomes das colunas
        String[] colunas = {"Óleo Essencial", "Indicações", "Contraindicações"};

        // Cria o modelo de tabela editável
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Todas as células são editáveis
            }
        };

        // Preenche o modelo com os dados da lista
        for (TableView tv : tableViews) {
            Object[] linha = {
                    tv.getOleoEssencial(),
                    tv.getIndicacoes(),
                    tv.getContraindicacoes()
            };
            model.addRow(linha);
        }

        return model;
    }

}
