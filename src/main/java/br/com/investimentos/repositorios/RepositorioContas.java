package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.usuarios.Conta;

import java.io.Serializable;

public class RepositorioContas {

    private No cabeca;

    public RepositorioContas() {
        this.cabeca = null;
    }

    public void inserirConta(Conta novaConta) {
        No novoNo = new No(novaConta);
        if (cabeca == null) {
            cabeca = novoNo; //quando a lista é vazia
        } else {
            No atual = cabeca;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novoNo);
        }
        ControladorArquivos.escreverNoArquivo(novaConta);
    }

    public void buscarConta(Conta conta) {

    }

    public void atualizarConta(Conta conta) {

    }

    public void removerConta(Conta conta) {

    }
}
