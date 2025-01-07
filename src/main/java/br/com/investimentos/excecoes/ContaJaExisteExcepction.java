package br.com.investimentos.excecoes;

import br.com.investimentos.controladores.ControladorGeral;

public class ContaJaExisteExcepction extends RuntimeException {
    public ContaJaExisteExcepction() {
    }

    public ContaJaExisteExcepction(String nomeUsuario) {
      super("Já existe alguém cadastrado com o nome usuário: "+nomeUsuario+". Tente outro.");
      ControladorGeral.alertaErro("Erro", "Já existe alguém cadastrado com esse nome usuário. Faça a troca e tente novamente.");
    }

    public ContaJaExisteExcepction(String nomeUsuario, String email) {
      super("Já existe alguém cadastrado com o email: "+email+". Tente outro.");
      ControladorGeral.alertaErro("Erro", "Já existe alguém cadastrado com esse email. Faça a troca e tente novamente.");
    }

    public ContaJaExisteExcepction(String nomeUsuario, String email, String cpf) {
      super("Já existe alguém cadastrado com esse CPF: "+cpf+". Tente outro.");
      ControladorGeral.alertaErro("Erro", "Já existe alguém cadastrado com esse CPF. Faça a troca e tente novamente.");
    }
}
