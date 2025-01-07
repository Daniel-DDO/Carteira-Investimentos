package br.com.investimentos.controladores;

import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField fieldConfSenha;

    @FXML
    private TextField fieldCpf;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldNomeCompleto;

    @FXML
    private TextField fieldNomeUser;

    @FXML
    private PasswordField fieldSenha;

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

        if (nome == null || nome.trim().isEmpty()) {
            System.err.println("O nome não pode ser vazio ou nulo.");
            ControladorGeral.alertaErro("Erro", "O nome não pode ser vazio ou nulo.");
        }
        else if (nomeUsuario == null || nomeUsuario.trim().isEmpty() || nomeUsuario.length() < 6) {
            System.err.println("O nome usuário não pode ser vazio ou nulo.");
            ControladorGeral.alertaErro("Erro", "O nome usuário não pode ser vazio, nulo ou ter menos de 6 dígitos.");
        }
        else if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".com")) {
            System.err.println("Insira um email válido.");
            ControladorGeral.alertaErro("Erro", "Insira um email válido.");
        }
        else if (senha == null || senha.trim().isEmpty() || !senha.equals(confSenha) || senha.length() < 6) {
            if (senha == null) {
                ControladorGeral.alertaErro("Erro", "A senha não pode ser nula.");
            } else if (senha.trim().isEmpty()) {
                ControladorGeral.alertaErro("Erro", "A senha não pode ser vazia.");
            } else if (senha.length() < 6) {
                ControladorGeral.alertaErro("Erro", "A senha deve ter 6 dígitos ou mais.");
            } else if (!senha.equals(confSenha)) {
                ControladorGeral.alertaErro("Erro", "As senhas devem ser iguais.");
            } else {
                ControladorGeral.alertaErro("Erro", "Verifique se as senhas conferem. A senha deve ter ao menos 6 dígitos.");
                System.err.println("Verifique se as senhas conferem. A senha deve ter ao menos 6 dígitos.");
            }
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
            RepositorioContas.getInstancia().verificarInformacoes(nomeUsuario, email, cpf);

            Conta contaUsuarioComum = new UsuarioComum(nome, nomeUsuario, email, senha, telefone, cpf);

            RepositorioContas repositorioContas = RepositorioContas.getInstancia();
            repositorioContas.inserirConta(contaUsuarioComum);

            ControladorGeral.alertaInformacao("Cadastro Concluído!", "Seu cadastro foi realizado com sucesso.");

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

            Programa.trocarTela(1);
            System.out.println("\nCONTAS CRIADAS\n");
            repositorioContas.exibirContas(); //pra ver se tá criando no array
        }
    }

    @FXML
    public void voltarBotao03(ActionEvent event) {
        Programa.trocarTela(2);
    }


    //Tela cadastro de usuário adm

    @FXML
    private Button botaoConfirmar04;

    @FXML
    private Button botaoVoltar04;

    @FXML
    private PasswordField fieldConfSenhaAdm;

    @FXML
    private TextField fieldCpfAdm;

    @FXML
    private TextField fieldEmailAdm;

    @FXML
    private TextField fieldNomeCompletoAdm;

    @FXML
    private TextField fieldNomeUserAdm;

    @FXML
    private PasswordField fieldSenhaAdm;

    @FXML
    private TextField fieldTelefoneAdm;

    @FXML
    public void voltarBotao04(ActionEvent event) {
        Programa.trocarTela(2);
    }

    @FXML
    public void confirmarBotao04(ActionEvent event) {
        String nome = fieldNomeCompletoAdm.getText();
        String nomeUsuario = fieldNomeUserAdm.getText();
        String email = fieldEmailAdm.getText();
        String senha = fieldSenhaAdm.getText();
        String confSenha = fieldConfSenhaAdm.getText();
        String telefone = fieldTelefoneAdm.getText();
        String cpf = fieldCpfAdm.getText();

        if (nome == null || nome.trim().isEmpty()) {
            ControladorGeral.alertaErro("Erro", "O nome não pode ser nulo ou vazio.");
            System.err.println("O nome não pode ser nulo ou vazio.");
        } else if (nomeUsuario == null || nomeUsuario.trim().isEmpty() || nomeUsuario.length() < 6) {
            ControladorGeral.alertaErro("Erro", "O nome de usuário não pode ser vazio, nulo ou ter menos de 6 dígitos.");
            System.err.println("O nome de usuário não pode ser vazio, nulo ou ter menos de 6 dígitos.");
        } else if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".com")) {
            ControladorGeral.alertaErro("Erro", "Digite um email válido.");
            System.err.println("Digite um email válido.");
        } else if (senha == null || senha.trim().isEmpty() || senha.length() < 6 || !senha.equals(confSenha)) {
            if (senha == null) {
                ControladorGeral.alertaErro("Erro", "A senha não pode ser nula.");
            } else if (senha.trim().isEmpty()) {
                ControladorGeral.alertaErro("Erro", "A senha não pode ser vazia.");
            } else if (senha.length() < 6) {
                ControladorGeral.alertaErro("Erro", "A senha deve ter 6 dígitos ou mais.");
            } else if (!senha.equals(confSenha)) {
                ControladorGeral.alertaErro("Erro", "As senhas devem ser iguais.");
            } else {
                ControladorGeral.alertaErro("Erro", "Verifique se as senhas conferem. A senha deve ter ao menos 6 dígitos.");
                System.err.println("Verifique se as senhas conferem. A senha deve ter ao menos 6 dígitos.");
            }
        } else if (telefone == null || telefone.trim().isEmpty() || telefone.length() < 8) {
            ControladorGeral.alertaErro("Erro", "O telefone deve ter ao menos 8 dígitos.");
            System.err.println("O telefone deve ter ao menos 8 dígitos.");
        } else if (cpf == null || cpf.trim().isEmpty() || cpf.length() != 11) {
            ControladorGeral.alertaErro("Erro", "Digite um CPF válido.");
            System.err.println("Digite um CPF válido.");
        } else {
            RepositorioContas.getInstancia().verificarInformacoes(nomeUsuario, email, cpf);

            Conta contaAdm = new UsuarioAdministrador(nome, nomeUsuario, email, senha, telefone, cpf);

            RepositorioContas repositorioContas = RepositorioContas.getInstancia();
            repositorioContas.inserirConta(contaAdm);

            System.out.println("Cadastro concluído com sucesso.");
            ControladorGeral.alertaInformacao("Carteira Investimentos", "Cadastro concluído com sucesso!");

            fieldNomeCompletoAdm.clear();
            fieldNomeUserAdm.clear();
            fieldEmailAdm.clear();
            fieldSenhaAdm.clear();
            fieldConfSenhaAdm.clear();
            fieldTelefoneAdm.clear();
            fieldCpfAdm.clear();

            repositorioContas.exibirContas();
            Programa.trocarTela(1);
        }
    }
}
