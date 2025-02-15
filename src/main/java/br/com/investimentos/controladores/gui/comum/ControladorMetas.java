package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorMetas {

    @FXML
    private Button botaoConfirmar053;

    @FXML
    private Button botaoVoltar053;

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private Button criarNovasMetasBotao;

    @FXML
    private Label informacoesMetaLabel;

    @FXML
    private Label statusMetaLabel;

    @FXML
    void botaoCriarNovasMetas(ActionEvent event) {

    }

    @FXML
    void confirmarBotao053(ActionEvent event) {

    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {

    }

    @FXML
    void voltarBotao053(ActionEvent event) {
        trocarTela(5);
    }

}
