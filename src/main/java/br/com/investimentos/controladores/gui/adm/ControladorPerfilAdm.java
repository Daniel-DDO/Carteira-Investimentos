package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorPerfilAdm implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 16) {
            //passar info
        }
    }

    @FXML
    private Button botaoConfirmar06;

    @FXML
    private Button botaoExcluirConta;

    @FXML
    private Button botaoVoltar06;

    @FXML
    public Label informacoesContaAdm;

    @FXML
    void confirmarBotao06(ActionEvent event) {

    }

    @FXML
    void excluirContaBotao(ActionEvent event) {
        boolean confirmar = ControladorGeral.alertaConfirmacaoComRes("Excluir conta", "Deseja excluir a sua conta? Essa é uma operação irreversível e não há como desfazer. Ao clicar em OK, tudo associado a sua conta será excluido e apagado permanentemente.");
        if (confirmar) {
            Programa.trocarTela(1);
            RepositorioContaUsuario.getInstancia().excluirConta(UsuarioLogado.getInstancia().getUsuarioAdministrador());
            ControladorGeral.alertaConfirmacao("Conta excluída", "A sua conta foi excluída e tudo associado a ela também foi deletado.");
        }
    }

    @FXML
    void voltarBotao06(ActionEvent event) {
        Programa.trocarTela(6);
    }

}
