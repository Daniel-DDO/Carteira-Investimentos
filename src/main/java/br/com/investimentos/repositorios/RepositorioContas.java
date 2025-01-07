package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.controladores.ControladorGeral;
import br.com.investimentos.excecoes.ContaJaExisteExcepction;
import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.usuarios.Conta;
import br.com.investimentos.usuarios.TipoConta;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;

public class RepositorioContas {
    private static RepositorioContas instancia;
    private int tamanho = 100;
    private Conta[] contas = new Conta[tamanho];
    private int posicao = 0;

    private RepositorioContas() {
        Conta[] contasCarregadas = ControladorArquivos.lerDoArquivo();
        if (contasCarregadas != null) {
            for (Conta conta : contasCarregadas) {
                if (conta != null) {
                    contas[posicao] = conta;
                    posicao++;
                }
            }
        }
    }

    public void inserirConta(Conta novaConta) {
        if (posicao < tamanho) {
            contas[posicao] = novaConta;
            posicao++;
            ControladorArquivos.escreverNoArquivo(novaConta);
        } else {
            System.err.println("Array de contas está cheio.");
        }

    }

    public void buscarConta(Conta conta) {
        boolean encontrado = false;
        for (int i = 0; i < tamanho; i++) {
            if (conta.equals(contas[i])) {
                encontrado = true;
                System.out.println(encontrado+"\n");
            }
            if (encontrado) {
                break;
            }
        }
    }

    public void atualizarConta(Conta conta) {

    }

    public void removerConta(Conta conta) {
        boolean encontrado = false;
        for (int i = 0; i < posicao; i++) {
            if (conta.equals(contas[i])) {
                encontrado = true;

                for (int j = i; j < posicao - 1; j++) {
                    contas[j] = contas[j + 1];
                }
                contas[posicao - 1] = null;
                posicao--;

                //ControladorArquivos.removerDoArquivo(conta);
                System.out.println("Conta removida.\n");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Conta não encontrada.\n");
        }
    }


    public void exibirContas() {
        for (int i = 0; i < tamanho; i++) {
            if (contas[i] != null) {
                System.out.println(contas[i]);
            }
        }
    }

    public boolean buscarContaPorNomeUsuario(String nomeUsuario) throws ContaNaoExisteException {
        boolean encontrado = false;
        for (int i = 0; i < tamanho; i++) {
            if (contas[i] != null) {
                if (nomeUsuario.equals(contas[i].getNomeUsuario())) {
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            throw new ContaNaoExisteException(nomeUsuario);
        }
        return encontrado;
    }

    public boolean buscarContaParaLogar(String usuarioOuEmail, String senha, TipoConta tipoConta) throws ContaNaoExisteException {
        boolean contaEncontrada = false;
        if (usuarioOuEmail.contains("@")) {
            //considerar o login pelo email e senha
            for (int i = 0; i < tamanho; i++) {
                if (contas[i] != null && contas[i].getTipoConta().equals(tipoConta)) {
                    if (usuarioOuEmail.equals(contas[i].getEmail()) && senha.equals(contas[i].getSenha())) {
                        contaEncontrada = true;
                    }
                }
            }
        } else {
            //considerar o login pelo nomeUsuario e senha
            for (int i = 0; i < tamanho; i++) {
                if (contas[i] != null && contas[i].getTipoConta().equals(tipoConta)) {
                    if (usuarioOuEmail.equals(contas[i].getNomeUsuario()) && senha.equals(contas[i].getSenha())) {
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

    public Conta obterContaParaLogar(String usuarioOuEmail, String senha, TipoConta tipoConta) throws ContaNaoExisteException {
        for (int i = 0; i < tamanho; i++) {
            if (contas[i] != null && contas[i].getTipoConta().equals(tipoConta)) {
                if (usuarioOuEmail.contains("@")) {
                    if (usuarioOuEmail.equals(contas[i].getEmail()) && senha.equals(contas[i].getSenha())) {
                        return contas[i];
                    }
                } else {
                    if (usuarioOuEmail.equals(contas[i].getNomeUsuario()) && senha.equals(contas[i].getSenha())) {
                        return contas[i];
                    }
                }
            }
        }
        throw new ContaNaoExisteException();
    }

    public void verificarInformacoes(String nomeUsuario, String email, String cpf) throws ContaJaExisteExcepction {
        for (int i = 0; i < tamanho; i++) {
            if (contas[i] != null) {
                if (contas[i].getNomeUsuario().equals(nomeUsuario)) {
                    throw new ContaJaExisteExcepction(nomeUsuario);
                } else if (contas[i].getEmail().equals(email)) {
                    throw new ContaJaExisteExcepction(nomeUsuario, email);
                } else if (contas[i].getCpf().equals(cpf)) {
                    throw new ContaJaExisteExcepction(nomeUsuario, email, cpf);
                }
            }
        }
    }

    public Conta[] getContas() {
        return contas;
    }

    public void setContas(Conta[] contas) {
        this.contas = contas;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public static RepositorioContas getInstancia() {
        if (instancia == null) {
           instancia = new RepositorioContas();
        }
        return instancia;
    }

    public static void setInstancia(RepositorioContas instancia) {
        RepositorioContas.instancia = instancia;
    }
}
