package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.Programa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorSimularInvestimentos {

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }
}
