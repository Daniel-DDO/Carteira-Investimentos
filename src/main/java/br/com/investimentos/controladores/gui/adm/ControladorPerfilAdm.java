package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorPerfilAdm implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 16) {
            //passar info
        }
    }

    @FXML
    private Button botaoConfirmar06;

    @FXML
    private Button botaoExcluirConta;

    @FXML
    private Button botaoVoltar06;

    @FXML
    public Label informacoesContaAdm;

    @FXML
    void confirmarBotao06(ActionEvent event) {

    }

    @FXML
    void excluirContaBotao(ActionEvent event) {

    }

    @FXML
    void voltarBotao06(ActionEvent event) {
        Programa.trocarTela(6);
    }

}
