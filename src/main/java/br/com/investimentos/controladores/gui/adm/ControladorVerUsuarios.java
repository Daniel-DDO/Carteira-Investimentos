package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorVerUsuarios implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 23) {
            System.out.println("23");
        }
    }

    @FXML
    private Button botaoConfirmar063;

    @FXML
    private Button botaoVoltar063;

    @FXML
    private ComboBox<UsuarioComum> cboxSelecionarUser;

    @FXML
    private Label infoUserSelecionadoLabel;

    @FXML
    void confirmarBotao063(ActionEvent event) {

    }

    @FXML
    void selecionarUserCbox(ActionEvent event) {

    }

    @FXML
    void voltarBotao063(ActionEvent event) {
        trocarTela(6);
    }

}
