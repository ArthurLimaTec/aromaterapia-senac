package org.general.service;

import org.general.model.*;
import org.general.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
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
            validarItens(oleoEssencial, indicacoesList, contraindicacoesList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
        }
    }

    public void validarItens(OleoEssencial oleoEssencial, List<Indicacao> indicacoesList, List<Contraindicacao> contraindicacoesList) {

        boolean oleoExiste;

        try{
            if(oleoEssencialRepository.findByNome(oleoEssencial.getNome()) == null){oleoEssencialRepository.save(oleoEssencial);}

            for(Indicacao value : indicacoesList){
                if(indicacaoRepository.findBySintoma(value.getSintoma()) == null) {indicacaoRepository.save(value);}
            }

            for(Contraindicacao value : contraindicacoesList){
                if(contraindicacaoRepository.findByContraindicacao(value.getContraindicacao()) == null) {contraindicacaoRepository.save(value);}
            }

            cadastrarVinculos(oleoEssencial.getNome(), indicacoesList, contraindicacoesList);

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
        }

    }

    public void cadastrarVinculos(String nome, List<Indicacao> indicacoesList, List<Contraindicacao> contraindicacoesList){

        try {
            OleoEssencial oleo = oleoEssencialRepository.findByNome(nome);

            for (int i = 0; i < indicacoesList.size(); i++){

                Indicacao indicacaoSintoma = indicacoesList.get(i);
                Indicacao indicacao = indicacaoRepository.findBySintoma(indicacaoSintoma.getSintoma());

                OleoEssencialIndicacao entidadeIndicacao = oleoEssencialIndicacaoRepository.findMatchByIndicacaoId(oleo.getId(), indicacao.getId());

                if(entidadeIndicacao == null){
                    OleoEssencialIndicacao oleoEssencialIndicacao = new OleoEssencialIndicacao();
                    oleoEssencialIndicacao.setOleoEssencialId(oleo);
                    oleoEssencialIndicacao.setIndicacaoId(indicacao);

                    oleoEssencialIndicacaoRepository.save(oleoEssencialIndicacao);
                }
            }


            for (int i = 0; i < contraindicacoesList.size(); i++){

                Contraindicacao contraindicacaoString = contraindicacoesList.get(i);
                Contraindicacao contraindicacao = contraindicacaoRepository.findByContraindicacao(contraindicacaoString.getContraindicacao());

                OleoEssencialContraindicacao entidadeContraindicacao = oleoEssencialContraindicacaoRepository.findMatchByContraindicacaoId(oleo.getId(), contraindicacao.getId());

                if(entidadeContraindicacao == null){
                    OleoEssencialContraindicacao oleoEssencialContraindicacao = new OleoEssencialContraindicacao();
                    oleoEssencialContraindicacao.setOleoEssencialId(oleo);
                    oleoEssencialContraindicacao.setContraindicacaoId(contraindicacao);

                    oleoEssencialContraindicacaoRepository.save(oleoEssencialContraindicacao);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar vínculos.");
            throw e;
        }

    }




}
