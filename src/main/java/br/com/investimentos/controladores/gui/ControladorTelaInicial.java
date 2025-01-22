package br.com.investimentos.controladores.gui;

import br.com.investimentos.controladores.ControladorContaUsuario;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.comum.ControladorCarteiras;
import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import br.com.investimentos.usuarios.ContaUsuario;
import br.com.investimentos.usuarios.EnumTipoConta;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControladorTelaInicial {

    @FXML
    private Hyperlink cadastrarClicar;

    @FXML
    private TextField emailUsuarioField;

    @FXML
    private Button entrarBotao;

    @FXML
    private PasswordField senhaField;

    @FXML
    private CheckBox souAdmBox;

    public void botaoEntrar(ActionEvent actionEvent) {
        System.out.println("Clicou entrar");
        EnumTipoConta enumTipoConta;
        String emailUsuario = emailUsuarioField.getText();
        String senha = senhaField.getText();

        if (souAdmBox.isSelected()) {
            enumTipoConta = EnumTipoConta.ADM;
        } else {
            enumTipoConta = EnumTipoConta.COMUM;
        }

        RepositorioContaUsuario repositorioContaUsuario = RepositorioContaUsuario.getInstancia();

        try {
            ContaUsuario contaUsuarioLogada = ControladorContaUsuario.getInstancia().obterContaParaLogar(emailUsuario, senha, enumTipoConta);

            if (souAdmBox.isSelected()) {
                if (contaUsuarioLogada instanceof UsuarioAdministrador) {
                    UsuarioLogado.getInstancia().setUsuarioAdministrador((UsuarioAdministrador) contaUsuarioLogada);
                    System.out.println("Usuário administrador logado: " + contaUsuarioLogada.getNome());
                    Programa.trocarTela(6);
                } else {
                    throw new ContaNaoExisteException("A conta encontrada não é de tipo administrador.");
                }
            } else {
                if (contaUsuarioLogada instanceof UsuarioComum) {
                    UsuarioLogado.getInstancia().setUsuarioComum((UsuarioComum) contaUsuarioLogada);
                    System.out.println("Usuário comum logado: " + contaUsuarioLogada.getNome());
                    Programa.trocarTela(5);
                    configurarPosLogin();
                } else {
                    throw new ContaNaoExisteException("A conta encontrada não é de tipo usuário comum.");
                }
            }
            emailUsuarioField.clear();
            senhaField.clear();
        } catch (ContaNaoExisteException contaNaoExisteException) {
            if (emailUsuario.isEmpty() || senha.isEmpty()) {
                ControladorGeral.alertaErro("Erro", "Algum campo está vazio. Insira e tente fazer o login.");
            } else {
                ControladorGeral.alertaErro("Erro", "A conta não existe. Verifique as informações.");
            }
            contaNaoExisteException.printStackTrace();
        }
    }

    public void clicarCadastrar(ActionEvent actionEvent) {
        System.out.println("Clicou cadastrar");
        Programa.trocarTela(2);
    }

    public void configurarPosLogin() {
        ControladorCarteiras.getInstancia().visualizarCarteirasCbox();
    }
}
