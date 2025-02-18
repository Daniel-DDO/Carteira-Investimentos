package br.com.investimentos.usuarios;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.financas.ExtratoOperacoes;
import br.com.investimentos.financas.MetasRentabilidade;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CarteiraUsuario implements Serializable {
    private long carteiraID;
    private String nomeCarteira;
    private double saldoDisponivel;
    private double saldoInicial;
    private LocalDate dataCriacao;
    private String objetivoInvestimento;
    private EnumTipoInvestidor enumTipoInvestidor;
    private UsuarioComum usuario;
    private EnumTipoMoeda enumTipoMoeda;
    private int posicao = 0;
    private int posicao1 = 0;
    private int tamanho = 400;
    private AtivosFinanceiros[] ativosFinanceiros;
    private ExtratoOperacoes[] extratoOperacoes;
    private MetasRentabilidade metasRentabilidade;

    @Serial
    private static final long serialVersionUID = 1L;

    public CarteiraUsuario(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor, EnumTipoMoeda enumTipoMoeda, UsuarioComum usuario) {
        this.nomeCarteira = nomeCarteira;
        this.saldoInicial = saldoDisponivel;
        this.saldoDisponivel = saldoDisponivel;
        this.dataCriacao = dataCriacao;
        this.objetivoInvestimento = objetivoInvestimento;
        this.enumTipoInvestidor = enumTipoInvestidor;
        this.enumTipoMoeda = enumTipoMoeda;
        this.usuario = usuario;
        this.ativosFinanceiros = new AtivosFinanceiros[tamanho];
        this.extratoOperacoes = new ExtratoOperacoes[tamanho];
    }

    public void depositarDinheiro(double valorDeposito) {
        this.saldoDisponivel = this.saldoDisponivel + valorDeposito;
    }

    public String informacoesCarteira() {
        return "Carteira "+nomeCarteira+"\nSaldo disponível: "+String.format("%.2f", saldoDisponivel)+" "+enumTipoMoeda;
    }

    public void removerAtivoDaCarteira(int quantidade) {
        for (int i = quantidade; i < posicao - 1; i++) {
            ativosFinanceiros[i] = ativosFinanceiros[i + 1];
        }
        ativosFinanceiros[--posicao] = null;
    }

    public void adicionarAoExtrato(ExtratoOperacoes novaOperacao) {
        if (posicao1 < tamanho) {
            extratoOperacoes[posicao1] = novaOperacao;
            posicao1++;
        }
    }

    public ExtratoOperacoes[] retornarOperacoes() {
        ExtratoOperacoes[] operacoes = new ExtratoOperacoes[posicao1];
        for (int i = 0; i < posicao1; i++) {
            operacoes[i] = extratoOperacoes[i];
        }
        return operacoes;
    }

    public double calcularRentabilidade() {
        double valorInicial = this.saldoInicial;
        double valorAtual = this.saldoDisponivel;

        if (valorInicial == 0) {
            return 0;
        }
        return ((valorAtual - valorInicial) / valorInicial) * 100;
    }

    public double calcularValorInvestido() {
        double valorTotal = 0.0;

        for (int i = 0; i < posicao1; i++) {
            ExtratoOperacoes operacao = extratoOperacoes[i];
            if (operacao != null && "Compra".equalsIgnoreCase(operacao.getOperacao())) {
                valorTotal += operacao.getPrecoUn() * operacao.getQuantidade();
            }
        }

        return valorTotal;
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

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao1() {
        return posicao1;
    }

    public void setPosicao1(int posicao1) {
        this.posicao1 = posicao1;
    }

    public ExtratoOperacoes[] getExtratoOperacoes() {
        return extratoOperacoes;
    }

    public void setExtratoOperacoes(ExtratoOperacoes[] extratoOperacoes) {
        this.extratoOperacoes = extratoOperacoes;
    }

    public MetasRentabilidade getMetasRentabilidade() {
        return metasRentabilidade;
    }

    public void setMetasRentabilidade(MetasRentabilidade metasRentabilidade) {
        this.metasRentabilidade = metasRentabilidade;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @Override
    public String toString() {
        return "CARTEIRA: "+nomeCarteira+"\n";
    }

    public String exibirInformacoesCarteira() {
        return "Nome da carteira: "+nomeCarteira+"\nID carteira: "+carteiraID+"\nSaldo disponível: "+String.format("%.2f", saldoDisponivel)+" "+enumTipoMoeda+"\nData de criação: "+dataCriacao
                +"\nObjetivo de investimento: "+objetivoInvestimento+"\nTipo investidor: "+enumTipoInvestidor+"\n";
    }

    public String exibirNoGerenciarCarteiras() {
        return "Carteira: "+nomeCarteira+"  -  ID Carteira: "+carteiraID+
                "\nSaldo disponível: "+String.format("%.2f", saldoDisponivel)+" "+enumTipoMoeda;
    }
}
