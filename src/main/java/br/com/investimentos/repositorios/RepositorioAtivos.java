package br.com.investimentos.repositorios;

import br.com.investimentos.financas.Investimentos;

public class RepositorioAtivos {

    private static RepositorioAtivos repositorioAtivos;
    private int tamanho = 100;
    private Investimentos[] investimentos = new Investimentos[tamanho];

    public void adicionarInvestimento() {

    }

    public void buscarInvestimentos() {

    }

    public void removerInverstimento() {

    }

    public static RepositorioAtivos getRepositorioInvestimentos() {
        return repositorioAtivos;
    }

    public static void setRepositorioInvestimentos(RepositorioAtivos repositorioAtivos) {
        RepositorioAtivos.repositorioAtivos = repositorioAtivos;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Investimentos[] getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(Investimentos[] investimentos) {
        this.investimentos = investimentos;
    }
}
