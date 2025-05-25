package org.general.telas;

import org.general.model.TableView;

import javax.swing.*;
import java.util.function.Consumer;

public class JanelaEditar extends JDialog {
    private JTextField txtOleo;
    private JTextField txtIndicacoes;
    private JTextField txtContraindicacoes;
    private boolean confirmado = false;


    public JanelaEditar(JFrame parent, String oleo, String indicacoes, String contraindicacoes, Consumer<TableView> aoSalvar) {
        super(parent, "Editar Linha", true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        txtOleo = new JTextField(oleo, 30);
        txtIndicacoes = new JTextField(indicacoes, 30);
        txtContraindicacoes = new JTextField(contraindicacoes, 30);

        add(new JLabel("Óleo Essencial:"));
        add(txtOleo);
        add(new JLabel("Indicações:"));
        add(txtIndicacoes);
        add(new JLabel("Contraindicações:"));
        add(txtContraindicacoes);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Chama a função recebida, passando os dados editados
            aoSalvar.accept(new TableView(
                    txtOleo.getText(),
                    txtIndicacoes.getText(),
                    txtContraindicacoes.getText()
            ));
            setVisible(false);
            dispose();
        });
        add(btnSalvar);

        pack();
        setLocationRelativeTo(parent);
    }

    public String getOleo() {
        return txtOleo.getText();
    }

    public String getIndicacoes() {
        return txtIndicacoes.getText();
    }

    public String getContraindicacoes() {
        return txtContraindicacoes.getText();
    }
}
