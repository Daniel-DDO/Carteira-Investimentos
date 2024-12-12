package br.com.investimentos.main;

import br.com.investimentos.controladores.*;
import br.com.investimentos.repositorios.RepositorioContas;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;

import static javafx.application.Application.launch;

public class Main extends Programa {
    public static void main(String[] args) {
        System.out.println("Carteira de Investimentos");
        //launch();

        RepositorioContas repositorioContas = new RepositorioContas();

        Conta usuarioComum1 = new UsuarioComum("José", "jose.silva",
                "jose@email.com", "123456", "81987654321", "11122233344");

        Conta usuarioAdm1 = new UsuarioAdministrador("Flávio", "flavio.junior",
                "fjunior@email.com", "123456", "81981192347", "99988877755");

        repositorioContas.inserirConta(usuarioComum1);
        repositorioContas.inserirConta(usuarioAdm1);

        repositorioContas.exibirContas();

        repositorioContas.buscarConta(usuarioAdm1);

        repositorioContas.removerConta(usuarioAdm1);

        repositorioContas.buscarConta(usuarioAdm1);

        repositorioContas.exibirContas();
    }
}
