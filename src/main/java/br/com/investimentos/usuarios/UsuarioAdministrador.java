package br.com.investimentos.usuarios;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;

import java.time.LocalDate;

public class UsuarioAdministrador extends ContaUsuario {
    private String setor;
    private int ativosCadastrados;

    public UsuarioAdministrador() {
        this.setTipoConta(EnumTipoConta.ADM);
    }

    public UsuarioAdministrador(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf, EnumTipoConta.ADM);
    }

    public static AtivosFinanceiros criarAtivo(String nomeAtivo, String tipoAtivo, String codigo, double valorAtual, double valorNominal,
                                               double rentabilidade, double risco, double liquidez, String moeda, LocalDate dataInicial) {
        AtivosFinanceiros novoAtivo = new AtivosFinanceiros(
                nomeAtivo, tipoAtivo, codigo, valorAtual, valorNominal, rentabilidade, risco, liquidez, moeda, dataInicial
        );

        return novoAtivo;
    }

    public void editarAtivo(AtivosFinanceiros ativoFin) {

    }

    public void removerAtivo() {

    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public int getAtivosCadastrados() {
        return ativosCadastrados;
    }

    public void setAtivosCadastrados(int ativosCadastrados) {
        this.ativosCadastrados = ativosCadastrados;
    }

    @Override
    public String toString() {
        return "CONTA ADM:"+"\nNome: "+getNome()+"\nNome usuário: "+getNomeUsuario()+"\nEmail: "+getEmail()+"\nSenha: "+getSenha()+
                "\nTelefone: "+getTelefone()+"\nCpf: "+getCpf()+"\nTipo Conta: "+getTipoConta()+"\n";
    }
}

