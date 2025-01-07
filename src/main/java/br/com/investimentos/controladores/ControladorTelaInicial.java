package br.com.investimentos.controladores;

import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.TipoConta;
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
        TipoConta tipoConta;
        String emailUsuario = emailUsuarioField.getText();
        String senha = senhaField.getText();

        if (souAdmBox.isSelected()) {
            tipoConta = TipoConta.ADM;
        } else {
            tipoConta = TipoConta.COMUM;
        }

        RepositorioContas repositorioContas = RepositorioContas.getInstancia();

        try {
            Conta contaLogada = repositorioContas.obterContaParaLogar(emailUsuario, senha, tipoConta);

            if (souAdmBox.isSelected()) {
                if (contaLogada instanceof UsuarioAdministrador) {
                    UsuarioLogado.getInstancia().setUsuarioAdministrador((UsuarioAdministrador) contaLogada);
                    System.out.println("Usuário administrador logado: " + contaLogada.getNome());
                    Programa.trocarTela(6);
                } else {
                    throw new ContaNaoExisteException("A conta encontrada não é de tipo administrador.");
                }
            } else {
                if (contaLogada instanceof UsuarioComum) {
                    UsuarioLogado.getInstancia().setUsuarioComum((UsuarioComum) contaLogada);
                    System.out.println("Usuário comum logado: " + contaLogada.getNome());
                    Programa.trocarTela(5);
                } else {
                    throw new ContaNaoExisteException("A conta encontrada não é de tipo usuário comum.");
                }
            }
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
}
