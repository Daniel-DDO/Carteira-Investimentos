package br.com.investimentos.financas;

import java.time.LocalDate;

public class Investimentos {
    private int investimentoID;
    private String tipoInvestimento;
    private String descricaoInvest;
    private double valorInvestido;
    private double rentabilidadeAtual;
    private LocalDate dataInvest;

    public int getInvestimentoID() {
        return investimentoID;
    }

    public void setInvestimentoID(int investimentoID) {
        this.investimentoID = investimentoID;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public String getDescricaoInvest() {
        return descricaoInvest;
    }

    public void setDescricaoInvest(String descricaoInvest) {
        this.descricaoInvest = descricaoInvest;
    }

    public double getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public double getRentabilidadeAtual() {
        return rentabilidadeAtual;
    }

    public void setRentabilidadeAtual(double rentabilidadeAtual) {
        this.rentabilidadeAtual = rentabilidadeAtual;
    }

    public LocalDate getDataInvest() {
        return dataInvest;
    }

    public void setDataInvest(LocalDate dataInvest) {
        this.dataInvest = dataInvest;
    }
}
