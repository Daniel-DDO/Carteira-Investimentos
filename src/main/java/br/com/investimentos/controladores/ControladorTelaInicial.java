package br.com.investimentos.controladores;

import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.TipoConta;
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
            if (souAdmBox.isSelected()) {
                //Deve buscar e logar como usuário adm
                repositorioContas.buscarContaParaLogar(emailUsuario, senha, tipoConta);
                Programa.trocarTela(6);
            } else {
                //Deve buscar e logar como usuário comum
                repositorioContas.buscarContaParaLogar(emailUsuario, senha, tipoConta);
                Programa.trocarTela(5);
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
