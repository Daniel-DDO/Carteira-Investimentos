package br.com.investimentos.controladores.gui;

import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorUserComum {

    @FXML
    private Button botaoFundosImobiliarios;

    @FXML
    private Button botaoMeuPerfil;

    @FXML
    private Button botaoMinhasCarterias;

    @FXML
    private Button botaoProjecoesRentab;

    @FXML
    private Button botaoRenda;

    @FXML
    private Button botaoSimularInvestimentos;

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(1);
    }

    @FXML
    void fundosImobiliariosBotao(ActionEvent event) {
        if (UsuarioLogado.getInstancia().getUsuarioComum().getQuantidadeCarteiras() == 0) {
            ControladorGeral.alertaInformacao("Projeções", "Para fazer a simulação de projeções financeiras é necessário ter ao menos 1 carteira cadastrada. Crie uma carteira na aba 'Minhas carteiras'.");
        } else {
            Programa.trocarTela(10);
        }
    }

    @FXML
    void meuPerfilBotao(ActionEvent event) {
        Programa.trocarTela(12);

        UsuarioLogado sessao = UsuarioLogado.getInstancia();
        UsuarioComum usuario = sessao.getUsuarioComum();

        if (usuario != null) {
            Programa.inserirTextoLabel(Programa.getControladorPerfil().informacoesContaComum, usuario.toString());
        } else {
            Programa.inserirTextoLabel(Programa.getControladorPerfil().informacoesContaComum, "Nenhum usuário está logado.");
        }
    }

    @FXML
    void minhasCarteirasBotao(ActionEvent event) {
        Programa.trocarTela(8);
    }

    @FXML
    void projecoesRentabBotao(ActionEvent event) {
        Programa.trocarTela(9);
    }

    @FXML
    void rendaBotao(ActionEvent event) {
        Programa.trocarTela(11);
    }

    @FXML
    void simularInvestimentosBotao(ActionEvent event) {
        Programa.trocarTela(7);
    }
}
