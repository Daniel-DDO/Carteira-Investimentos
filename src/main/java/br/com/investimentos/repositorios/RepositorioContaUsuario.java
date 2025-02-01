package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.usuarios.ContaUsuario;

import java.io.*;

public class RepositorioContaUsuario {
    private static RepositorioContaUsuario instancia;
    private static int tamanho = 100;
    private ContaUsuario[] contaUsuarios = new ContaUsuario[tamanho];
    private int posicao = 0;

    private RepositorioContaUsuario() {
        ContaUsuario[] contasCarregadas = lerDoArquivo();
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
            escreverNoArquivo(novaContaUsuario);
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
        atualizarContas(contaUsuarios);
    }

    public void atualizarContas(ContaUsuario contaUsuario) {
        for (int i = 0; i < tamanho; i++) {
            if (contaUsuarios[i] != null) {
                if (contaUsuarios[i].equals(contaUsuario)) {
                    contaUsuarios[i] = contaUsuario;
                    break;
                }
            }
        }
        atualizarContas(contaUsuarios);
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

    //Arquivos
    private static final String CONTAS_ARQUIVO = "Contas.dat";

    public static void escreverNoArquivo(ContaUsuario contaUsuario) {
        ContaUsuario[] contaUsuarios = lerDoArquivo();

        if (contaUsuarios == null) {
            contaUsuarios = new ContaUsuario[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < contaUsuarios.length; i++) {
            if (contaUsuarios[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            contaUsuarios[posicaoLivre] = contaUsuario;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTAS_ARQUIVO))) {
                oos.writeObject(contaUsuarios);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array de contas está cheio.");
        }

    }

    public static void atualizarContas(ContaUsuario[] contaUsuarios) {
        ContaUsuario[] contasAtualizadas = new ContaUsuario[contaUsuarios.length];
        int posicaoLivre = 0;

        for (int i = 0; i < contaUsuarios.length; i++) {
            if (contaUsuarios[i] != null) {
                contasAtualizadas[posicaoLivre] = contaUsuarios[i];
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTAS_ARQUIVO))) {
            oos.writeObject(contasAtualizadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContaUsuario[] lerDoArquivo() {
        File arquivo = new File(CONTAS_ARQUIVO);
        if (!arquivo.exists()) {
            return new ContaUsuario[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ContaUsuario[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ContaUsuario[tamanho];
        }
    }
}