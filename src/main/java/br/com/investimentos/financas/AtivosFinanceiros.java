package br.com.investimentos.financas;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class AtivosFinanceiros implements Serializable {

    private String nomeAtivo;
    private String tipoAtivo;
    private String codigo;
    private double valorAtual;
    private double valorNominal;
    private double rentabilidade;
    private double risco;
    private double liquidez;
    private String moeda;
    private LocalDate dataInicial;

    @Serial
    private static final long serialVersionUID = 1L;

    public AtivosFinanceiros() {

    }

    public AtivosFinanceiros(String nomeAtivo, String tipoAtivo, String codigo, double valorAtual, double valorNominal, double rentabilidade, double risco, double liquidez, String moeda, LocalDate dataInicial) {
        this.nomeAtivo = nomeAtivo;
        this.tipoAtivo = tipoAtivo;
        this.codigo = codigo;
        this.valorAtual = valorAtual;
        this.valorNominal = valorNominal;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
        this.liquidez = liquidez;
        this.moeda = moeda;
        this.dataInicial = dataInicial;
    }

    @Override
    public String toString() {
        return "AtivosFinanceiros{" +
                "nomeAtivo= " + nomeAtivo + '\'' +
                ", tipoAtivo= " + tipoAtivo + '\'' +
                ", codigo= " + codigo + '\'' +
                ", valorAtual= " + valorAtual +
                ", valorNominal=" + valorNominal +
                ", rentabilidade= " + rentabilidade +
                ", risco= " + risco +
                ", liquidez= " + liquidez +
                ", moeda= " + moeda + '\'' +
                ", dataInicial= " + dataInicial +
                '}';
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public void setNomeAtivo(String nomeAtivo) {
        this.nomeAtivo = nomeAtivo;
    }

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public double getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public double getRisco() {
        return risco;
    }

    public void setRisco(double risco) {
        this.risco = risco;
    }

    public double getLiquidez() {
        return liquidez;
    }

    public void setLiquidez(double liquidez) {
        this.liquidez = liquidez;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }
}
