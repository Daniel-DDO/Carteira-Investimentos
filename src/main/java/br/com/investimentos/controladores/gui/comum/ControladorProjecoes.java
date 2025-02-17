package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.financas.EnumTempo;
import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorProjecoes implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 10) {
            inicializarComboBox();
            cboxSelecionarTempo.getItems().addAll(EnumTempo.values());
            aporteMensalField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    aporteMensalField.setText(oldValue);
                }
            });
            taxaRetField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    taxaRetField.setText(oldValue);
                }
            });
            prazoField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    prazoField.setText(oldValue);
                }
            });
        }
    }

    @FXML
    private TextField aporteMensalField;

    @FXML
    private Button botaoConfirmar054;

    @FXML
    private Button botaoVoltar054;

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private ComboBox<EnumTempo> cboxSelecionarTempo;

    @FXML
    private Label infoSaldoLabel;

    @FXML
    private Label informacoesGeraisLabel;

    @FXML
    private TextField prazoField;

    @FXML
    private Button simularBotao;

    @FXML
    private TextField taxaRetField;

    private CarteiraUsuario carteiraSelecionada;

    public CarteiraUsuario getCarteiraSelecionada() {
        return carteiraSelecionada;
    }

    public void setCarteiraSelecionada(CarteiraUsuario carteiraSelecionada) {
        this.carteiraSelecionada = carteiraSelecionada;
    }

    @FXML
    void confirmarBotao054(ActionEvent event) {

    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {
        setCarteiraSelecionada(cboxSelecionarCarteira.getValue());
        infoSaldoLabel.setText("Saldo carteira "+getCarteiraSelecionada().getEnumTipoMoeda()+": "+String.format("%.2f",getCarteiraSelecionada().getSaldoDisponivel()));
    }

    @FXML
    void selecionarTempoCbox(ActionEvent event) {

    }

    @FXML
    void voltarBotao054(ActionEvent event) {
        setCarteiraSelecionada(null);
        infoSaldoLabel.setText("Saldo carteira");
        cboxSelecionarTempo.getItems().clear();
        aporteMensalField.clear();
        taxaRetField.clear();
        prazoField.clear();
        infoSaldoLabel.setText("Informações atuais.");
        trocarTela(5);
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
    void botaoSimular(ActionEvent event) {
        if (cboxSelecionarCarteira.getValue() == null) {
            ControladorGeral.alertaErro("Carteira", "Selecione uma carteira para continuar.");
        } else if (aporteMensalField.getText() == null || aporteMensalField.getText().isEmpty()) {
            ControladorGeral.alertaErro("Aporte mensal", "Digite algum valor para o aporte mensal, ou seja, um depósito recorrente que você pretende fazer.");
        } else if (taxaRetField.getText() == null || taxaRetField.getText().isEmpty()) {
            ControladorGeral.alertaErro("Taxa de retorno", "Digite uma taxa de retorno.");
        } else if (prazoField.getText() == null || prazoField.getText().isEmpty()) {
            ControladorGeral.alertaErro("Prazo", "Digite um prazo. (Apenas números).");
        } else if (cboxSelecionarTempo.getValue() == null) {
            ControladorGeral.alertaErro("Prazo", "Selecione se o prazo deve ser calculado em semanas, meses ou anos.");
        } else {
            double aporteMensal = Double.parseDouble(aporteMensalField.getText());
            double taxaRetorno = Double.parseDouble(taxaRetField.getText()) / 100;
            int prazo = Integer.parseInt(prazoField.getText());
            String tempoSelecionado = String.valueOf(cboxSelecionarTempo.getValue());


            EnumTipoMoeda tipoMoeda = getCarteiraSelecionada().getEnumTipoMoeda();
            double saldoDisp = getCarteiraSelecionada().getSaldoDisponivel();

            aporteMensal = converterMoeda(aporteMensal, EnumTipoMoeda.USD, tipoMoeda);

            double saldoInicial = converterMoeda(saldoDisp, EnumTipoMoeda.USD, tipoMoeda);
            System.out.println(saldoDisp);
            System.out.println(saldoInicial);

            String ipca = obterIPCA();
            String selic = obterSELIC();
            String dolar = String.format("%.2f", converterMoeda(1, getCarteiraSelecionada().getEnumTipoMoeda(), EnumTipoMoeda.USD));

            double ipcaValor = Double.parseDouble(ipca.replace(",", "."));
            double selicValor = Double.parseDouble(selic.replace(",", "."));
            double dolarValor = Double.parseDouble(dolar.replace(",", "."));

            double taxaDeRetornoMensal = taxaRetorno / 12;
            double valorFuturo = calcularValorFuturoCrescimentoComposto(saldoInicial, aporteMensal, taxaDeRetornoMensal, prazo, tempoSelecionado);

            double valorFuturoConvertido = converterMoeda(valorFuturo, tipoMoeda, EnumTipoMoeda.USD);
            String informacoes = String.format(
                    "IPCA: %.2f%%\nSELIC: %.2f%%\nDólar: %.2f %s\nRentabilidade futura: %.2f %s",
                    ipcaValor, selicValor, dolarValor,
                    getCarteiraSelecionada().getEnumTipoMoeda(),
                    valorFuturoConvertido, tipoMoeda
            );

            informacoesGeraisLabel.setText(informacoes);
        }
    }

    private double calcularValorFuturoCrescimentoComposto(double saldoInicial, double aporteMensal, double taxaRetornoMensal, int prazo, String tempoSelecionado) {
        double valorFuturo;

        if (tempoSelecionado.equalsIgnoreCase("anos")) {
            prazo *= 12;
        } else if (tempoSelecionado.equalsIgnoreCase("semanas")) {
            prazo *= 4;
        }

        valorFuturo = saldoInicial * Math.pow(1 + taxaRetornoMensal, prazo) + (aporteMensal * (Math.pow(1 + taxaRetornoMensal, prazo) - 1)) / taxaRetornoMensal;

        return valorFuturo;
    }

    private String obterIPCA() {
        try {
            String url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.433/dados/ultimos/1?formato=json";
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

                JSONObject jsonObject = new JSONObject(response.toString().replace("[", "").replace("]", ""));
                return jsonObject.getString("valor");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Erro ao obter IPCA";
    }

    private String obterSELIC() {
        try {
            String url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.1178/dados/ultimos/1?formato=json";
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

                JSONObject jsonObject = new JSONObject(response.toString().replace("[", "").replace("]", ""));
                return jsonObject.getString("valor");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Erro ao obter SELIC";
    }


}
