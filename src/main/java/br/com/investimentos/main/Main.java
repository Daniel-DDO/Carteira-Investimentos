package br.com.investimentos.main;

import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import br.com.investimentos.usuarios.ContaUsuario;
import br.com.investimentos.usuarios.EnumTipoConta;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;

import java.util.Scanner;

import static javafx.application.Application.launch;

public class Main extends Programa {
    public static void main(String[] args) {
        System.out.println("Carteira de Investimentos");

        launch();

        //Daqui pra frente tudo é apenas para depuração e testes

        RepositorioContaUsuario repositorioContaUsuario = RepositorioContaUsuario.getInstancia();
        ContaUsuario usuarioComum = new UsuarioComum();
        ContaUsuario usuarioAdm = new UsuarioAdministrador();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1. Criar conta comum\n2. Criar conta adm\n3. Exibir contas\n4. Logar conta\n5. Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("CRIANDO CONTA COMUM\nNome: ");
                    scanner.nextLine();
                    usuarioComum.setNome(scanner.nextLine());
                    System.out.print("Nome usuário: ");
                    usuarioComum.setNomeUsuario(scanner.nextLine());
                    System.out.print("Email: ");
                    usuarioComum.setEmail(scanner.nextLine());
                    System.out.print("Senha: ");
                    usuarioComum.setSenha(scanner.nextLine());
                    System.out.print("CPF: ");
                    usuarioComum.setCpf(scanner.nextLine());
                    System.out.print("Telefone: ");
                    usuarioComum.setTelefone(scanner.nextLine());

                    repositorioContaUsuario.inserirConta(usuarioComum);
                    break;
                case 2:
                    System.out.print("CRIANDO CONTA ADM\nNome: ");
                    scanner.nextLine();
                    usuarioAdm.setNome(scanner.nextLine());
                    System.out.print("Nome usuário: ");
                    usuarioAdm.setNomeUsuario(scanner.nextLine());
                    System.out.print("Email: ");
                    usuarioAdm.setEmail(scanner.nextLine());
                    System.out.print("Senha: ");
                    usuarioAdm.setSenha(scanner.nextLine());
                    System.out.print("CPF: ");
                    usuarioAdm.setCpf(scanner.nextLine());
                    System.out.print("Telefone: ");
                    usuarioAdm.setTelefone(scanner.nextLine());

                    repositorioContaUsuario.inserirConta(usuarioAdm);
                    break;
                case 3:
                    repositorioContaUsuario.exibirContas();
                    break;
                case 4:
                    String usuarioOuEmail;
                    String senha;
                    EnumTipoConta enumTipoConta;
                    int tipo;
                    System.out.println("Digite 1 para ADMINISTRADOR e qualquer número para conta COMUM.");
                    tipo = scanner.nextInt();
                    if (tipo == 1) {
                        enumTipoConta = EnumTipoConta.ADM;
                    } else {
                        enumTipoConta = EnumTipoConta.COMUM;
                    }

                    System.out.print("LOGIN\nNome usuário ou email: ");
                    usuarioOuEmail = scanner.nextLine();
                    while (usuarioOuEmail.isEmpty() || usuarioOuEmail == null) {
                        System.err.print("Nome usuário ou email: ");
                        usuarioOuEmail = scanner.nextLine();
                    }
                    System.out.print("Senha: ");
                    senha = scanner.nextLine();
                    try {
                        repositorioContaUsuario.buscarContaParaLogar(usuarioOuEmail, senha, enumTipoConta);
                    } catch (ContaNaoExisteException contaNaoExisteException) {
                        contaNaoExisteException.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.err.println("Opção inválida, tente novamente.");
                    break;
            }
        } while (opcao != 5);

    }
}
