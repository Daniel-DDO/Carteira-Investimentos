package br.com.investimentos.controladores;

import br.com.investimentos.excecoes.AtivoJaExisteException;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;
import br.com.investimentos.usuarios.UsuarioAdministrador;

import java.time.LocalDate;

public class ControladorAtivosFinanceiros {

    private static ControladorAtivosFinanceiros instancia;

    public static ControladorAtivosFinanceiros getInstancia() {
        if (instancia == null) {
            instancia = new ControladorAtivosFinanceiros();
        }
        return instancia;
    }

    public void criarNovoAtivo(String nomeAtivo, String tipoAtivo, String codigo, double valorAtual, double valorNominal, double rentabilidade, double risco, double liquidez, String moeda, LocalDate dataInicial, int quantidadeDisponivel) {
        AtivosFinanceiros novoAtivo = UsuarioAdministrador.criarAtivo(nomeAtivo, tipoAtivo, codigo, valorAtual, valorNominal, rentabilidade, risco, liquidez, moeda, dataInicial, quantidadeDisponivel);
        RepositorioAtivos.getInstancia().adicionarAtivos(novoAtivo);
        RepositorioAtivos.getInstancia().exibirTodosAtivos();
    }

    public boolean verificarNomeAtivo(String nomeAtivo) throws AtivoJaExisteException {
        AtivosFinanceiros[] ativosFinanceiros = RepositorioAtivos.getInstancia().getAtivosFinanceiros();
        boolean nomeAtivoIgual = false;

        for (int i = 0; i < RepositorioAtivos.getInstancia().getTamanho(); i++) {
            if (ativosFinanceiros[i] != null) {
                if (ativosFinanceiros[i].getNomeAtivo().equals(nomeAtivo)) {
                    nomeAtivoIgual = true;
                    throw new AtivoJaExisteException(nomeAtivo);
                }
            }
        }

        return nomeAtivoIgual;
    }

}
