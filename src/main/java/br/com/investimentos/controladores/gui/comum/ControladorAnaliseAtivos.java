package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorAnaliseAtivos implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 11) {
            carregarDadosAtivo();
        }
    }

    @FXML
    private BarChart<String, Number> analiseAtivosGrafico;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button botaoConfirmar055;

    @FXML
    private Button botaoVoltar055;

    @FXML
    void confirmarBotao055() {

    }

    @FXML
    void voltarBotao055() {
        trocarTela(5);
    }

    private void carregarDadosAtivo() {
        ObservableList<XYChart.Series<String, Number>> dadosGrafico = FXCollections.observableArrayList();
        XYChart.Series<String, Number> seriePrecoAtual = new XYChart.Series<>();
        seriePrecoAtual.setName("Preço Atual");

        AtivosFinanceiros[] ativosFinanceirosDoArquivo = RepositorioAtivos.lerAtivos();

        for (AtivosFinanceiros ativo : ativosFinanceirosDoArquivo) {
            if (ativo != null) {
                seriePrecoAtual.getData().add(new XYChart.Data<>(ativo.getCodigo(), ativo.getPrecoAtual()));
            }
        }

        analiseAtivosGrafico.getData().clear();
        analiseAtivosGrafico.getData().add(seriePrecoAtual);
    }

}
