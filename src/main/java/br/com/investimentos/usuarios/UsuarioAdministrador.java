package br.com.investimentos.usuarios;

public class UsuarioAdministrador extends Conta {
    private String setor;
    private int ativosCadastrados;

    public UsuarioAdministrador() {
        this.setTipoConta(TipoConta.ADM);
    }

    public UsuarioAdministrador(
            String nome, String nomeUsuario, String email, String senha,
            String telefone, String cpf
    ) {
        super(nome, nomeUsuario, email, senha, telefone, cpf, TipoConta.ADM);
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

    @Override
    public String toString() {
        return "CONTA ADM:"+"\nNome: "+getNome()+"\nNome usuário: "+getNomeUsuario()+"\nEmail: "+getEmail()+"\nSenha: "+getSenha()+
                "\nTelefone: "+getTelefone()+"\nCpf: "+getCpf()+"\nTipo Conta: "+getTipoConta()+"\n";
    }
}

