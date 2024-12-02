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

    public UsuarioComum() {}

    public UsuarioComum(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf);
    }

    public void visualizarCarteira() {}

    public void gerenciarCarteira() {}
}
