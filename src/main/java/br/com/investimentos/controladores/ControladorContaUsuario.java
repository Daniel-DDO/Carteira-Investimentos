package br.com.investimentos.controladores;

import br.com.investimentos.excecoes.ContaJaExisteExcepction;
import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import br.com.investimentos.usuarios.ContaUsuario;
import br.com.investimentos.usuarios.EnumTipoConta;

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

    public boolean buscarContaParaLogar(String usuarioOuEmail, String senha, EnumTipoConta enumTipoConta) throws ContaNaoExisteException {
        ContaUsuario[] contaUsuarios = RepositorioContaUsuario.getInstancia().getContas();
        int tamanho = RepositorioContaUsuario.getInstancia().getTamanho();

        boolean contaEncontrada = false;
        if (usuarioOuEmail.contains("@")) {
            //considerar o login pelo email e senha
            for (int i = 0; i < tamanho; i++) {
                if (contaUsuarios[i] != null && contaUsuarios[i].getTipoConta().equals(enumTipoConta)) {
                    if (usuarioOuEmail.equals(contaUsuarios[i].getEmail()) && senha.equals(contaUsuarios[i].getSenha())) {
                        contaEncontrada = true;
                    }
                }
            }
        } else {
            //considerar o login pelo nomeUsuario e senha
            for (int i = 0; i < tamanho; i++) {
                if (contaUsuarios[i] != null && contaUsuarios[i].getTipoConta().equals(enumTipoConta)) {
                    if (usuarioOuEmail.equals(contaUsuarios[i].getNomeUsuario()) && senha.equals(contaUsuarios[i].getSenha())) {
                        contaEncontrada = true;
                    }
                }
            }
        }
        if (!contaEncontrada) {
            throw new ContaNaoExisteException();
        } else {
            System.out.println(contaEncontrada);
        }
        return contaEncontrada;
    }

    public ContaUsuario obterContaParaLogar(String usuarioOuEmail, String senha, EnumTipoConta enumTipoConta) throws ContaNaoExisteException {
        ContaUsuario[] contaUsuarios = RepositorioContaUsuario.getInstancia().getContas();
        int tamanho = RepositorioContaUsuario.getInstancia().getTamanho();

        for (int i = 0; i < tamanho; i++) {
            if (contaUsuarios[i] != null && contaUsuarios[i].getTipoConta().equals(enumTipoConta)) {
                if (usuarioOuEmail.contains("@")) {
                    if (usuarioOuEmail.equals(contaUsuarios[i].getEmail()) && senha.equals(contaUsuarios[i].getSenha())) {
                        return contaUsuarios[i];
                    }
                } else {
                    if (usuarioOuEmail.equals(contaUsuarios[i].getNomeUsuario()) && senha.equals(contaUsuarios[i].getSenha())) {
                        return contaUsuarios[i];
                    }
                }
            }
        }
        throw new ContaNaoExisteException();
    }

    public void exibirContas() {
        ContaUsuario[] contaUsuarios = RepositorioContaUsuario.getInstancia().getContas();
        int tamanho = RepositorioContaUsuario.getInstancia().getTamanho();

        for (int i = 0; i < tamanho; i++) {
            if (contaUsuarios[i] != null) {
                System.out.println(contaUsuarios[i]);
            }
        }
    }
}
