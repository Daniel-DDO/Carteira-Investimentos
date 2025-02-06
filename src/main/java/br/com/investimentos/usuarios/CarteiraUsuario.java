package br.com.investimentos.usuarios;

import br.com.investimentos.controladores.gui.ControladorGeral;
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
    private LocalDate dataCriacao;
    private String objetivoInvestimento;
    private EnumTipoInvestidor enumTipoInvestidor;
    private UsuarioComum usuario;
    private EnumTipoMoeda enumTipoMoeda;
    private int posicao = 0;
    private int tamanho = 400;
    private AtivosFinanceiros[] ativosFinanceiros = new AtivosFinanceiros[400];

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
        this.ativosFinanceiros = new AtivosFinanceiros[tamanho];
    }

    public void depositarDinheiro(double valorDeposito) {
        this.saldoDisponivel = this.saldoDisponivel + valorDeposito;
    }

    public void comprarAtivos(AtivosFinanceiros ativoFinanceiro, int quantidade) {
        if (quantidade <= 0) {
            ControladorGeral.alertaErro("Quantidade Inválida", "A quantidade de ativos deve ser maior que zero.");
            return;
        }

        boolean ativoEncontrado = false;

        for (int i = 0; i < posicao; i++) {
            if (ativosFinanceiros[i].getCodigo().equals(ativoFinanceiro.getCodigo())) {
                ativosFinanceiros[i].adicionarQuantidade(quantidade);
                ativoEncontrado = true;
                break;
            }
        }

        System.out.println(ativosFinanceiros.length);

        if (!ativoEncontrado) {
            if (posicao < ativosFinanceiros.length) {
                ativoFinanceiro.setQuantidade(quantidade);
                ativosFinanceiros[posicao++] = ativoFinanceiro;
            } else {
                ControladorGeral.alertaErro("Limite da Carteira", "Não há espaço para novos ativos na carteira.");
            }
        }
    }

    public void venderAtivos(AtivosFinanceiros ativoFinanceiro, int quantidade) {
        if (quantidade <= 0) {
            ControladorGeral.alertaErro("Quantidade Inválida", "A quantidade de ativos deve ser maior que zero.");
            return;
        }

        boolean ativoEncontrado = false;

        for (int i = 0; i < posicao; i++) {
            if (ativosFinanceiros[i].getCodigo().equals(ativoFinanceiro.getCodigo())) {
                ativoEncontrado = true;

                if (ativosFinanceiros[i].getQuantidade() >= quantidade) {
                    ativosFinanceiros[i].removerQuantidade(quantidade);
                    if (ativosFinanceiros[i].getQuantidade() == 0) {
                        removerAtivoDaCarteira(i);
                    }

                    ControladorGeral.alertaInformacao("Venda Realizada", "Venda de " + quantidade + " unidades do ativo " + ativoFinanceiro.getCodigo() + " efetuada com sucesso.");
                } else {
                    ControladorGeral.alertaErro("Quantidade Insuficiente", "Você não possui quantidade suficiente do ativo para realizar a venda.");
                }
                break;
            }
        }

        if (!ativoEncontrado) {
            ControladorGeral.alertaErro("Ativo Não Encontrado", "O ativo " + ativoFinanceiro.getCodigo() + " não foi encontrado na sua carteira.");
        }
    }


    public String informacoesCarteira() {
        return "Carteira "+nomeCarteira+"\nSaldo disponível: "+saldoDisponivel+" "+enumTipoMoeda;
    }

    public void removerAtivoDaCarteira(int quantidade) {
        for (int i = quantidade; i < posicao - 1; i++) {
            ativosFinanceiros[i] = ativosFinanceiros[i + 1];
        }
        ativosFinanceiros[--posicao] = null;
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
