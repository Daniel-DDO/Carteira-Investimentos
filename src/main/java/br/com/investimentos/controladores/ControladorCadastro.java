package br.com.investimentos.controladores;

import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorCadastro {

    //Tela pré-cadastro

    @FXML
    private Button botaoAdministrador;

    @FXML
    private Button botaoConfirmar02;

    @FXML
    private Button botaoUserComum;

    @FXML
    private Button botaoVoltar02;

    @FXML
    private Label tipoCadastro;

    @FXML
    public void administrradorBotao(ActionEvent event) {
        Programa.trocarTela(4);
    }

    @FXML
    public void confirmarBotao02(ActionEvent event) {

    }

    @FXML
    public void userComumBotao(ActionEvent event) {
        Programa.trocarTela(3);
    }

    @FXML
    public void voltarBotao02(ActionEvent event) {
        Programa.trocarTela(1); //voltar para a tela principal
    }


    //Tela cadastro de usuário comum

    @FXML
    private Button botaoConfirmar03;

    @FXML
    private Button botaoVoltar03;

    @FXML
    private TextField fieldConfSenha;

    @FXML
    private TextField fieldCpf;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldNomeCompleto;

    @FXML
    private TextField fieldNomeUser;

    @FXML
    private TextField fieldSenha;

    @FXML
    private TextField fieldTelefone;

    @FXML
    public void confirmarBotao03(ActionEvent event) {
        String nome = fieldNomeCompleto.getText();
        String nomeUsuario = fieldNomeUser.getText();
        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();
        String confSenha = fieldConfSenha.getText();
        String telefone = fieldTelefone.getText();
        String cpf = fieldCpf.getText();

        RepositorioContas repositorioContas = new RepositorioContas();

        if (nome == null || nome.trim().isEmpty()) {
            System.err.println("O nome não pode ser vazio ou nulo.");
            ControladorGeral.alertaErro("Erro", "O nome não pode ser vazio ou nulo.");
        }
        else if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            System.err.println("O nome usuário não pode ser vazio ou nulo.");
            ControladorGeral.alertaErro("Erro", "O nome usuário não pode ser vazio ou nulo.");
        }
        else if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            System.err.println("Insira um email válido.");
            ControladorGeral.alertaErro("Erro", "Insira um email válido.");
        }
        else if (senha == null || senha.trim().isEmpty() || !senha.equals(confSenha) || senha.length() < 6) {
            System.err.println("Verifique se as senhas conferem. Elas devem ter ao menos 6 caracteres.");
            ControladorGeral.alertaErro("Erro", "Verifique se as senhas conferem. Elas devem ter ao menos 6 caracteres.");
        }
        else if (telefone == null || telefone.trim().isEmpty() || telefone.length() < 8) {
            System.err.println("Verifique se o telefone está correto.");
            ControladorGeral.alertaErro("Erro", "Verifique se o telefone está correto.");
        }
        else if (cpf == null || cpf.trim().isEmpty() || cpf.length() != 11) {
            System.err.println("Verifique se o CPF é válido.");
            ControladorGeral.alertaErro("Erro", "Verifique se o CPF é válido.");
        }
        else {
            Conta contaUsuarioComum = new UsuarioComum();
            contaUsuarioComum.setNome(nome);
            contaUsuarioComum.setNomeUsuario(nomeUsuario);
            contaUsuarioComum.setEmail(email);
            contaUsuarioComum.setSenha(senha);
            contaUsuarioComum.setTelefone(telefone);
            contaUsuarioComum.setCpf(cpf);

            repositorioContas.inserirConta(contaUsuarioComum);

            System.out.println("\n" + contaUsuarioComum.getNome() + "\n" + contaUsuarioComum.getNomeUsuario() + "\n" +
                    contaUsuarioComum.getEmail() + "\n" + contaUsuarioComum.getSenha() + "\n" +
                    contaUsuarioComum.getTelefone() + "\n" + contaUsuarioComum.getCpf()+ "\n");

            System.out.println("Cadastro concluído com sucesso.");
            fieldNomeCompleto.clear();
            fieldNomeUser.clear();
            fieldEmail.clear();
            fieldSenha.clear();
            fieldConfSenha.clear();
            fieldTelefone.clear();
            fieldCpf.clear();
        }
    }

    @FXML
    public void voltarBotao03(ActionEvent event) {
        Programa.trocarTela(2);
    }


    //Tela cadastro de usuário adm
}
