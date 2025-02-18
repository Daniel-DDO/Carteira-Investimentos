package br.com.investimentos.financas;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class ExtratoOperacoes implements Serializable {

    private String operacao;
    private LocalDate dataOperacao;
    private String informacoes;
    private AtivosFinanceiros ativo;
    private int quantidade;
    private double precoUn;

    @Serial
    private static final long serialVersionUID = 1L;

    public ExtratoOperacoes(String operacao, LocalDate dataOperacao, String informacoes, AtivosFinanceiros ativo, double precoUn, int quantidade) {
        this.operacao = operacao;
        this.dataOperacao = dataOperacao;
        this.informacoes = informacoes;
        this.ativo = ativo;
        this.precoUn = precoUn;
        this.quantidade = quantidade;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public LocalDate getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(LocalDate dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public AtivosFinanceiros getAtivo() {
        return ativo;
    }

    public void setAtivo(AtivosFinanceiros ativo) {
        this.ativo = ativo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUn() {
        return precoUn;
    }

    public void setPrecoUn(double precoUn) {
        this.precoUn = precoUn;
    }
}
