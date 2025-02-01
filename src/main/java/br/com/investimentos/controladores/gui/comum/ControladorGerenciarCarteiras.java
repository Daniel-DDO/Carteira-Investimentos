package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

public class ControladorGerenciarCarteiras implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 15) {
            inicializarComboBox();

            codigoAcao.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getCodigo()));
            precoAtual.setCellValueFactory(cellData ->
                    new SimpleDoubleProperty(cellData.getValue().getPrecoAtual()).asObject());
            precoAbertura.setCellValueFactory(cellData ->
                    new SimpleDoubleProperty(cellData.getValue().getPrecoAbertura()).asObject());
            precoMaior.setCellValueFactory(cellData ->
                    new SimpleDoubleProperty(cellData.getValue().getMaiorPreco()).asObject());
            precoMenor.setCellValueFactory(cellData ->
                    new SimpleDoubleProperty(cellData.getValue().getMenorPreco()).asObject());
            moedaLocal.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getMoeda()));

            carregarDadosAtivo();

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
        infoCarteiraSelecionadaLabel.setText(infoCarteira());
    }

    @FXML
    private TableView<AtivosFinanceiros> acoesDisponiveisTable;

    @FXML
    private TableColumn<AtivosFinanceiros, String> codigoAcao;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoAtual;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoAbertura;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoMaior;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoMenor;

    @FXML
    private TableColumn<AtivosFinanceiros, String> moedaLocal;
    private static final String API_KEY1 = "PZRJA4TYHQXYDI9H";
    private static final String API_KEY2 = "5FNWTKTBXYGGMC65";
    private static final String API_KEY3 = "13BA91QBJXYKU9E6";
    private static final String[] SYMBOLS = {
            "AAPL", "GOOGL", "AMZN", "MSFT", "TSLA", "META", "NFLX", "NVDA", "INTC", "AMD",
            "SPY", "QQQ", "V", "MA", "DIS", "BA", "IBM", "CSCO", "PYPL", "CRM", "ORCL",
            "NVDA", "INTC", "ADBE", "GE", "KO", "PEP", "WMT", "HD", "MCD", "GS", "JPM",
            "C", "BAC", "VZ", "T", "XOM", "CVX", "PFE", "MRK", "JNJ", "UNH", "ABT",
            "MDT", "GILD", "AMGN", "BMY", "LLY", "HCA", "CVS", "WBA", "TGT", "LOW",
            "CAT", "DE", "NKE", "LULU", "ADP", "TMO", "AMAT", "CSX", "UPS", "FDX",
            "DHL", "SAP", "HPE", "QCOM", "MU", "SNPS", "INTU", "EXPE", "UBER", "LYFT",
            "BABA", "JD", "TCEHY", "PDD", "BIDU", "DISH", "YUM", "DHR", "ISRG", "SYK",
            "IDXX", "RMD", "MELI", "TEAM", "DOCU", "VEEV", "TWLO", "Z", "OKTA", "SHOP",
            "ZM", "SQ", "RBLX", "ROKU", "PLTR", "NET", "CRWD", "MDB", "FSLY", "TTD"
    };

    private void carregarDadosAtivo() {
        ObservableList<AtivosFinanceiros> ativosList = FXCollections.observableArrayList();
        String apiKey = API_KEY1;

        for (String symbol : SYMBOLS) {
            String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
                    + symbol + "&interval=1min&apikey=" + apiKey;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                    JsonObject timeSeries = jsonResponse.getAsJsonObject("Time Series (1min)");

                    if (timeSeries != null && timeSeries.size() > 0) {
                        JsonElement firstEntry = timeSeries.entrySet().iterator().next().getValue();

                        double precoAtual = firstEntry.getAsJsonObject().get("1. open").getAsDouble();
                        double precoAbertura = firstEntry.getAsJsonObject().get("1. open").getAsDouble();
                        double maiorPreco = firstEntry.getAsJsonObject().get("2. high").getAsDouble();
                        double menorPreco = firstEntry.getAsJsonObject().get("3. low").getAsDouble();

                        String moeda = getMoedaPorSigla(symbol);

                        AtivosFinanceiros ativo = new AtivosFinanceiros(symbol, precoAtual, precoAbertura, maiorPreco, menorPreco, moeda);
                        ativosList.add(ativo);
                    }
                } else {
                    if (responseCode == 403) {
                        System.out.println("Limite da API Key 1 atingido. Tentando com a segunda chave...");
                        apiKey = API_KEY2;
                        // Refaça a requisição com a nova chave.
                        apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
                                + symbol + "&interval=1min&apikey=" + apiKey;
                        connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                        connection.setRequestMethod("GET");

                        responseCode = connection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder response = new StringBuilder();

                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();

                            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                            JsonObject timeSeries = jsonResponse.getAsJsonObject("Time Series (1min)");

                            if (timeSeries != null && timeSeries.size() > 0) {
                                JsonElement firstEntry = timeSeries.entrySet().iterator().next().getValue();

                                double precoAtual = firstEntry.getAsJsonObject().get("1. open").getAsDouble();
                                double precoAbertura = firstEntry.getAsJsonObject().get("1. open").getAsDouble();
                                double maiorPreco = firstEntry.getAsJsonObject().get("2. high").getAsDouble();
                                double menorPreco = firstEntry.getAsJsonObject().get("3. low").getAsDouble();

                                String moeda = getMoedaPorSigla(symbol);

                                AtivosFinanceiros ativo = new AtivosFinanceiros(symbol, precoAtual, precoAbertura, maiorPreco, menorPreco, moeda);
                                ativosList.add(ativo);
                            }
                        } else {
                            System.out.println("Erro na requisição para " + symbol + " com a chave API2: " + responseCode);
                        }
                    }
                    else if (responseCode == 403) {
                        System.out.println("Limite da API Key 2 atingido. Tentando com a terceira chave...");
                        apiKey = API_KEY3;
                        apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
                                + symbol + "&interval=1min&apikey=" + apiKey;
                        connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                        connection.setRequestMethod("GET");

                        responseCode = connection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder response = new StringBuilder();

                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();

                            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                            JsonObject timeSeries = jsonResponse.getAsJsonObject("Time Series (1min)");

                            if (timeSeries != null && timeSeries.size() > 0) {
                                JsonElement firstEntry = timeSeries.entrySet().iterator().next().getValue();

                                double precoAtual = firstEntry.getAsJsonObject().get("1. open").getAsDouble();
                                double precoAbertura = firstEntry.getAsJsonObject().get("1. open").getAsDouble();
                                double maiorPreco = firstEntry.getAsJsonObject().get("2. high").getAsDouble();
                                double menorPreco = firstEntry.getAsJsonObject().get("3. low").getAsDouble();

                                String moeda = getMoedaPorSigla(symbol);

                                AtivosFinanceiros ativo = new AtivosFinanceiros(symbol, precoAtual, precoAbertura, maiorPreco, menorPreco, moeda);
                                ativosList.add(ativo);
                            }
                        } else {
                            System.out.println("Erro na requisição para " + symbol + " com a chave API3: " + responseCode);
                        }
                    } else {
                        System.out.println("Erro na requisição para " + symbol + ": " + responseCode);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        acoesDisponiveisTable.setItems(ativosList);
    }


    private String getMoedaPorSigla(String sigla) {
        switch (sigla) {
            default:
                return "USD";
        }
    }


    @FXML
    private TableView<?> mihasAcoesTable;

    @FXML
    private TableColumn<?, ?> codigoMinhasAcoes;

    @FXML
    private TableColumn<?, ?> precoMedioMinhasAcoes;

    @FXML
    private TableColumn<?, ?> quantidadeMinhasAcoes;

    @FXML
    private Button comprarBotao;

    @FXML
    private Button venderBotao;

    @FXML
    void botaoComprar(ActionEvent event) {
        abrirCompraVenda();
    }

    @FXML
    void botaoVender(ActionEvent event) {
        abrirCompraVenda();
    }

    private Stage novaJanela;

    public void abrirCompraVenda() {
        if (novaJanela != null && novaJanela.isShowing()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/investimentos/controladores/confirmar-compra-venda.fxml"));
            Parent root = loader.load();
            novaJanela = new Stage();
            novaJanela.setTitle("Digite a quantidade:");

            Scene sceneNovaJanela = new Scene(root, 400, 250);
            novaJanela.setResizable(false);
            novaJanela.setScene(sceneNovaJanela);
            novaJanela.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Tela compra ou venda

    @FXML
    private Label compraVendaLabel;

    @FXML
    private Button confirmarCompraVendaBotao;

    @FXML
    private TextField quantidadeCompVendField;

    @FXML
    void botaoConfirmarCompraVenda(ActionEvent event) {

    }

    //final dessa tela extra


    public String infoCarteira() {
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira.getSelectionModel().getSelectedItem();
        String informacoes;

        if (carteiraSelecionada != null) {
            informacoes = carteiraSelecionada.exibirNoGerenciarCarteiras();
            infoCarteiraSelecionadaLabel.setText(informacoes);
        } else {
            informacoes = "Selecione uma carteira para verificar as informações.";
        }
        return informacoes;
    }


    public void visualizarCarteirasCbox() {
        ArrayList<CarteiraUsuario> carteiraUsuarios = ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());

        boolean temCarteira = false;

        for (int i = 0; i < carteiraUsuarios.size(); i++) {
            if (carteiraUsuarios.get(i) != null) {
                if (carteiraUsuarios.get(i).getUsuario().getNomeUsuario().equals(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario())) {
                    System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario());
                    System.out.println(carteiraUsuarios.get(i).getUsuario().getNomeUsuario());

                    for (int a = 0; a < carteiraUsuarios.size(); a++) {
                        if (carteiraUsuarios.get(a) != null) {
                            if (carteiraUsuarios.get(a).getUsuario().getNomeUsuario().equals(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario())) {
                                System.out.println(carteiraUsuarios.get(a));
                            }
                        }
                    }
                    temCarteira = true;

                    if (cboxSelecionarCarteira != null) {
                        cboxSelecionarCarteira.getItems().clear();
                        cboxSelecionarCarteira.setPromptText("Selecione uma carteira");
                        cboxSelecionarCarteira.getItems().addAll(carteiraUsuarios);
                    }

                }
            }
        }

        if (!temCarteira) {
            System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario()+" não tem carteiras.");
        }
    }

    public void inicializarComboBox() {
        if (UsuarioLogado.getInstancia().getUsuarioComum() != null) {
            cboxSelecionarCarteira.getItems().clear();
            visualizarCarteirasCbox();
        }
    }
}
