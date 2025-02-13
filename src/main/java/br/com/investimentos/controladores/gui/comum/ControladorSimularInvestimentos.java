package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.EnumTempo;
import br.com.investimentos.financas.EnumTipoMoeda;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class ControladorSimularInvestimentos implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 7) {
            carregarAtivosNaComboBox();
            configurarFiltroNumerico(fieldValorInicial);
            configurarFiltroNumerico(fieldAporteMensal);
            configurarFiltroInteiro(fieldPeriodo);
        }
    }

    public void initialize() {
        if (cboxSelecionarMoeda != null && cboxSelecionarTempo != null) {
            cboxSelecionarMoeda.getItems().addAll(EnumTipoMoeda.values());
            cboxSelecionarTempo.getItems().addAll(EnumTempo.values());
        }
    }

    private void carregarAtivosNaComboBox() {
        ObservableList<AtivosFinanceiros> ativos = ControladorGerenciarCarteiras.getInstancia().obterAtivos();

        if (cboxSelecionarAtivo != null && ativos != null) {
            cboxSelecionarAtivo.getItems().addAll(ativos);
            cboxSelecionarAtivo.setConverter(new StringConverter<AtivosFinanceiros>() {
                @Override
                public String toString(AtivosFinanceiros ativo) {
                    return (ativo != null) ? ativo.nomeAtivo() : "";
                }

                @Override
                public AtivosFinanceiros fromString(String nome) {
                    return cboxSelecionarAtivo.getItems().stream()
                            .filter(a -> a.nomeAtivo().equals(nome))
                            .findFirst()
                            .orElse(null);
                }
            });
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
    private LineChart<String, Number> graficoEvolucao;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private void configurarFiltroNumerico(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.|,)?\\d*")) {
                textField.setText(oldValue);
            }
        });
    }

    private void configurarFiltroInteiro(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(oldValue);
            }
        });
    }

    @FXML
    void simularInvestBotao(ActionEvent event) {
        AtivosFinanceiros ativoSelecionado = cboxSelecionarAtivo.getValue();
        EnumTempo tempo = cboxSelecionarTempo.getValue();

        try {
            double valorInicial = Double.parseDouble(fieldValorInicial.getText().replace(",", "."));
            double aporteMensal = Double.parseDouble(fieldAporteMensal.getText().replace(",", "."));
            int periodo = Integer.parseInt(fieldPeriodo.getText());

            if (ativoSelecionado != null && tempo != null) {
                double precoAtual = ativoSelecionado.getPrecoAtual();
                double precoAbertura = ativoSelecionado.getPrecoAbertura();

                if (precoAbertura > 0) {
                    int periodoAjustado = ajustarPeriodoComBaseNoTempo(tempo, periodo);

                    simularInvestimento(ativoSelecionado, valorInicial, aporteMensal, periodoAjustado, precoAtual, precoAbertura);
                } else {
                    ControladorGeral.alertaErro("Erro", "Não foi possível obter os dados do ativo selecionado.");
                }
            } else {
                ControladorGeral.alertaErro("Erro", "Selecione um ativo e um período.");
            }
        } catch (NumberFormatException e) {
            ControladorGeral.alertaErro("Erro", "Preencha apenas com números.");
            System.out.println("Erro: Certifique-se de preencher os campos corretamente.");
        }
    }

    private int ajustarPeriodoComBaseNoTempo(EnumTempo tempo, int periodo) {
        switch (tempo) {
            case Semanas:
                return periodo * 4;
            case Meses:
                return periodo;
            case Anos:
                return periodo * 12;
            default:
                return periodo;
        }
    }

    private void simularInvestimento(AtivosFinanceiros ativo, double valorInicial, double aporteMensal, int periodo, double precoAtual, double precoAbertura) {
        System.out.println("Iniciando simulação para o ativo: " + ativo.nomeAtivo());
        System.out.println("Valor inicial: "+valorInicial+", Aporte Mensal: "+aporteMensal+", Período: "+periodo);
        System.out.println("Preço Atual: "+precoAtual+", Preço Abertura: "+precoAbertura);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Evolução do Investimento");

        double saldo = valorInicial;
        double variacao = (precoAtual - precoAbertura) / precoAbertura;
        System.out.println("Variação percentual: " + variacao);

        for (int i = 1; i <= periodo; i++) {
            saldo += aporteMensal;
            saldo *= (1 + variacao);
            series.getData().add(new XYChart.Data<>(String.valueOf(i), saldo));
        }

        System.out.println("Total de pontos adicionados ao gráfico: " + series.getData().size());

        graficoEvolucao.getData().clear();
        graficoEvolucao.getData().add(series);
    }

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }
}
