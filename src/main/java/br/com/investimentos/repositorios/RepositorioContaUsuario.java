package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.usuarios.ContaUsuario;

public class RepositorioContaUsuario {
    private static RepositorioContaUsuario instancia;
    private int tamanho = 100;
    private ContaUsuario[] contaUsuarios = new ContaUsuario[tamanho];
    private int posicao = 0;

    private RepositorioContaUsuario() {
        ContaUsuario[] contasCarregadas = ControladorArquivos.lerDoArquivo();
        if (contasCarregadas != null) {
            for (ContaUsuario contaUsuario : contasCarregadas) {
                if (contaUsuario != null) {
                    contaUsuarios[posicao] = contaUsuario;
                    posicao++;
                }
            }
        }
    }

    public void inserirConta(ContaUsuario novaContaUsuario) {
        if (posicao < tamanho) {
            contaUsuarios[posicao] = novaContaUsuario;
            posicao++;
            ControladorArquivos.escreverNoArquivo(novaContaUsuario);
        } else {
            System.err.println("Array de contas está cheio.");
        }

    }

    public void buscarConta(ContaUsuario contaUsuario) {
        boolean encontrado = false;
        for (int i = 0; i < tamanho; i++) {
            if (contaUsuario.equals(contaUsuarios[i])) {
                encontrado = true;
                System.out.println(encontrado+"\n");
            }
            if (encontrado) {
                break;
            }
        }
    }

    public void excluirConta(ContaUsuario contaUsuario) {
        for (int i = 0; i < tamanho; i++) {
            if (contaUsuarios[i] != null) {
                if (contaUsuarios[i].equals(contaUsuario)) {
                    contaUsuarios[i] = contaUsuarios[posicao-1];
                    contaUsuarios[posicao-1] = null;
                    posicao--;
                    break;
                }
            }
        }
        ControladorArquivos.atualizarContas(contaUsuarios);
    }

    public ContaUsuario[] getContas() {
        return contaUsuarios;
    }

    public void setContas(ContaUsuario[] contaUsuarios) {
        this.contaUsuarios = contaUsuarios;
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

    public static RepositorioContaUsuario getInstancia() {
        if (instancia == null) {
           instancia = new RepositorioContaUsuario();
        }
        return instancia;
    }

    public static void setInstancia(RepositorioContaUsuario instancia) {
        RepositorioContaUsuario.instancia = instancia;
    }
}