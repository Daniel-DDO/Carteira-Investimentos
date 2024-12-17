package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.usuarios.Conta;

public class RepositorioContas {
    private static RepositorioContas repositorioContas;
    private int tamanho = 100;
    private Conta[] contas = new Conta[tamanho];
    private int posicao = 0;

    public RepositorioContas() {
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

                ControladorArquivos.removerDoArquivo(conta);
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

    public static RepositorioContas getRepositorioContas() {
        if (repositorioContas  == null) {
           repositorioContas = new RepositorioContas();
        }
        return repositorioContas;
    }

    public static void setRepositorioContas(RepositorioContas repositorioContas) {
        RepositorioContas.repositorioContas = repositorioContas;
    }
}
