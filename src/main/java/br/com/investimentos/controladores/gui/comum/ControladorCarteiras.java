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
import java.util.ArrayList;

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
        if (ControladorCarteirasUser.getInstancia().verificarSeTemCarteira(UsuarioLogado.getInstancia().getUsuarioComum())) {
            Programa.trocarTela(15);
        } else {
            ControladorGeral.alertaErro("Visualizar carteiras",
                    "Ops! Você não tem carteiras. Crie uma para visualizá-la ou gerenciá-la.");
        }
    }

    @FXML
    void botaoVisualizarCarteiras(ActionEvent event) {
        if (ControladorCarteirasUser.getInstancia().verificarSeTemCarteira(UsuarioLogado.getInstancia().getUsuarioComum())) {
            Programa.trocarTela(14);
        } else {
            ControladorGeral.alertaErro("Visualizar carteiras",
                    "Ops! Você não tem carteiras. Crie uma para visualizá-la ou gerenciá-la.");
        }
    }

    @FXML
    void confirmarBotao052(ActionEvent event) {

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
            nomeCarteiraField.clear();
            objetivoCarteiraField.clear();
            saldoInicialField.clear();
            tipoInvestidorComboBox.getItems().clear();
            Programa.trocarTela(8);
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

}
