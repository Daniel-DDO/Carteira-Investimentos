package br.com.investimentos.financas;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class AtivosFinanceiros implements Serializable {

    private String nomeAtivo;
    private String tipoAtivo;
    private String codigo;
    private double precoAtual;
    private double precoMedio;
    private double precoAbertura;
    private double maiorPreco;
    private double menorPreco;
    private double valorNominal;
    private double rentabilidade;
    private double risco;
    private double liquidez;
    private String moeda;
    private LocalDate dataInicial;
    private int quantidade;

    @Serial
    private static final long serialVersionUID = 1L;

    public AtivosFinanceiros() {

    }

    public AtivosFinanceiros(String nomeAtivo, String tipoAtivo, String codigo, double precoAtual, double valorNominal, double rentabilidade, double risco, double liquidez, String moeda, LocalDate dataInicial, int quantidade) {
        this.nomeAtivo = nomeAtivo;
        this.tipoAtivo = tipoAtivo;
        this.codigo = codigo;
        this.precoAtual = precoAtual;
        this.valorNominal = valorNominal;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
        this.liquidez = liquidez;
        this.moeda = moeda;
        this.dataInicial = dataInicial;
        this.quantidade = quantidade;
    }

    public AtivosFinanceiros(String codigo, double precoAtual, double precoAbertura, double maiorPreco, double menorPreco, String moeda) {
        this.codigo = codigo;
        this.precoAtual = precoAtual;
        this.precoAbertura = precoAbertura;
        this.maiorPreco = maiorPreco;
        this.menorPreco = menorPreco;
        this.moeda = moeda;
    }

    @Override
    public String toString() {
        return "ATIVO FINANCEIRO"+"\nNome ativo: "+nomeAtivo
                +"\nTipo ativo: "+tipoAtivo
                +"\nCódigo ativo: "+codigo
                +"\nValor atual: "+ precoAtual
                +"\nValor nominal: "+valorNominal
                +"\nRentabilidade: "+rentabilidade
                +"\nRisco: "+risco
                +"\nLiquidez: "+liquidez
                +"\nMoeda: "+moeda
                +"\nData inicial: "+dataInicial
                +"\nQuant. Disponível: "+ quantidade +"\n";
    }

    public String informacoesDoAtivo() {
        return "ATIVO FINANCEIRO\n"+"Código/Nome ação: "+codigo+"\nPreço atual: "+precoAtual+"\nMoeda: "+moeda;
    }

    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void removerQuantidade(int quantidade) {
        this.quantidade -= quantidade;
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

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }

    public double getPrecoAbertura() {
        return precoAbertura;
    }

    public void setPrecoAbertura(double precoAbertura) {
        this.precoAbertura = precoAbertura;
    }

    public double getMaiorPreco() {
        return maiorPreco;
    }

    public void setMaiorPreco(double maiorPreco) {
        this.maiorPreco = maiorPreco;
    }

    public double getMenorPreco() {
        return menorPreco;
    }

    public void setMenorPreco(double menorPreco) {
        this.menorPreco = menorPreco;
    }
}
