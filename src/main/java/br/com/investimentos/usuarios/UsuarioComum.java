package br.com.investimentos.usuarios;

import br.com.investimentos.financas.Investimentos;
import br.com.investimentos.financas.RiscosFinanceiros;

import java.time.LocalDate;

public class UsuarioComum extends Conta {

    private Investimentos tipoInvestimento;
    private double orcamentoMensal;
    private String objetivoInvestimento;
    private LocalDate tempoInvestimento;
    private double reservaEmergencia;
    private RiscosFinanceiros riscosFinanceiros;
    private double rendaFixa;

    public UsuarioComum() {}

    public UsuarioComum(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf);

    }

    public UsuarioComum(String nome, String nomeUsuario, String email, String senha,
                        String telefone, String cpf, Investimentos tipoInvestimento,
                        double orcamentoMensal, String objetivoInvestimento, LocalDate tempoInvestimento,
                        double reservaEmergencia, RiscosFinanceiros riscosFinanceiros, double rendaFixa) {
        super(nome, nomeUsuario, email, senha, telefone, cpf);
        this.tipoInvestimento = tipoInvestimento;
        this.orcamentoMensal = orcamentoMensal;
        this.objetivoInvestimento = objetivoInvestimento;
        this.tempoInvestimento = tempoInvestimento;
        this.reservaEmergencia = reservaEmergencia;
        this.riscosFinanceiros = riscosFinanceiros;
        this.rendaFixa = rendaFixa;
    }

    public void visualizarCarteira() {}

    public void gerenciarCarteira() {}

    public Investimentos getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(Investimentos tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public double getOrcamentoMensal() {
        return orcamentoMensal;
    }

    public void setOrcamentoMensal(double orcamentoMensal) {
        this.orcamentoMensal = orcamentoMensal;
    }

    public String getObjetivoInvestimento() {
        return objetivoInvestimento;
    }

    public void setObjetivoInvestimento(String objetivoInvestimento) {
        this.objetivoInvestimento = objetivoInvestimento;
    }

    public LocalDate getTempoInvestimento() {
        return tempoInvestimento;
    }

    public void setTempoInvestimento(LocalDate tempoInvestimento) {
        this.tempoInvestimento = tempoInvestimento;
    }

    public double getReservaEmergencia() {
        return reservaEmergencia;
    }

    public void setReservaEmergencia(double reservaEmergencia) {
        this.reservaEmergencia = reservaEmergencia;
    }

    public RiscosFinanceiros getRiscosFinanceiros() {
        return riscosFinanceiros;
    }

    public void setRiscosFinanceiros(RiscosFinanceiros riscosFinanceiros) {
        this.riscosFinanceiros = riscosFinanceiros;
    }

    public double getRendaFixa() {
        return rendaFixa;
    }

    public void setRendaFixa(double rendaFixa) {
        this.rendaFixa = rendaFixa;
    }

    @Override
    public String toString() {
        return "CONTA:\n" + "Nome: "+getNome()+"\nNome Usuário: "+getNomeUsuario()+"\nEmail: "+getEmail()+
                "\nSenha: "+getSenha()+"\nTelefone: "+getTelefone()+"\nCpf: "+getCpf()+"\n\n";
    }
}
