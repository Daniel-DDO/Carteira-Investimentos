package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
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
        for (int i = 0; i < tamanho; i++) {
            if (conta == contas[i]) {
                System.out.println("Conta encontrada.");
            }
        }
    }

    public void atualizarConta(Conta conta) {

    }

    public void removerConta(Conta conta) {
        buscarConta(conta);

    }

    public void exibirContas() {
        for (int i = 0; i < tamanho; i++) {
            if (contas[i] != null) {
                System.out.println(contas[i]);
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
