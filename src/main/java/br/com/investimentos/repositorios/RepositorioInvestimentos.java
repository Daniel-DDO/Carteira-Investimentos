package br.com.investimentos.repositorios;

import br.com.investimentos.financas.Investimentos;

public class RepositorioInvestimentos {

    private static RepositorioInvestimentos repositorioInvestimentos;
    private int tamanho = 100;
    private Investimentos[] investimentos = new Investimentos[tamanho];

    public void adicionarInvestimento() {

    }

    public void buscarInvestimentos() {

    }

    public static RepositorioInvestimentos getRepositorioInvestimentos() {
        return repositorioInvestimentos;
    }

    public static void setRepositorioInvestimentos(RepositorioInvestimentos repositorioInvestimentos) {
        RepositorioInvestimentos.repositorioInvestimentos = repositorioInvestimentos;
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
