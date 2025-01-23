package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.EnumTipoInvestidor;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ControladorCarteiras {

    private static ControladorCarteiras instancia;

    public static ControladorCarteiras getInstancia() {
        if (instancia == null) {
            instancia = new ControladorCarteiras();
        }
        return instancia;
    }

    @FXML
    public void initialize() {
        if (tipoInvestidorComboBox != null) {
            tipoInvestidorComboBox.getItems().addAll(EnumTipoInvestidor.values());
        }
    }

    @FXML
    private Button botaoVoltar05;

    @FXML
    private Button botaoConfirmar052;

    @FXML
    private Button criarCarteiraBotao;

    @FXML
    private Button gerenciarCarteirasBotao;

    @FXML
    private Button visualizarCarteirasBotao;

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }

    @FXML
    void botaoCriarCarteira(ActionEvent event) {
        Programa.trocarTela(13);
    }

    @FXML
    void botaoGerenciarCarteiras(ActionEvent event) {

    }

    @FXML
    void botaoVisualizarCarteiras(ActionEvent event) {
        ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());
        Programa.trocarTela(14);
    }

    @FXML
    void confirmarBotao052(ActionEvent event) {
        visualizarCarteirasCbox();
    }


    //Tela 05-2-1

    @FXML
    private Button botaoConfirmar0521;

    @FXML
    private Button botaoVoltar052;

    @FXML
    private TextField nomeCarteiraField;

    @FXML
    private TextField objetivoCarteiraField;

    @FXML
    private TextField saldoInicialField;

    @FXML
    private ComboBox<EnumTipoInvestidor> tipoInvestidorComboBox;


    public void criarNovaCarteira() {
        String nomeCarteira = nomeCarteiraField.getText();
        String objetivoCarteira = objetivoCarteiraField.getText();
        double saldoInicial = Double.parseDouble(saldoInicialField.getText());
        EnumTipoInvestidor enumTipoInvestidor = tipoInvestidorComboBox.getValue();

        if (nomeCarteira.length() < 5) {
            ControladorGeral.alertaErro("Erro", "O nome da carteira deve conter 5 ou mais caracteres.");
        } else if (objetivoCarteira.length() < 10) {
            ControladorGeral.alertaErro("Erro", "Os objetivos da carteira devem conter 10 ou mais caracteres.");
        } else if (saldoInicial < 0) {
            ControladorGeral.alertaErro("Erro", "O saldo inicial não pode ser negativo.");
        } else if (enumTipoInvestidor == null) {
            enumTipoInvestidor = EnumTipoInvestidor.MODERADO;
        } else {

            LocalDate localDate = LocalDate.now();

            ControladorCarteirasUser.getInstancia().criarNovaCarteira(nomeCarteira, saldoInicial, localDate, objetivoCarteira, enumTipoInvestidor, UsuarioLogado.getInstancia().getUsuarioComum());
            ControladorGeral.alertaInformacao("Carteira criada!", "Parabéns, sua carteira foi criada com sucesso.");
        }
    }

    @FXML
    void confirmarBotao0521(ActionEvent event) {
        criarNovaCarteira();
    }

    @FXML
    void voltarBotao052(ActionEvent event) {
        Programa.trocarTela(8);
    }


    //Tela 05-2-2

    public void listarCarteirasCbox() {
        if (cboxSelecionarCarteira != null) {
            cboxSelecionarCarteira.getItems().addAll(ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum()));
        }
    }

    public void visualizarCarteirasCbox() {
        CarteiraUsuario[] carteiraUsuarios = ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());
        boolean temCarteira = false;

        for (int i = 0; i < RepositorioCarteiras.getInstancia().getTamanho(); i++) {
            if (carteiraUsuarios[i] != null) {
                if (carteiraUsuarios[i].getUsuario().getNomeUsuario().equals(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario())) {
                    System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario());
                    System.out.println(carteiraUsuarios[i].getUsuario().getNomeUsuario());
                    temCarteira = true;

                    listarCarteirasCbox();
                }
            }
        }

        if (!temCarteira) {
            System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario()+" não tem carteiras.");
        }
    }

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private Label infoCarteiraSelecionadaLabel;

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {
        infoCarteiraSelecionadaLabel.setText("Selecionado");
    }

}
