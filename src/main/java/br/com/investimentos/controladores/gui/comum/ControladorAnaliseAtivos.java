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

import java.util.ArrayList;
import java.util.List;

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
        seriePrecoAtual.setName("Preço Atual - Exibição na moeda padrão dos ativos (USD).");

        AtivosFinanceiros[] ativosFinanceirosDoArquivo = RepositorioAtivos.lerAtivos();
        String[] cores = {"#ff5733", "#33ff57", "#3357ff", "#f3ff33", "#ff33f3", "#33f3ff", "#ff8c00", "#8c00ff", "#00ff8c"};
        int corIndex = 0;

        List<String> categorias = new ArrayList<>();

        for (AtivosFinanceiros ativo : ativosFinanceirosDoArquivo) {
            if (ativo != null) {
                XYChart.Data<String, Number> dado = new XYChart.Data<>(ativo.getCodigo(), ativo.getPrecoAtual());
                seriePrecoAtual.getData().add(dado);
                categorias.add(ativo.getCodigo());

                String cor = cores[corIndex % cores.length];
                dado.nodeProperty().addListener((obs, oldNode, newNode) -> {
                    if (newNode != null) {
                        newNode.setStyle("-fx-bar-fill: " + cor + ";");
                    }
                });

                corIndex++;
            }
        }

        analiseAtivosGrafico.getData().clear();
        analiseAtivosGrafico.getData().add(seriePrecoAtual);
        xAxis.setTickLabelGap(10);
        xAxis.setAutoRanging(false);
        xAxis.setCategories(FXCollections.observableArrayList(categorias));
    }

}
