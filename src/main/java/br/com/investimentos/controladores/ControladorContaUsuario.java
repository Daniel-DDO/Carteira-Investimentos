package br.com.investimentos.controladores;

import br.com.investimentos.excecoes.ContaJaExisteExcepction;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import br.com.investimentos.usuarios.ContaUsuario;

public class ControladorContaUsuario {

    private static ControladorContaUsuario instancia;

    public static ControladorContaUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ControladorContaUsuario();
        }
        return instancia;
    }

    public void verificarInformacoes(String nomeUsuario, String email, String cpf) throws ContaJaExisteExcepction {
        ContaUsuario[] contaUsuarios = RepositorioContaUsuario.getInstancia().getContas();

        for (int i = 0; i < RepositorioContaUsuario.getInstancia().getTamanho(); i++) {
            if (contaUsuarios[i] != null) {
                if (contaUsuarios[i].getNomeUsuario().equals(nomeUsuario)) {
                    throw new ContaJaExisteExcepction(nomeUsuario);
                } else if (contaUsuarios[i].getEmail().equals(email)) {
                    throw new ContaJaExisteExcepction(nomeUsuario, email);
                } else if (contaUsuarios[i].getCpf().equals(cpf)) {
                    throw new ContaJaExisteExcepction(nomeUsuario, email, cpf);
                }
            }
        }
    }



}
