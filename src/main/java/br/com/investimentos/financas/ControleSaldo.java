package br.com.investimentos.financas;

import br.com.investimentos.usuarios.UsuarioComum;

public class ControleSaldo {

    private UsuarioComum usuario;
    private ControleSaldo[] historicoTransacoes;
    private double saldoAtual;
    private double saldoBloqueado;
    private double saldoTotal;
    private double limiteDisponivel;
    private double limiteGastoMensal;
    private String mesReferencia;

    public UsuarioComum getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioComum usuario) {
        this.usuario = usuario;
    }

    public ControleSaldo[] getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void setHistoricoTransacoes(ControleSaldo[] historicoTransacoes) {
        this.historicoTransacoes = historicoTransacoes;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public double getSaldoBloqueado() {
        return saldoBloqueado;
    }

    public void setSaldoBloqueado(double saldoBloqueado) {
        this.saldoBloqueado = saldoBloqueado;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public double getLimiteGastoMensal() {
        return limiteGastoMensal;
    }

    public void setLimiteGastoMensal(double limiteGastoMensal) {
        this.limiteGastoMensal = limiteGastoMensal;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }
}
