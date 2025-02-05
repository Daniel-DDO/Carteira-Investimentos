package br.com.investimentos.usuarios;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.financas.Investimentos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CarteiraUsuario implements Serializable {
    private long carteiraID;
    private String nomeCarteira;
    private double saldoDisponivel;
    private AtivosFinanceiros[] ativosFinanceiros;
    private LocalDate dataCriacao;
    private String objetivoInvestimento;
    private EnumTipoInvestidor enumTipoInvestidor;
    private UsuarioComum usuario;
    private EnumTipoMoeda enumTipoMoeda;

    @Serial
    private static final long serialVersionUID = 1L;

    public CarteiraUsuario(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor, EnumTipoMoeda enumTipoMoeda, UsuarioComum usuario) {
        this.nomeCarteira = nomeCarteira;
        this.saldoDisponivel = saldoDisponivel;
        this.dataCriacao = dataCriacao;
        this.objetivoInvestimento = objetivoInvestimento;
        this.enumTipoInvestidor = enumTipoInvestidor;
        this.enumTipoMoeda = enumTipoMoeda;
        this.usuario = usuario;
    }

    public void depositarDinheiro(double valorDeposito) {
        this.saldoDisponivel = saldoDisponivel + valorDeposito;
    }

    public void comprarAtivos() {

    }

    public AtivosFinanceiros[] getAtivosFinanceiros() {
        return ativosFinanceiros;
    }

    public void setAtivosFinanceiros(AtivosFinanceiros[] ativosFinanceiros) {
        this.ativosFinanceiros = ativosFinanceiros;
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

    public EnumTipoInvestidor getEnumTipoInvestidor() {
        return enumTipoInvestidor;
    }

    public void setEnumTipoInvestidor(EnumTipoInvestidor enumTipoInvestidor) {
        this.enumTipoInvestidor = enumTipoInvestidor;
    }

    public EnumTipoMoeda getEnumTipoMoeda() {
        return enumTipoMoeda;
    }

    public void setEnumTipoMoeda(EnumTipoMoeda enumTipoMoeda) {
        this.enumTipoMoeda = enumTipoMoeda;
    }

    public UsuarioComum getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioComum usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "CARTEIRA: "+nomeCarteira+"\n";
    }

    public String exibirInformacoesCarteira() {
        return "Nome da carteira: "+nomeCarteira+"\nID carteira: "+carteiraID+"\nSaldo disponível: "+saldoDisponivel+" "+enumTipoMoeda+"\nData de criação: "+dataCriacao
                +"\nObjetivo de investimento: "+objetivoInvestimento+"\nTipo investidor: "+enumTipoInvestidor+"\n";
    }

    public String exibirNoGerenciarCarteiras() {
        return "Carteira: "+nomeCarteira+"  -  ID Carteira: "+carteiraID+
                "\nSaldo disponível: R$ "+saldoDisponivel;
    }
}
