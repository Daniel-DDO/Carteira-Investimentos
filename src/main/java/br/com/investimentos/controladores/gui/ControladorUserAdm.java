package br.com.investimentos.controladores.gui;

import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorUserAdm {

    @FXML
    private Button ativosFinanceirosBotao;

    @FXML
    private Button botaoConfirmar06;

    @FXML
    private Button botaoVoltar06;

    @FXML
    private Button meuPerfilBotao;

    @FXML
    private Button carteirasBotao;

    @FXML
    private Button informacoesUserBotao;

    @FXML
    void botaoAtivosFinanceiros(ActionEvent event) {
        Programa.trocarTela(17);
    }

    @FXML
    void botaoMeuPerfil(ActionEvent event) {
        Programa.trocarTela(16);

        UsuarioLogado sessao = UsuarioLogado.getInstancia();
        UsuarioAdministrador usuario = sessao.getUsuarioAdministrador();

        if (usuario != null) {
            Programa.inserirTextoLabel(Programa.getControladorPerfilAdm().informacoesContaAdm, usuario.toString());
        } else {
            Programa.inserirTextoLabel(Programa.getControladorPerfilAdm().informacoesContaAdm, "Nenhum usuário está logado.");
        }
    }

    @FXML
    void botaoCarteiras(ActionEvent event) {
        Programa.trocarTela(22);
    }

    @FXML
    void botaoInformacoesUser(ActionEvent event) {
        Programa.trocarTela(23);
    }

    @FXML
    void confirmarBotao06(ActionEvent event) {

    }

    @FXML
    void voltarBotao06(ActionEvent event) {
        Programa.trocarTela(1);
    }
}
