package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.Programa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControladorSimularInvestimentos {

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    private Button botaoSimularInvest;

    @FXML
    private ComboBox<?> cboxSelecionarAtivo;

    @FXML
    private ComboBox<?> cboxSelecionarMoeda;

    @FXML
    private ComboBox<?> cboxSelecionarTempo;

    @FXML
    private TextField fieldAporteMensal;

    @FXML
    private TextField fieldPeriodo;

    @FXML
    private TextField fieldValorInicial;

    @FXML
    private LineChart<?, ?> graficoEvolucao;

    @FXML
    void simularInvestBotao(ActionEvent event) {

    }

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }
}
