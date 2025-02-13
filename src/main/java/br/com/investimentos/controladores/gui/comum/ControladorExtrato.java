package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.MudancaTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorExtrato implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 20) {
            System.out.println("T20");
        }
    }

    @FXML
    private Button botaoConfirmar0524;

    @FXML
    private Button botaoVoltar0524;

    @FXML
    private ComboBox<?> cboxSelecionarCarteira;

    @FXML
    private TableColumn<?, ?> dataColuna;

    @FXML
    private TableColumn<?, ?> informacoesColuna;

    @FXML
    private TableColumn<?, ?> operacoesColuna;

    @FXML
    private TableView<?> tableExtrato;

    @FXML
    void confirmarBotao0524(ActionEvent event) {

    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {

    }

    @FXML
    void voltarBotao05224(ActionEvent event) {
        trocarTela(8);
    }

}
