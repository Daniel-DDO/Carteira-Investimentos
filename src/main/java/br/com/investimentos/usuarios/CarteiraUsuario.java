package br.com.investimentos.usuarios;

import br.com.investimentos.financas.Investimentos;

import java.time.LocalDate;

public class CarteiraUsuario {
    private int carteiraID;
    private int nomeCarteira;
    private double saldoDisponivel;
    private Investimentos[] investimentos;
    private LocalDate dataCriacao;
    private String objetivoInvestimento;

    public int getCarteiraID() {
        return carteiraID;
    }

    public void setCarteiraID(int carteiraID) {
        this.carteiraID = carteiraID;
    }

    public int getNomeCarteira() {
        return nomeCarteira;
    }

    public void setNomeCarteira(int nomeCarteira) {
        this.nomeCarteira = nomeCarteira;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public Investimentos[] getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(Investimentos[] investimentos) {
        this.investimentos = investimentos;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getObjetivoInvestimento() {
        return objetivoInvestimento;
    }

    public void setObjetivoInvestimento(String objetivoInvestimento) {
        this.objetivoInvestimento = objetivoInvestimento;
    }
}
