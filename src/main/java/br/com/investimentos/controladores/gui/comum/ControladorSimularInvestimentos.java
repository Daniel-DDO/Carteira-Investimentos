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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            cboxSelecionarAtivo.getItems().clear();
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

    private int ajustarPeriodoComBaseNoTempo(EnumTempo tempo, int periodo) {
        switch (tempo) {
            case Semanas:
                return periodo / 4;
            case Meses:
                return periodo;
            case Anos:
                return periodo * 12;
            default:
                return periodo;
        }
    }

    private double converterMoeda(double valor, EnumTipoMoeda moedaOrigem, EnumTipoMoeda moedaDestino) {
        if (moedaOrigem == null || moedaDestino == null) {
            System.out.println("Erro: Moeda de origem ou destino inválida.");
            return valor;
        }

        if (moedaOrigem == moedaDestino) {
            return valor;
        }

        double taxaCambio = getTaxaCambio(moedaOrigem.toString(), moedaDestino.toString());
        System.out.println("Taxa câmbio: "+taxaCambio);

        if (taxaCambio > 0) {
            return valor / taxaCambio;
        } else {
            System.out.println("Erro ao obter taxa de câmbio. Mantendo valor original.");
            return valor;
        }
    }

    private double getTaxaCambio(String moedaOrigem, String moedaDestino) {
        System.out.println("Consultando taxa de câmbio: " + moedaOrigem + " para " + moedaDestino);

        try {
            String url = "https://economia.awesomeapi.com.br/last/" + moedaOrigem + "-" + moedaDestino;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                String key = moedaOrigem + moedaDestino;

                if (jsonObject.has(key)) {
                    return jsonObject.getJSONObject(key).getDouble("bid");
                } else {
                    System.out.println("Erro: Chave não encontrada no JSON retornado.");
                }
            } else {
                System.out.println("Erro na conexão com a API. Código: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    @FXML
    void simularInvestBotao(ActionEvent event) {
        AtivosFinanceiros ativoSelecionado = cboxSelecionarAtivo.getValue();
        EnumTempo tempo = cboxSelecionarTempo.getValue();
        EnumTipoMoeda moedaOrigem;
        EnumTipoMoeda moedaDestino = cboxSelecionarMoeda.getValue();

        if (ativoSelecionado == null || tempo == null) {
            ControladorGeral.alertaErro("Erro", "Selecione um ativo e um período.");
            return;
        }

        try {
            moedaOrigem = EnumTipoMoeda.valueOf(ativoSelecionado.getMoeda());
        } catch (IllegalArgumentException | NullPointerException e) {
            ControladorGeral.alertaErro("Erro", "Moeda do ativo inválida.");
            return;
        }

        if (moedaDestino == null) {
            ControladorGeral.alertaErro("Erro", "Selecione uma moeda de destino.");
            return;
        }

        try {
            double valorInicial = Double.parseDouble(fieldValorInicial.getText().replace(",", "."));
            double aporteMensal = Double.parseDouble(fieldAporteMensal.getText().replace(",", "."));
            int periodo = Integer.parseInt(fieldPeriodo.getText());

            double precoAtual = ativoSelecionado.getPrecoAtual();
            double precoAbertura = ativoSelecionado.getPrecoAbertura();

            if (precoAbertura > 0) {
                double valorInicialConvertido = converterMoeda(valorInicial, moedaOrigem, moedaDestino);
                double aporteMensalConvertido = converterMoeda(aporteMensal, moedaOrigem, moedaDestino);
                double precoAtualConvertido = converterMoeda(precoAtual, moedaOrigem, moedaDestino);
                double precoAberturaConvertido = converterMoeda(precoAbertura, moedaOrigem, moedaDestino);

                int periodoAjustado = ajustarPeriodoComBaseNoTempo(tempo, periodo);

                simularInvestimento(ativoSelecionado, valorInicialConvertido, aporteMensalConvertido, periodoAjustado, precoAtualConvertido, precoAberturaConvertido, moedaDestino);
            } else {
                ControladorGeral.alertaErro("Erro", "Não foi possível obter os dados do ativo selecionado.");
            }
        } catch (NumberFormatException e) {
            ControladorGeral.alertaErro("Erro", "Preencha apenas com números.");
            System.out.println("Erro: Certifique-se de preencher os campos corretamente.");
        }
    }

    private void simularInvestimento(AtivosFinanceiros ativo, double valorInicial, double aporteMensal, int periodo, double precoAtual, double precoAbertura, EnumTipoMoeda moedaSelecionada) {
        if (moedaSelecionada == null) {
            ControladorGeral.alertaErro("Erro", "Selecione uma moeda.");
            return;
        }

        System.out.println("Iniciando simulação para o ativo: "+ativo.nomeAtivo());
        System.out.println("Moeda selecionada: "+moedaSelecionada);
        System.out.println("Valor inicial: "+valorInicial+", Aporte Mensal: "+aporteMensal);
        System.out.println("Preço Atual: "+precoAtual+", Preço Abertura: "+precoAbertura);

        valorInicial = converterMoeda(valorInicial, moedaSelecionada, moedaSelecionada);
        aporteMensal = converterMoeda(aporteMensal, moedaSelecionada, moedaSelecionada);
        precoAtual = converterMoeda(precoAtual, moedaSelecionada, moedaSelecionada);
        precoAbertura = converterMoeda(precoAbertura, moedaSelecionada, moedaSelecionada);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Evolução do Investimento em "+ativo.getMoeda());

        double saldo = valorInicial;
        double variacao = (precoAtual - precoAbertura) / precoAbertura;
        System.out.println("Variação percentual: " + variacao);

        for (int i = 1; i <= periodo; i++) {
            saldo += aporteMensal;
            saldo *= (1 + variacao);
            series.getData().add(new XYChart.Data<>(String.valueOf(i), saldo));
        }

        System.out.println("Total de pontos adicionados ao gráfico: " + series.getData().size());

        graficoEvolucao.getData().add(series);

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%.2f", object.doubleValue());
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string);
            }
        });
    }

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        graficoEvolucao.getData().clear();
        fieldAporteMensal.clear();
        fieldPeriodo.clear();
        fieldValorInicial.clear();
        Programa.trocarTela(5);
    }
}
