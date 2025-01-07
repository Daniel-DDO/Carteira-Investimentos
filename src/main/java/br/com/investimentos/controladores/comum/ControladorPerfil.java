package br.com.investimentos.controladores.comum;

import br.com.investimentos.controladores.ControladorGeral;
import br.com.investimentos.controladores.Programa;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.TipoConta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

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
        ControladorGeral.alertaConfirmacao("Conta excluída", "A sua conta foi excluída e tudo associado a ela também foi deletado.");
        RepositorioContas.getInstancia().excluirConta(UsuarioLogado.getInstancia().getUsuarioComum());
        Programa.trocarTela(1);
    }


}
