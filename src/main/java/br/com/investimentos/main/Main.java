package br.com.investimentos.main;

import br.com.investimentos.controladores.*;
import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;

import java.util.Scanner;

import static javafx.application.Application.launch;

public class Main extends Programa {
    public static void main(String[] args) {
        System.out.println("Carteira de Investimentos");

        RepositorioContas repositorioContas = RepositorioContas.getInstancia();
        Conta usuarioComum = new UsuarioComum();
        Conta usuarioAdm = new UsuarioAdministrador();

        //launch();

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

                    repositorioContas.inserirConta(usuarioComum);
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

                    repositorioContas.inserirConta(usuarioAdm);
                    break;
                case 3:
                    repositorioContas.exibirContas();
                    break;
                case 4:
                    String usuarioOuEmail;
                    String senha;
                    System.out.print("LOGIN\nNome usuário ou email: ");
                    usuarioOuEmail = scanner.nextLine();
                    while (usuarioOuEmail.isEmpty() || usuarioOuEmail == null) {
                        System.err.print("Nome usuário ou email: ");
                        usuarioOuEmail = scanner.nextLine();
                    }
                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    repositorioContas.buscarContaParaLogar(usuarioOuEmail, senha);
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    break;
                default:
                    break;
            }
        } while (opcao != 5);
    }
}
