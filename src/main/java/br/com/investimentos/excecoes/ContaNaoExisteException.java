package br.com.investimentos.excecoes;

public class ContaNaoExisteException extends Exception {
    private String nomeUsuario;
    private String email;

    public ContaNaoExisteException() {
        super("A conta não existe.");
    }

    public ContaNaoExisteException(String nomeUsuario) {
        super("A conta com o nome usuário '"+nomeUsuario+"' não existe.");
        this.nomeUsuario = nomeUsuario;
    }

//    public ContaNaoExisteException(String email) {
//        super("A conta com o nome email '"+email+"' não existe.");
//        this.email = email;
//    }
}
