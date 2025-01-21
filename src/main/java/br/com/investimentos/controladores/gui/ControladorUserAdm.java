package br.com.investimentos.controladores.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorUserAdm {

    @FXML
    private Button botaoConfirmar06;

    @FXML
    private Button botaoVoltar06;

    @FXML
    void confirmarBotao06(ActionEvent event) {

    }

    @FXML
    void voltarBotao06(ActionEvent event) {
        Programa.trocarTela(1);
    }
}
