package br.com.investimentos.controladores.comum;

import br.com.investimentos.controladores.Programa;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.usuarios.TipoConta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorPerfil {

    @FXML
    private Button botaoExcluirConta;

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    public Label informacoesContaComum;

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }

    @FXML
    void excluirContaBotao(ActionEvent event) {

    }


}
