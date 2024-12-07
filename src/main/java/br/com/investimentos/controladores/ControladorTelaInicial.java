package br.com.investimentos.controladores;

import br.com.investimentos.usuarios.Conta;
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

        String emailUsuario = emailUsuarioField.getText();
        String senha = senhaField.getText();

        if (souAdmBox.isSelected()) {
            //Deve buscar e logar como usuário adm
        } else {
            //Deve buscar e logar como usuário comum
            Conta conta = ControladorArquivos.buscarConta(emailUsuario);
            if (conta != null && conta.getSenha().equals(senha)) {
                System.out.println("Usuário logado.");
                ControladorGeral.alertaInformacao("Login", "Login bem sucedido!");
                Programa.trocarTela(5);
            } else {
                System.out.println("Erro ao logar.");
                ControladorGeral.alertaErro("Login", "Erro. Verifique as informações e tente novamente.");
            }
        }
    }

    public void clicarCadastrar(ActionEvent actionEvent) {
        System.out.println("Clicou cadastrar");
        Programa.trocarTela(2);
    }
}
