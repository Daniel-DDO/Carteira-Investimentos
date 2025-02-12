package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.repositorios.RepositorioAtivos;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import com.google.gson.JsonArray;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ControladorGerenciarCarteiras implements MudancaTela {

    private boolean compra = false;

    private static ControladorGerenciarCarteiras controladorGerenciarCarteiras;

    public static ControladorGerenciarCarteiras getInstancia() {
        if (controladorGerenciarCarteiras == null){
            controladorGerenciarCarteiras = new ControladorGerenciarCarteiras();
        }
        return controladorGerenciarCarteiras;
    }

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

            codigoMinhasAcoes.setCellValueFactory(cellData -> {
                AtivosFinanceiros ativo = cellData.getValue();
                String codigo = (ativo != null && ativo.getCodigo() != null) ? ativo.getCodigo() : "N/A";
                return new SimpleStringProperty(codigo);
            });

            quantidadeMinhasAcoes.setCellValueFactory(cellData -> {
                AtivosFinanceiros ativo = cellData.getValue();
                int quantidade = (ativo != null) ? ativo.getQuantidade() : 0;
                return new SimpleIntegerProperty(quantidade).asObject();
            });

            precoMedioMinhasAcoes.setCellFactory(column -> new TableCell<AtivosFinanceiros, Double>() {
                @Override
                protected void updateItem(Double precoMedio, boolean empty) {
                    super.updateItem(precoMedio, empty);
                    if (empty || precoMedio == null) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", precoMedio));
                    }
                }
            });

            precoMedioMinhasAcoes.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<>(cellData.getValue().getPrecoMedio())
            );

            moedaMinhasAcoes.setCellValueFactory(cellData -> {
                AtivosFinanceiros ativo = cellData.getValue();
                String moeda = (ativo != null && ativo.getMoeda() != null) ? ativo.getMoeda() : "USD";
                return new SimpleStringProperty(moeda);
            });

            carregarDadosAtivo();
            atualizarInformacoesTela();
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
    public Label informacoesCambioLabel;
    @FXML
    public TextField quantidadeCompVendField;
    @FXML
    public Label compraVendaLabel;

    @FXML
    public void voltarBotao0523(ActionEvent actionEvent) {
        Programa.trocarTela(8);
    }

    @FXML
    public void confirmarBotao0523(ActionEvent actionEvent) {
        botaoConfirmarCompraVenda(actionEvent);
    }

    @FXML
    public void selecionarCarteiraCbox(ActionEvent actionEvent) {
        infoCarteiraSelecionadaLabel.setText(infoCarteira());
    }

    @FXML
    public TableView<AtivosFinanceiros> acoesDisponiveisTable;

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

    private static final String API_KEY1 = "176d06fe91ded98c0dbd428b9fc1d45311bf34ea";
    private static final String API_KEY2 = "d24000f8cfa3e553854f164e1ffe7308eacd7be4";
    private static final String[] SYMBOLS = {
            "AAPL"
    };

    /*
    private static final String[] SYMBOLS = {
            "AAPL", "GOOGL", "AMZN", "MSFT", "TSLA", "META", "NFLX", "NVDA", "INTC", "AMD",
            "SPY", "QQQ", "V", "MA", "DIS", "BA", "IBM", "CSCO", "PYPL", "CRM", "ORCL",
            "NVDA", "INTC", "ADBE", "GE", "KO", "PEP", "WMT", "HD", "MCD", "GS", "JPM",
            "C", "BAC", "VZ", "T", "XOM", "CVX", "PFE", "MRK", "JNJ", "UNH", "ABT",
            "MDT", "GILD", "AMGN", "BMY", "LLY", "HCA", "CVS", "WBA", "TGT", "LOW",
            "CAT", "DE", "NKE", "LULU", "ADP", "TMO", "AMAT", "CSX", "UPS", "FDX",
            "DHL", "SAP", "HPE", "QCOM", "MU", "SNPS", "INTU", "EXPE", "UBER", "LYFT",
            "BABA", "JD", "TCEHY", "PDD", "BIDU", "DISH", "YUM", "DHR", "ISRG", "SYK",
            "IDXX", "RMD", "MELI", "TEAM"
    };
     */

    private String apiKeyEmUso = API_KEY1;

    @FXML
    public void carregarDadosAtivo() {
        ObservableList<AtivosFinanceiros> ativosList = FXCollections.observableArrayList();
        AtivosFinanceiros[] ativosFinanceirosDoArquivo = RepositorioAtivos.lerAtivos();

        for (String symbol : SYMBOLS) {
            String apiUrl = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?token=" + apiKeyEmUso;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("Requisitando " + symbol + " com código de resposta: " + responseCode);

                if (responseCode == 429) {
                    System.out.println("Limite de requisições atingido para " + apiKeyEmUso);
                    apiKeyEmUso = (apiKeyEmUso.equals(API_KEY1)) ? API_KEY2 : API_KEY1;
                    apiUrl = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?token=" + apiKeyEmUso;
                    connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                    connection.setRequestMethod("GET");
                    responseCode = connection.getResponseCode();
                    System.out.println("Tentando novamente com a chave " + apiKeyEmUso + " para " + symbol);
                }

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JsonArray jsonResponse = JsonParser.parseString(response.toString()).getAsJsonArray();

                    if (jsonResponse.size() > 0) {
                        JsonObject firstEntry = jsonResponse.get(0).getAsJsonObject();

                        double precoAtual = firstEntry.get("close").getAsDouble();
                        double precoAbertura = firstEntry.get("open").getAsDouble();
                        double maiorPreco = firstEntry.get("high").getAsDouble();
                        double menorPreco = firstEntry.get("low").getAsDouble();

                        String moeda = getMoedaPorSigla(symbol);

                        AtivosFinanceiros novoAtivo = new AtivosFinanceiros(symbol, precoAtual, precoAbertura, maiorPreco, menorPreco, moeda);

                        boolean ativoAtualizado = false;
                        for (int i = 0; i < ativosFinanceirosDoArquivo.length; i++) {
                            if (ativosFinanceirosDoArquivo[i] != null &&
                                    ativosFinanceirosDoArquivo[i].getCodigo().equals(novoAtivo.getCodigo())) {
                                ativosFinanceirosDoArquivo[i] = novoAtivo;
                                ativoAtualizado = true;
                                System.out.println("Ativo atualizado: " + novoAtivo.getCodigo());
                                break;
                            }
                        }

                        if (!ativoAtualizado) {
                            for (int i = 0; i < ativosFinanceirosDoArquivo.length; i++) {
                                if (ativosFinanceirosDoArquivo[i] == null) {
                                    ativosFinanceirosDoArquivo[i] = novoAtivo;
                                    System.out.println("Novo ativo adicionado: " + novoAtivo.getCodigo());
                                    break;
                                }
                            }
                        }

                        ativosList.add(novoAtivo);
                    } else {
                        System.out.println("Sem dados para " + symbol);
                    }
                } else {
                    System.out.println("Erro na requisição para " + symbol + ": " + responseCode);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a requisição para " + symbol);
                e.printStackTrace();
            }
        }

        if (ativosList.isEmpty()) {
            System.out.println("Carregando dados do arquivo, já que não conseguimos dados da API.");
            for (AtivosFinanceiros ativoArquivo : ativosFinanceirosDoArquivo) {
                if (ativoArquivo != null) {
                    ativosList.add(ativoArquivo);
                    System.out.println("Ativo carregado do arquivo: " + ativoArquivo.getCodigo());
                }
            }
        } else {
            System.out.println("Atualizando o arquivo com os novos dados.");
            RepositorioAtivos.atualizarAtivos(ativosFinanceirosDoArquivo);
        }

        System.out.println("Dados carregados: " + ativosList.size());
        acoesDisponiveisTable.setItems(ativosList);
    }

    private void carregarAtivosDaCarteira(CarteiraUsuario carteiraUsuario) {
        ObservableList<AtivosFinanceiros> ativosCarteiraList = FXCollections.observableArrayList();

        List<AtivosFinanceiros> ativosNaCarteira = Optional.ofNullable(carteiraUsuario.getAtivosFinanceiros())
                .map(Arrays::asList)
                .orElseGet(ArrayList::new);

        ativosNaCarteira.stream()
                .filter(ativo -> ativo != null && (ativo.getQuantidade() > 0 || ativo.getPrecoMedio() > 0.0))
                .forEach(ativosCarteiraList::add);

        mihasAcoesTable.setItems(ativosCarteiraList);
    }


    private String getMoedaPorSigla(String sigla) {
        return "USD";
    }

    @FXML
    private TableView<AtivosFinanceiros> mihasAcoesTable;

    @FXML
    private TableColumn<AtivosFinanceiros, String> codigoMinhasAcoes;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoMedioMinhasAcoes;

    @FXML
    private TableColumn<AtivosFinanceiros, Integer> quantidadeMinhasAcoes;

    @FXML
    private TableColumn<AtivosFinanceiros, String> moedaMinhasAcoes;

    @FXML
    private Button comprarBotao;

    @FXML
    private Button venderBotao;

    @FXML
    void botaoComprar(ActionEvent event) {
        AtivosFinanceiros ativoFinanceiro = acoesDisponiveisTable.getSelectionModel().getSelectedItem();
        CarteiraUsuario carteiraUsuario = cboxSelecionarCarteira.getValue();
        if (ativoFinanceiro != null && carteiraUsuario != null) {
            this.compra = true;
            abrirCompraVenda(ativoFinanceiro, carteiraUsuario, true);
        } else if (ativoFinanceiro == null) {
            ControladorGeral.alertaErro("Ativos Financeiros", "Para comprar um ativo, primeiro você deve selecionar na tabela.");
        } else if (carteiraUsuario == null) {
            ControladorGeral.alertaErro("Ativos Financeiros", "Para comprar ativos, você deve selecionar a carteira de investimentos.");
        } else {
            ControladorGeral.alertaErro("Ativos Financeiros", "Para comprar um ativo, primeiro você deve selecionar na tabela.");
        }
    }

    @FXML
    void botaoVender(ActionEvent event) {
        AtivosFinanceiros ativoFinanceiro = mihasAcoesTable.getSelectionModel().getSelectedItem();
        CarteiraUsuario carteiraUsuario = cboxSelecionarCarteira.getValue();
        if (ativoFinanceiro != null && carteiraUsuario != null) {
            this.compra = false;
            abrirCompraVenda(ativoFinanceiro, carteiraUsuario, false);
        } else if (ativoFinanceiro == null) {
            ControladorGeral.alertaErro("Ativos Financeiros", "Para comprar um ativo, primeiro você deve selecionar na tabela.");
        } else if (carteiraUsuario == null) {
            ControladorGeral.alertaErro("Ativos Financeiros", "Para comprar ativos, você deve selecionar a carteira de investimentos.");
        } else {
            ControladorGeral.alertaErro("Ativos Financeiros", "Para comprar um ativo, primeiro você deve selecionar na tabela.");
        }
    }

    public void abrirCompraVenda(AtivosFinanceiros ativoFinanceiro, CarteiraUsuario carteira, boolean compra) {
        setDadosCompraVenda(ativoFinanceiro, carteira, compra);
            Label informacoesCambioLabel =  this.informacoesCambioLabel;
            Label compraVendaLabel = this.compraVendaLabel;

            if (compraVendaLabel != null) {
                String operacao;
                if (compra) {
                    operacao = "Comprar";
                } else {
                    operacao = "Vender";
                }
                compraVendaLabel.setText(operacao + ": " + ativoFinanceiro.informacoesDoAtivo());
                informacoesCambioLabel.setText(carteira.informacoesCarteira()+precoCambio(ativoFinanceiro, carteira));
        }
    }

    public String precoCambio(AtivosFinanceiros ativoFinanceiro, CarteiraUsuario carteiraUsuario) {
        String saldoPosConversao;
        String precoPosConversao;
        String moedaOrigem = carteiraUsuario.getEnumTipoMoeda().toString();
        String moedaDestino = ativoFinanceiro.getMoeda();

        double saldoAtual = carteiraUsuario.getSaldoDisponivel();
        double taxaCambio = getTaxaCambio(moedaOrigem, moedaDestino);

        if (taxaCambio != -1) {
            saldoPosConversao = String.format("%.2f", saldoAtual * taxaCambio);
            precoPosConversao = String.format("%.2f", ativoFinanceiro.getPrecoAtual() / taxaCambio);
        } else {
            saldoPosConversao = "Não foi possível obter o câmbio";
            precoPosConversao = "Não foi possível obter o preço em "+moedaDestino;
        }

        return "\n\nPreço ativo (unidade): "+precoPosConversao+" "+moedaOrigem+
                "\nCâmbio "+moedaOrigem+" para "+moedaDestino+
                "\nPreço ativo (unidade): "+ativoFinanceiro.getPrecoAtual()+" "+ativoFinanceiro.getMoeda()+"\n\n"+
                "Seu saldo atual em "+moedaDestino+": "+saldoPosConversao;
    }

    private double getTaxaCambio(String moedaOrigem, String moedaDestino) {
        System.out.println(moedaOrigem+" "+moedaDestino);

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
                return jsonObject.getJSONObject(key).getDouble("bid");
            } else {
                System.out.println("Erro na conexão com a API.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private AtivosFinanceiros ativoSelecionado;
    private CarteiraUsuario carteiraSelecionada;

    public AtivosFinanceiros ativoSelecionado() {
        return ativoSelecionado;
    }

    public CarteiraUsuario carteiraSelecionada() {
        return carteiraSelecionada;
    }

    public void setDadosCompraVenda(AtivosFinanceiros ativo, CarteiraUsuario carteira, boolean compra) {
        this.ativoSelecionado = ativo;
        this.carteiraSelecionada = carteira;
    }

    @FXML
    void botaoConfirmarCompraVenda(ActionEvent event) {
        try {
            int quantidade = Integer.parseInt(quantidadeCompVendField.getText());

            if (quantidade <= 0) {
                ControladorGeral.alertaErro("Quantidade Inválida", "A quantidade deve ser maior que zero.");
                return;
            }

            AtivosFinanceiros ativoParaComprar = ativoSelecionado();
            AtivosFinanceiros ativoParaVender = ativoSelecionado();
            CarteiraUsuario carteiraUsuario = carteiraSelecionada();

            if (this.compra) {
                comprarAtivos(ativoParaComprar, carteiraUsuario);
            } else {
                venderAtivos(ativoParaVender, carteiraUsuario, quantidade);
            }
            RepositorioCarteiras.getInstancia().atualizarCarteira(carteiraUsuario);

            atualizarInformacoesTela();
        } catch (NumberFormatException e) {
            ControladorGeral.alertaErro("Entrada Inválida", "Digite um número válido para a quantidade.");
        }
    }


    public void comprarAtivos(AtivosFinanceiros ativoFinanceiro, CarteiraUsuario carteiraUsuario) {
        int quantidade = Integer.parseInt(quantidadeCompVendField.getText());

        double taxaCambio = getTaxaCambio(carteiraSelecionada().getEnumTipoMoeda().toString(), ativoFinanceiro.getMoeda());
        if (taxaCambio == -1) {
            ControladorGeral.alertaErro("Erro de Câmbio", "Não foi possível obter a taxa de câmbio. Tente novamente mais tarde.");
            return;
        }

        System.out.println(taxaCambio);
        double precoAtivoConvertido = ativoFinanceiro.getPrecoAtual() / taxaCambio;
        double valorFinalOrigem = ativoFinanceiro.getPrecoAtual() * quantidade;
        double valorFinal = precoAtivoConvertido * quantidade;

        if (carteiraUsuario.getSaldoDisponivel() >= valorFinal) {
            carteiraUsuario.setSaldoDisponivel(carteiraUsuario.getSaldoDisponivel() - valorFinal);

            ControladorCarteirasUser.getInstancia().calcularPrecoMedioAtivo(carteiraUsuario, ativoFinanceiro, quantidade, precoAtivoConvertido);

            ControladorGeral.alertaInformacao("Compra Realizada",
                    "Compra de " + quantidade + " unidades do ativo " + ativoFinanceiro.getCodigo() + " efetuada com sucesso.");
        } else {
            ControladorGeral.alertaErro("Saldo Insuficiente",
                    "Saldo insuficiente para completar a compra. Valor necessário: " + String.format("%.2f", valorFinalOrigem) + " " + ativoFinanceiro.getMoeda());
        }
    }


    public void venderAtivos(AtivosFinanceiros ativoFinanceiro, CarteiraUsuario carteiraUsuario, int quantidade) {
        if (quantidade <= 0) {
            ControladorGeral.alertaErro("Quantidade Inválida", "A quantidade de ativos deve ser maior que zero.");
            return;
        }

        boolean ativoEncontrado = false;

        for (int i = 0; i < carteiraUsuario.getTamanho(); i++) {
            AtivosFinanceiros ativo = carteiraUsuario.getAtivosFinanceiros()[i];

            if (ativo.getCodigo().equals(ativoFinanceiro.getCodigo())) {
                ativoEncontrado = true;

                if (ativo.getQuantidade() >= quantidade) {
                    ativo.removerQuantidade(quantidade);
                    double valorRecebido = ativoFinanceiro.getPrecoAtual() * quantidade;
                    carteiraUsuario.setSaldoDisponivel(carteiraUsuario.getSaldoDisponivel() + valorRecebido);

                    if (ativo.getQuantidade() == 0) {
                        carteiraUsuario.removerAtivoDaCarteira(i);
                    }

                    ControladorGeral.alertaInformacao("Venda Realizada", "Venda de " + quantidade + " unidades do ativo " + ativoFinanceiro.getCodigo() + " efetuada com sucesso.");
                } else {
                    ControladorGeral.alertaErro("Quantidade Insuficiente", "Você não possui quantidade suficiente do ativo para realizar a venda.");
                }
                break;
            }
        }

        if (!ativoEncontrado) {
            ControladorGeral.alertaErro("Ativo Não Encontrado", "O ativo " + ativoFinanceiro.getCodigo() + " não foi encontrado na sua carteira.");
        }
    }


    public String infoAtivoComprar() {
        AtivosFinanceiros ativoFinanceiro = acoesDisponiveisTable.getSelectionModel().getSelectedItem();
        String informacoes;

        if (ativoFinanceiro != null) {
            informacoes = ativoFinanceiro.informacoesDoAtivo();
            compraVendaLabel.setText(informacoes);
        } else {
            informacoes = "Selecione um ativo.";
        }
        return informacoes;
    }

    public String infoAtivoVender() {
        return "a";
    }

    public String infoCarteira() {
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira.getSelectionModel().getSelectedItem();
        String informacoes;

        if (carteiraSelecionada != null) {
            informacoes = carteiraSelecionada.exibirNoGerenciarCarteiras();
            infoCarteiraSelecionadaLabel.setText(informacoes);
            carregarAtivosDaCarteira(carteiraSelecionada);
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

    public void atualizarInformacoesTela() {
        infoCarteira();
        if (informacoesCambioLabel != null && carteiraSelecionada() != null && ativoSelecionado() != null) {
            informacoesCambioLabel.setText(carteiraSelecionada().informacoesCarteira() + precoCambio(ativoSelecionado(), carteiraSelecionada()));
        }
        if (carteiraSelecionada() != null) {
            carregarAtivosDaCarteira(carteiraSelecionada());
        }
    }

    public ObservableList<AtivosFinanceiros> obterAtivos(EnumTipoMoeda enumTipoMoeda) {
        ObservableList<AtivosFinanceiros> ativosList = FXCollections.observableArrayList();
        AtivosFinanceiros[] ativosFinanceirosDoArquivo = RepositorioAtivos.lerAtivos();
        String apiKeyEmUso = API_KEY1;

        for (String symbol : SYMBOLS) {
            String apiUrl = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?token=" + apiKeyEmUso;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("Requisitando " + symbol + " com código de resposta: " + responseCode);

                if (responseCode == 429) {
                    System.out.println("Limite de requisições atingido para " + apiKeyEmUso);
                    apiKeyEmUso = (apiKeyEmUso.equals(API_KEY1)) ? API_KEY2 : API_KEY1;
                    apiUrl = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?token=" + apiKeyEmUso;
                    connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                    connection.setRequestMethod("GET");
                    responseCode = connection.getResponseCode();
                    System.out.println("Tentando novamente com a chave " + apiKeyEmUso + " para " + symbol);
                }

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JsonArray jsonResponse = JsonParser.parseString(response.toString()).getAsJsonArray();

                    if (jsonResponse.size() > 0) {
                        JsonObject firstEntry = jsonResponse.get(0).getAsJsonObject();

                        double precoAtual = firstEntry.get("close").getAsDouble();
                        double precoAbertura = firstEntry.get("open").getAsDouble();
                        double maiorPreco = firstEntry.get("high").getAsDouble();
                        double menorPreco = firstEntry.get("low").getAsDouble();

                        String moeda = enumTipoMoeda.name();

                        AtivosFinanceiros novoAtivo = new AtivosFinanceiros(symbol, precoAtual, precoAbertura, maiorPreco, menorPreco, moeda);

                        boolean ativoAtualizado = false;
                        for (int i = 0; i < ativosFinanceirosDoArquivo.length; i++) {
                            if (ativosFinanceirosDoArquivo[i] != null &&
                                    ativosFinanceirosDoArquivo[i].getCodigo().equals(novoAtivo.getCodigo())) {
                                ativosFinanceirosDoArquivo[i] = novoAtivo;
                                ativoAtualizado = true;
                                System.out.println("Ativo atualizado: " + novoAtivo.getCodigo());
                                break;
                            }
                        }

                        if (!ativoAtualizado) {
                            for (int i = 0; i < ativosFinanceirosDoArquivo.length; i++) {
                                if (ativosFinanceirosDoArquivo[i] == null) {
                                    ativosFinanceirosDoArquivo[i] = novoAtivo;
                                    System.out.println("Novo ativo adicionado: " + novoAtivo.getCodigo());
                                    break;
                                }
                            }
                        }

                        ativosList.add(novoAtivo);
                    } else {
                        System.out.println("Sem dados para " + symbol);
                    }
                } else {
                    System.out.println("Erro na requisição para " + symbol + ": " + responseCode);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a requisição para " + symbol);
                e.printStackTrace();
            }
        }

        if (ativosList.isEmpty()) {
            System.out.println("Carregando dados do arquivo, já que não conseguimos dados da API.");
            for (AtivosFinanceiros ativoArquivo : ativosFinanceirosDoArquivo) {
                if (ativoArquivo != null) {
                    ativosList.add(ativoArquivo);
                    System.out.println("Ativo carregado do arquivo: " + ativoArquivo.getCodigo());
                }
            }
        } else {
            System.out.println("Atualizando o arquivo com os novos dados.");
            RepositorioAtivos.atualizarAtivos(ativosFinanceirosDoArquivo);
        }

        System.out.println("Dados carregados: " + ativosList.size());
        return ativosList;
    }


    /* ALPHA VANTAGE
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

    @FXML
    private void carregarDadosAtivo() {
        ObservableList<AtivosFinanceiros> ativosList = FXCollections.observableArrayList();

        for (String symbol : SYMBOLS) {
            String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
                    + symbol + "&interval=1min&apikey=" + API_KEY1;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("Requisitando " + symbol + " com código de resposta: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Log da resposta da API
                    System.out.println("Resposta da API para " + symbol + ": " + response.toString());

                    // Parse JSON
                    JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

                    // Verifique se a chave 'Time Series (1min)' existe
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

                        // Logando o ativo que foi adicionado
                        System.out.println("Ativo adicionado: " + ativo.getCodigo());
                    } else {
                        // Se não houver dados para esse símbolo, registre isso
                        System.out.println("Sem dados para " + symbol + " - Time Series (1min) não encontrado ou vazio.");
                    }
                } else {
                    System.out.println("Erro na requisição para " + symbol + ": " + responseCode);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a requisição para " + symbol);
                e.printStackTrace();
            }
        }

        // Verifique se a lista foi preenchida corretamente
        System.out.println("Dados carregados: " + ativosList.size());

        // Atribuindo dados à tabela
        acoesDisponiveisTable.setItems(ativosList);
    }


    private String getMoedaPorSigla(String sigla) {
        switch (sigla) {
            default:
                return "USD";
        }
    }

     */
}
