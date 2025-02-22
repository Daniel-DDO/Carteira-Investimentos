package br.com.investimentos.financas;

import br.com.investimentos.usuarios.CarteiraUsuario;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class MetasRentabilidade implements Serializable {

    private double percentualMeta;
    private LocalDate prazoMeta;
    private String observacoes;
    private CarteiraUsuario carteira;
    private EnumStatusMetas status;
    private double rentabilidadeAtual;

    @Serial
    private static final long serialVersionUID = 1L;

    public MetasRentabilidade(double percentualMeta, LocalDate prazoMeta, String observacoes, CarteiraUsuario carteira, EnumStatusMetas status, double rentabilidadeAtual) {
        this.percentualMeta = percentualMeta;
        this.prazoMeta = prazoMeta;
        this.observacoes = observacoes;
        this.carteira = carteira;
        this.status = status;
        this.rentabilidadeAtual = rentabilidadeAtual;
    }

    public String exibirInformacoes() {
        return "Meta definida\n\nPercentual meta: "+percentualMeta+" %\nPrazo meta: "+prazoMeta+"\nRentabilidade atual: "+String.format("%.2f", rentabilidadeAtual)+
                " %\n\nObservações: "+observacoes;
    }

    public double getPercentualMeta() {
        return percentualMeta;
    }

    public void setPercentualMeta(double percentualMeta) {
        this.percentualMeta = percentualMeta;
    }

    public LocalDate getPrazoMeta() {
        return prazoMeta;
    }

    public void setPrazoMeta(LocalDate prazoMeta) {
        this.prazoMeta = prazoMeta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public CarteiraUsuario getCarteira() {
        return carteira;
    }

    public void setCarteira(CarteiraUsuario carteira) {
        this.carteira = carteira;
    }

    public EnumStatusMetas getStatus() {
        return status;
    }

    public void setStatus(EnumStatusMetas status) {
        this.status = status;
    }

    public double getRentabilidadeAtual() {
        return rentabilidadeAtual;
    }

    public void setRentabilidadeAtual(double rentabilidadeAtual) {
        this.rentabilidadeAtual = rentabilidadeAtual;
    }
}
