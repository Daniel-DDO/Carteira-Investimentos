package br.com.investimentos.basicas;

public class UsuarioAdministrador extends Conta {

    public UsuarioAdministrador() {
    }

    public UsuarioAdministrador(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf);
    }

}
