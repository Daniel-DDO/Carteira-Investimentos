package br.com.investimentos.repositorios;

import br.com.investimentos.financas.ControleSaldo;

public class RepositorioControleSaldo {
    private static RepositorioControleSaldo repositorioControleSaldo;
    private int tamanho = 100;
    private ControleSaldo[] controleSaldo = new ControleSaldo[tamanho];

    public void adicionarSaldo() {

    }

    public void atualizarSaldo() {

    }

    public void removerSaldo() {

    }

    public static RepositorioControleSaldo getRepositorioControleSaldo() {
        return repositorioControleSaldo;
    }

    public static void setRepositorioControleSaldo(RepositorioControleSaldo repositorioControleSaldo) {
        RepositorioControleSaldo.repositorioControleSaldo = repositorioControleSaldo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public ControleSaldo[] getControleSaldo() {
        return controleSaldo;
    }

    public void setControleSaldo(ControleSaldo[] controleSaldo) {
        this.controleSaldo = controleSaldo;
    }
}
