package br.com.investimentos.basicas;

public class UsuarioComum extends Conta {

    public UsuarioComum() {}

    public UsuarioComum(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf);
    }

}
