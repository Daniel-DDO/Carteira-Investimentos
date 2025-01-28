package br.com.investimentos.controladores;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;

import java.time.LocalDate;

public class ControladorAtivosFinanceiros {

    private static ControladorAtivosFinanceiros instancia;

    public static ControladorAtivosFinanceiros getInstancia() {
        if (instancia == null) {
            instancia = new ControladorAtivosFinanceiros();
        }
        return instancia;
    }

    public void criarNovoAtivo(String nomeAtivo, String tipoAtivo, String codigo, double valorAtual, double valorNominal, double rentabilidade, double risco, double liquidez, String moeda, LocalDate dataInicial) {
        AtivosFinanceiros novoAtivo = new AtivosFinanceiros(nomeAtivo, tipoAtivo, codigo, valorAtual, valorNominal, rentabilidade, risco, liquidez, moeda, dataInicial);
        RepositorioAtivos.getInstancia().adicionarAtivos(novoAtivo);
        RepositorioAtivos.getInstancia().exibirTodosAtivos();
    }

}
