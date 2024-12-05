package br.com.investimentos.usuarios;

public class UsuarioAdministrador extends Conta {
    private String setor;
    private int ativosCadastrados;

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
}

