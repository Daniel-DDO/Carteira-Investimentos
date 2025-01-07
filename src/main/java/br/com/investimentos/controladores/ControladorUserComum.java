package br.com.investimentos.controladores;

import br.com.investimentos.controladores.comum.ControladorPerfil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorUserComum {

    @FXML
    private Button botaoFundosImobiliarios;

    @FXML
    private Button botaoMeuPerfil;

    @FXML
    private Button botaoMinhasCarterias;

    @FXML
    private Button botaoProjecoesRentab;

    @FXML
    private Button botaoRenda;

    @FXML
    private Button botaoSimularInvestimentos;

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(1);
    }

    @FXML
    void fundosImobiliariosBotao(ActionEvent event) {
        Programa.trocarTela(10);
    }

    @FXML
    void meuPerfilBotao(ActionEvent event) {
        Programa.trocarTela(12);
        Programa.inserirTextoLabel(Programa.getControladorPerfil().informacoesContaComum, "Teste do DDO");
    }

    @FXML
    void minhasCarteirasBotao(ActionEvent event) {
        Programa.trocarTela(8);
    }

    @FXML
    void projecoesRentabBotao(ActionEvent event) {
        Programa.trocarTela(9);
    }

    @FXML
    void rendaBotao(ActionEvent event) {
        Programa.trocarTela(11);
    }

    @FXML
    void simularInvestimentosBotao(ActionEvent event) {
        Programa.trocarTela(7);
    }
}
