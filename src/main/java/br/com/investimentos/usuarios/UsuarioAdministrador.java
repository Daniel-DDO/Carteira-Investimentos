package br.com.investimentos.usuarios;

public class UsuarioAdministrador extends Conta {

    public UsuarioAdministrador() {
    }

    public UsuarioAdministrador(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf);
    }

    public void criarAtivo() {}

    public void editarAtivo() {}

    public void removerAtivo() {}
}
