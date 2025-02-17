package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorPerfil implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {

    }

    @FXML
    private Button botaoExcluirConta;

    @FXML
    private Button botaoConfirmar05;

    @FXML
    private Button botaoVoltar05;

    @FXML
    public Label informacoesContaComum;

    @FXML
    void confirmarBotao05(ActionEvent event) {

    }

    @FXML
    void voltarBotao05(ActionEvent event) {
        Programa.trocarTela(5);
    }

    @FXML
    void excluirContaBotao(ActionEvent event) {
        boolean confirmar = ControladorGeral.alertaConfirmacaoComRes("Excluir conta", "Deseja excluir a sua conta? Essa é uma operação irreversível e não há como desfazer. Ao clicar em OK, tudo associado a sua conta será excluido e apagado permanentemente.");
        if (confirmar) {
            Programa.trocarTela(1);
            RepositorioContaUsuario.getInstancia().excluirConta(UsuarioLogado.getInstancia().getUsuarioComum());
            ControladorGeral.alertaConfirmacao("Conta excluída", "A sua conta foi excluída e tudo associado a ela também foi deletado.");
        }
    }
}
