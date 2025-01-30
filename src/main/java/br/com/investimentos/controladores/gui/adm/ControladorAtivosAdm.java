package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.ControladorAtivosFinanceiros;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ControladorAtivosAdm implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 17) {
            System.out.println("Controlador ativos adm");
        }
    }

    @FXML
    private Button botaoConfirmar061;

    @FXML
    private Button botaoVoltar061;

    @FXML
    private TextField liquidezField;

    @FXML
    private TextField nomeAtivoField;

    @FXML
    private TextField rentabilidadeField;

    @FXML
    private TextField riscoFinanceiroField;

    @FXML
    private TextField tipoAtivoField;

    @FXML
    private TextField valorAtualField;

    @FXML
    private TextField valorNominalField;

    @FXML
    void confirmarBotao061(ActionEvent event) {
        String nomeAtivo = nomeAtivoField.getText();
        String tipoAtivo = tipoAtivoField.getText();
        double rentabilidade = Double.parseDouble(rentabilidadeField.getText());
        double riscoFinanceiro = Double.parseDouble(riscoFinanceiroField.getText());
        double valorAtual = Double.parseDouble((valorAtualField.getText()));
        double valorNominal = Double.parseDouble((valorNominalField.getText()));
        double liquidez = Double.parseDouble((liquidezField.getText()));

        if (nomeAtivo.trim().isEmpty()) {
            ControladorGeral.alertaErro("Nome ativo", "O nome do ativo não pode ser vazio.");
        } else if (ControladorAtivosFinanceiros.getInstancia().verificarNomeAtivo(nomeAtivo)) {
            ControladorAtivosFinanceiros.getInstancia().verificarNomeAtivo(nomeAtivo);
        } else if (tipoAtivo.trim().isEmpty()) {
            ControladorGeral.alertaErro("Erro", "Erro");
        } else if (riscoFinanceiro == 0) {
            ControladorGeral.alertaErro("Erro", "Erro");
        } else if (rentabilidade == 0) {
            ControladorGeral.alertaErro("Erro", "Erro");
        } else if (valorAtual == 0) {
            ControladorGeral.alertaErro("Erro", "Erro");
        } else if (valorNominal == 0) {
            ControladorGeral.alertaErro("Erro", "Erro");
        } else if (liquidez == 0) {

        } else {
            LocalDate dataInicial = LocalDate.now();
            ControladorAtivosFinanceiros.getInstancia().criarNovoAtivo(nomeAtivo, tipoAtivo, "código", valorAtual, valorNominal, rentabilidade, riscoFinanceiro, liquidez, "Real Brasileiro", dataInicial);
        }
    }

    private void converterStringDouble() {

    }

    @FXML
    void voltarBotao061(ActionEvent event) {
        Programa.trocarTela(6);
    }
}
