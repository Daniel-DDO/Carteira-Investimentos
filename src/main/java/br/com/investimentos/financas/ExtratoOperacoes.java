package br.com.investimentos.financas;

import java.time.LocalDate;

public class ExtratoOperacoes {
    private String operacao;
    private LocalDate dataOperacao;
    private String informacoes;

    public ExtratoOperacoes(String operacao, LocalDate dataOperacao, String informacoes) {
        this.operacao = operacao;
        this.dataOperacao = dataOperacao;
        this.informacoes = informacoes;
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
}
