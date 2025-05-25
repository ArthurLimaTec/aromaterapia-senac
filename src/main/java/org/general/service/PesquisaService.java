package org.general.service;

import org.general.model.*;
import org.general.repository.*;
import org.general.telas.JanelaEditar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public DefaultTableModel buscarTodos() {

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

    public List<TableView> tratarCampos(String oleosTxt, String indicacoesTxt, String contraindicacoesTxt) {

        String oleos = oleosTxt == null || oleosTxt.isBlank() ? null : Arrays.stream(oleosTxt.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(","));

        String indicacoes = indicacoesTxt == null || indicacoesTxt.isBlank() ? null : Arrays.stream(indicacoesTxt.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(","));

        String contraindicacoes = contraindicacoesTxt == null || contraindicacoesTxt.isBlank() ? null : Arrays.stream(contraindicacoesTxt.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(","));

        return buscarOleos(oleos, indicacoes, contraindicacoes);
    }

    public List<TableView> buscarOleos(String oleos, String indicacoes, String contraindicacoes) {

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

    public void excluir(String oleoEssencialNome) {

        try {

            OleoEssencial oleoEssencial = oleoEssencialRepository.findByNome(oleoEssencialNome);

            oleoEssencialIndicacaoRepository.excluirVinculo(oleoEssencial.getId());
            oleoEssencialContraindicacaoRepository.excluirVinculo(oleoEssencial.getId());

            oleoEssencialRepository.delete(oleoEssencial);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void editar(String oleoEssencialNome, List<String> indicacoesList, List<String> contraindicacoesList) {

        OleoEssencial oleoEssencial = oleoEssencialRepository.findByNome(oleoEssencialNome);
        List<OleoEssencialIndicacao> oleoEssencialIndicacaoDBList = oleoEssencialIndicacaoRepository.findAllByOleoId(oleoEssencial.getId());

        // Monta lista de sintomas das novas indicações
        Set<String> sintomasNovos = indicacoesList.stream()
                .map(String::trim)
                .collect(Collectors.toSet());

        // Vínculos com Indicações para remover (estão no banco, mas não na nova lista)
        List<OleoEssencialIndicacao> indicacoesParaRemover = oleoEssencialIndicacaoDBList.stream()
                .filter(oei -> !sintomasNovos.contains(oei.getIndicacaoId().getSintoma()))
                .toList();

        for (OleoEssencialIndicacao oei : indicacoesParaRemover) {
            oleoEssencialIndicacaoRepository.delete(oei);
        }


        // Sintomas já existentes no banco
        Set<String> sintomasDB = oleoEssencialIndicacaoDBList.stream()
                .map(oei -> oei.getIndicacaoId().getSintoma())
                .collect(Collectors.toSet());

        // Indicações para adicionar (estão na nova lista, mas não no banco)
        for (String sintoma : sintomasNovos) {
            if (!sintomasDB.contains(sintoma)) {
                Indicacao indicacao = indicacaoRepository.findBySintoma(sintoma);
                if (indicacao == null) {
                    indicacao = new Indicacao();
                    indicacao.setSintoma(sintoma);
                    indicacao = indicacaoRepository.save(indicacao); // Já retorna o objeto salvo
                }
                OleoEssencialIndicacao novoVinculo = new OleoEssencialIndicacao();
                novoVinculo.setOleoEssencialId(oleoEssencial);
                novoVinculo.setIndicacaoId(indicacao);
                oleoEssencialIndicacaoRepository.save(novoVinculo);
            }
        }

        List<OleoEssencialContraindicacao> oleoEssencialContraindicacaoDBList =
                oleoEssencialContraindicacaoRepository.findAllByOleoId(oleoEssencial.getId());

        Set<String> contraNovas = contraindicacoesList.stream()
                .map(String::trim)
                .collect(Collectors.toSet());

        List<OleoEssencialContraindicacao> contraParaRemover = oleoEssencialContraindicacaoDBList.stream()
                .filter(oec -> !contraNovas.contains(oec.getContraindicacaoId().getContraindicacao()))
                .toList();

        for (OleoEssencialContraindicacao oec : contraParaRemover) {
            oleoEssencialContraindicacaoRepository.delete(oec);
        }

        Set<String> contraDB = oleoEssencialContraindicacaoDBList.stream()
                .map(oec -> oec.getContraindicacaoId().getContraindicacao())
                .collect(Collectors.toSet());

        for (String contra : contraNovas) {
            if (!contraDB.contains(contra)) {
                Contraindicacao contraindicacao = contraindicacaoRepository.findByContraindicacao(contra);
                if (contraindicacao == null) {
                    contraindicacao = new Contraindicacao();
                    contraindicacao.setContraindicacao(contra);
                    contraindicacao = contraindicacaoRepository.save(contraindicacao);
                }
                OleoEssencialContraindicacao novoVinculo = new OleoEssencialContraindicacao();
                novoVinculo.setOleoEssencialId(oleoEssencial);
                novoVinculo.setContraindicacaoId(contraindicacao);
                oleoEssencialContraindicacaoRepository.save(novoVinculo);
            }
        }
    }
}
