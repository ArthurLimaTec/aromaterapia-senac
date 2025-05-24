package org.general.service;

import org.general.model.*;
import org.general.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroService {

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


    public void tratarCampos(String nome, List<String> indicacoes, List<String> contraindicacoes){

        OleoEssencial oleoEssencial = new OleoEssencial();
        oleoEssencial.setNome(nome);

        indicacoesList = new ArrayList<>();
        contraindicacoesList = new ArrayList<>();

        for (int i = 0; i < indicacoes.toArray().length; i++){
            Indicacao indicacao = new Indicacao();
            indicacao.setSintoma(indicacoes.get(i));

            indicacoesList.add(indicacao);
        }

        for (int i = 0; i < contraindicacoes.toArray().length; i++){
            Contraindicacao contraindicacao = new Contraindicacao();
            contraindicacao.setContraindicacao(contraindicacoes.get(i));

            contraindicacoesList.add(contraindicacao);
        }

        try {

            cadastrarOleo(oleoEssencial);
            cadastrarIndicacoes(indicacoesList);
            cadastrarContraindicacoes(contraindicacoesList);
            cadastrarVinculos(oleoEssencial.getNome(), indicacoesList, contraindicacoesList);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
        }
    }

    public void cadastrarOleo(OleoEssencial oleoEssencial){

        try {
            oleoEssencialRepository.save(oleoEssencial);

        }catch(Exception e){
            throw e;
        }
    }

    public void cadastrarIndicacoes(List<Indicacao> indicacoesList) {

        for (Indicacao value : indicacoesList) {
            try {
                indicacaoRepository.save(value);

            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void cadastrarContraindicacoes(List<Contraindicacao> contraindicacoesList){

        for (Contraindicacao contraindicacao : contraindicacoesList) {
            try {
                contraindicacaoRepository.save(contraindicacao);

            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void cadastrarVinculos(String nome, List<Indicacao> indicacoesList, List<Contraindicacao> contraindicacoesList){

        try {
            OleoEssencial oleoId = oleoEssencialRepository.findByNome(nome);

            for (int i = 0; i < indicacoesList.size(); i++){

                Indicacao indicacao = indicacoesList.get(i);

                Indicacao indicacaoId = indicacaoRepository.findBySintoma(indicacao.getSintoma());

                OleoEssencialIndicacao oleoEssencialIndicacao = new OleoEssencialIndicacao();
                oleoEssencialIndicacao.setOleoEssencialId(oleoId);
                oleoEssencialIndicacao.setIndicacaoId(indicacaoId);

                oleoEssencialIndicacaoRepository.save(oleoEssencialIndicacao);

            }


            for (int i = 0; i < contraindicacoesList.size(); i++){

                Contraindicacao contraindicacao = contraindicacoesList.get(i);

                Contraindicacao contraindicacaoId = contraindicacaoRepository.findByContraindicacao(contraindicacao.getContraindicacao());

                OleoEssencialContraindicacao oleoEssencialContraindicacao = new OleoEssencialContraindicacao();
                oleoEssencialContraindicacao.setOleoEssencialId(oleoId);
                oleoEssencialContraindicacao.setContraindicacaoId(contraindicacaoId);

                oleoEssencialContraindicacaoRepository.save(oleoEssencialContraindicacao);
            }

        } catch (Exception e) {
            throw e;
        }

    }


    private DefaultTableModel criarModeloTabela(List<OleoEssencial> oleosEssenciais) {
        // Define os nomes das colunas
        String[] colunas = {"Oleo Essencial", "Indicações", "Contraindicações"};

        // Cria o modelo de tabela com as colunas definidas
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        // Preenche o modelo com os dados dos podcasts
        for (OleoEssencial oleoEssencial : oleosEssenciais) {
            Object[] linha = {
                    oleoEssencial.getId(),
                    oleoEssencial.getNome()
            };
            model.addRow(linha);
        }

        return model;
    }

}
