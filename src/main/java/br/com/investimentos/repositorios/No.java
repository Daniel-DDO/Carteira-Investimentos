package br.com.investimentos.repositorios;

import br.com.investimentos.usuarios.Conta;

public class No {
    private Conta conta;
    private No proximo;

    public No() {}

    public No(Conta conta) {
        this.conta = conta;
        this.proximo = null;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public Conta getConta() {
        return conta;
    }

    public No getProximo() {
        return proximo;
    }
}
