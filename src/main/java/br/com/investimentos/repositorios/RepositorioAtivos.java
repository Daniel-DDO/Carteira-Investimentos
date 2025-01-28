package br.com.investimentos.repositorios;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.Investimentos;

public class RepositorioAtivos {

    private static RepositorioAtivos instancia;
    private int tamanho = 1000;
    private AtivosFinanceiros[] ativosFinanceiros = new AtivosFinanceiros[tamanho];

    public RepositorioAtivos getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioAtivos();
        }
        return instancia;
    }
    

}
