package br.com.investimentos.usuarios;

import java.io.Serial;
import java.io.Serializable;

public abstract class ContaUsuario implements Serializable {
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private EnumTipoConta enumTipoConta;

    @Serial
    private static final long serialVersionUID = 1L;

    public ContaUsuario() {}

    public ContaUsuario(String nome, String nomeUsuario, String email, String senha,
                        String telefone, String cpf, EnumTipoConta enumTipoConta) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.enumTipoConta = enumTipoConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnumTipoConta getTipoConta() {
        return enumTipoConta;
    }

    public void setTipoConta(EnumTipoConta enumTipoConta) {
        this.enumTipoConta = enumTipoConta;
    }
}
