package br.com.investimentos.excecoes;

import br.com.investimentos.controladores.gui.ControladorGeral;

public class AtivoJaExisteException extends RuntimeException {
    public AtivoJaExisteException() {
    }

    public AtivoJaExisteException(String nomeAtivo) {
      super("Já existe um ativo com o nome "+nomeAtivo+". Tente outro.");
      ControladorGeral.alertaErro("Nome ativo", "Já existe um ativo com o nome"+nomeAtivo+". Tente outro.");
    }
}
