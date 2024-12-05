package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.usuarios.Conta;

import java.io.Serializable;

public class RepositorioContas {

    public void inserirConta(Conta novaConta) {
        ControladorArquivos.escreverNoArquivo(novaConta);
    }

    public void buscarConta(Conta conta) {

    }

    public void atualizarConta(Conta conta) {

    }

    public void removerConta(Conta conta) {

    }
}
