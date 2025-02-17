package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
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

    @FXML
    void confirmarBotao054(ActionEvent event) {

    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {
        CarteiraUsuario carteiraUsuario = cboxSelecionarCarteira.getValue();
        double saldoDisponivel = carteiraUsuario.getSaldoDisponivel();
        EnumTipoMoeda tipoMoeda = carteiraUsuario.getEnumTipoMoeda();

    }

    @FXML
    void selecionarTempoCbox(ActionEvent event) {

    }

    @FXML
    void voltarBotao054(ActionEvent event) {
        trocarTela(5);
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
        String ipca = obterIPCA();
        String selic = obterSELIC();
        String dolar = obterDolar();

        String informacoes = "IPCA: " + ipca + "%\n" +
                "SELIC: " + selic + "%\n" +
                "Dólar: R$ " + dolar;

        informacoesGeraisLabel.setText(informacoes);
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

    private String obterDolar() {
        try {
            String url = "https://economia.awesomeapi.com.br/last/USD-BRL";
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
                return jsonObject.getJSONObject("USDBRL").getString("bid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Erro ao obter cotação do dólar";
    }


}
