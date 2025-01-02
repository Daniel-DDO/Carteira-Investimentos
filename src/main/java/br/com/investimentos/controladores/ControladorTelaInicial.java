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
        Conta conta = new Conta() {};
        String emailUsuario = emailUsuarioField.getText();
        String senha = senhaField.getText();

        RepositorioContas repositorioContas = RepositorioContas.getInstancia();

        try {
            if (souAdmBox.isSelected()) {
                //Deve buscar e logar como usuário adm
                repositorioContas.buscarContaParaLogar(emailUsuario, senha, conta);
            } else {
                //Deve buscar e logar como usuário comum
                repositorioContas.buscarContaParaLogar(emailUsuario, senha, conta);
            }
        } catch (ContaNaoExisteException contaNaoExisteException) {
            contaNaoExisteException.printStackTrace();
        }

    }

    public void clicarCadastrar(ActionEvent actionEvent) {
        System.out.println("Clicou cadastrar");
        Programa.trocarTela(2);
    }
}
