package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControladorGerenciarCarteiras implements MudancaTela {
    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 15) {
            //executa os métodos aqui.
        }
    }

    @FXML
    public ComboBox<CarteiraUsuario> cboxSelecionarCarteira;
    @FXML
    public Label infoCarteiraSelecionadaLabel;
    @FXML
    public Button botaoVoltar0523;
    @FXML
    public Button botaoConfirmar0523;

    @FXML
    public void voltarBotao0523(ActionEvent actionEvent) {
        Programa.trocarTela(8);
    }

    @FXML
    public void confirmarBotao0523(ActionEvent actionEvent) {
    }

    @FXML
    public void selecionarCarteiraCbox(ActionEvent actionEvent) {
    }
}
