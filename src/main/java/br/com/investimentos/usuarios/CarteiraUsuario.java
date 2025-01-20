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
    private TipoInvestidor tipoInvestidor;

    public CarteiraUsuario(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, TipoInvestidor tipoInvestidor) {
        this.nomeCarteira = nomeCarteira;
        this.saldoDisponivel = saldoDisponivel;
        this.dataCriacao = dataCriacao;
        this.objetivoInvestimento = objetivoInvestimento;
        this.tipoInvestidor = tipoInvestidor;
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

    public TipoInvestidor getTipoInvestidor() {
        return tipoInvestidor;
    }

    public void setTipoInvestidor(TipoInvestidor tipoInvestidor) {
        this.tipoInvestidor = tipoInvestidor;
    }
}
