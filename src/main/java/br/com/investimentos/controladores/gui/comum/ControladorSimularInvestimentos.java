package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.EnumTempo;
import br.com.investimentos.financas.EnumTipoMoeda;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControladorSimularInvestimentos {

    public void initialize() {
        if (cboxSelecionarMoeda != null && cboxSelecionarTempo != null) {
            cboxSelecionarMoeda.getItems().addAll(EnumTipoMoeda.values());
            cboxSelecionarTempo.getItems().addAll(EnumTempo.values());
        }
    }

    private void carregarAtivosNaComboBox() {
        ObservableList<AtivosFinanceiros> ativos = ControladorGerenciarCarteiras.getInstancia().obterAtivos(cboxSelecionarMoeda.getValue());
        if (cboxSelecionarAtivo != null && ativos != null) {
            cboxSelecionarAtivo.getItems().addAll(ativos);
        }
    }

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    private Button botaoSimularInvest;

    @FXML
    private ComboBox<AtivosFinanceiros> cboxSelecionarAtivo;

    @FXML
    private ComboBox<EnumTipoMoeda> cboxSelecionarMoeda;

    @FXML
    private ComboBox<EnumTempo> cboxSelecionarTempo;

    @FXML
    private TextField fieldAporteMensal;

    @FXML
    private TextField fieldPeriodo;

    @FXML
    private TextField fieldValorInicial;

    @FXML
    private LineChart<Number, Number> graficoEvolucao;

    @FXML
    void simularInvestBotao(ActionEvent event) {
        EnumTipoMoeda moedaSelecionada = cboxSelecionarMoeda.getValue();

        if (moedaSelecionada != null) {
            ObservableList<AtivosFinanceiros> ativos = ControladorGerenciarCarteiras.getInstancia()
                    .obterAtivos(moedaSelecionada);

            cboxSelecionarAtivo.getItems().clear();
            cboxSelecionarAtivo.getItems().addAll(ativos);
        }

        AtivosFinanceiros ativoSelecionado = cboxSelecionarAtivo.getValue();
        EnumTempo tempo = cboxSelecionarTempo.getValue();
        double valorInicial = Double.parseDouble(fieldValorInicial.getText());
        double aporteMensal = Double.parseDouble(fieldAporteMensal.getText());
        int periodo = Integer.parseInt(fieldPeriodo.getText());

        if (ativoSelecionado != null && tempo != null) {
            simularInvestimento(ativoSelecionado, valorInicial, aporteMensal, periodo);
        }
    }

    private void simularInvestimento(AtivosFinanceiros ativo, double valorInicial, double aporteMensal, int periodo) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Evolução do Investimento");

        double saldo = valorInicial;
        double variacao = (ativo.getPrecoAtual() - ativo.getPrecoAbertura()) / ativo.getPrecoAbertura();

        for (int i = 1; i <= periodo; i++) {
            saldo += aporteMensal;
            saldo *= (1 + variacao);
            series.getData().add(new XYChart.Data<>(i, saldo));
        }

        graficoEvolucao.getData().clear();
        graficoEvolucao.getData().add(series);
    }

    @FXML
    void confirmarBotao05(ActionEvent event) {
        carregarAtivosNaComboBox();
    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }
}
