package br.com.investimentos.usuarios;

import br.com.investimentos.financas.Investimentos;

import java.time.LocalDate;

public class CarteiraUsuario {
    private long carteiraID;
    private String nomeCarteira;
    private double saldoDisponivel;
    private Investimentos[] investimentos;
    private LocalDate dataCriacao;
    private String objetivoInvestimento;
    private EnumTipoInvestidor enumTipoInvestidor;

    public CarteiraUsuario(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor) {
        this.nomeCarteira = nomeCarteira;
        this.saldoDisponivel = saldoDisponivel;
        this.dataCriacao = dataCriacao;
        this.objetivoInvestimento = objetivoInvestimento;
        this.enumTipoInvestidor = enumTipoInvestidor;
    }

    public long getCarteiraID() {
        return carteiraID;
    }

    public void setCarteiraID(long carteiraID) {
        this.carteiraID = carteiraID;
    }

    public String getNomeCarteira() {
        return nomeCarteira;
    }

    public void setNomeCarteira(String nomeCarteira) {
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

    public EnumTipoInvestidor getTipoInvestidor() {
        return enumTipoInvestidor;
    }

    public void setTipoInvestidor(EnumTipoInvestidor enumTipoInvestidor) {
        this.enumTipoInvestidor = enumTipoInvestidor;
    }
}
