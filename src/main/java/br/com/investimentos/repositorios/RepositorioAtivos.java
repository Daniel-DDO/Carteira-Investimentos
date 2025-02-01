package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.financas.AtivosFinanceiros;

import java.io.*;

public class RepositorioAtivos {

    private static RepositorioAtivos instancia;
    private static int tamanho = 1000;
    private AtivosFinanceiros[] ativosFinanceiros = new AtivosFinanceiros[tamanho];
    private int posicao = 0;

    public static RepositorioAtivos getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioAtivos();
        }
        return instancia;
    }

    private RepositorioAtivos() {
        AtivosFinanceiros[] ativosCarregados = lerAtivos();
        if (ativosCarregados != null) {
            for (AtivosFinanceiros ativo : ativosCarregados) {
                if (ativo != null) {
                    ativosFinanceiros[posicao] = ativo;
                    posicao++;
                }
            }
        }
    }

    public void adicionarAtivos(AtivosFinanceiros ativo) {
        if (posicao < tamanho) {
            ativosFinanceiros[posicao] = ativo;
            posicao++;
            RepositorioAtivos.escreverAtivo(ativo);
        }
    }

    public boolean buscarAtivos(AtivosFinanceiros ativo) {
        boolean ativoEncontrado = false;
        for (int i = 0; i < tamanho; i++) {
            if (ativosFinanceiros[i] != null) {
                if (ativosFinanceiros[i].equals(ativo)) {
                    ativoEncontrado = true;
                }
            }
        }
        return ativoEncontrado;
    }

    public void excluirAtivos(AtivosFinanceiros ativo) {
        for (int i = 0; i < tamanho; i++) {
            if (ativosFinanceiros[i] != null) {
                if (ativosFinanceiros[i].equals(ativo)) {
                    ativosFinanceiros[i] = ativosFinanceiros[posicao-1];
                    ativosFinanceiros[posicao-1] = null;
                    posicao--;
                    break;
                }
            }
        }
        atualizarAtivos(ativosFinanceiros);
    }

    public void exibirTodosAtivos() {
        for (int i = 0; i < tamanho; i++) {
            if (ativosFinanceiros[i] != null) {
                System.out.println(ativosFinanceiros[i]);
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public AtivosFinanceiros[] getAtivosFinanceiros() {
        return ativosFinanceiros;
    }

    public void setAtivosFinanceiros(AtivosFinanceiros[] ativosFinanceiros) {
        this.ativosFinanceiros = ativosFinanceiros;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }


    //Arquivos
    private static final String ATIVOS_ARQUIVO = "Ativos.dat";

    public static void escreverAtivo(AtivosFinanceiros ativo) {
        AtivosFinanceiros[] ativosFinanceiros = lerAtivos();

        if (ativosFinanceiros == null) {
            ativosFinanceiros = new AtivosFinanceiros[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < ativosFinanceiros.length; i++) {
            if (ativosFinanceiros[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            ativosFinanceiros[posicaoLivre] = ativo;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ATIVOS_ARQUIVO))) {
                oos.writeObject(ativosFinanceiros);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array dos ativos está cheio.");
        }
    }

    public static AtivosFinanceiros[] lerAtivos() {
        File ativosArquivo = new File(ATIVOS_ARQUIVO);
        if (!ativosArquivo.exists()) {
            return new AtivosFinanceiros[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ativosArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof AtivosFinanceiros[]) {
                return (AtivosFinanceiros[]) obj;
            } else {
                return new AtivosFinanceiros[tamanho];
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new AtivosFinanceiros[tamanho];
        }
    }

    public static void atualizarAtivos(AtivosFinanceiros[] ativos) {
        AtivosFinanceiros[] ativosAtualizados = new AtivosFinanceiros[ativos.length];
        int posicaoLivre = 0;

        for (AtivosFinanceiros ativo : ativos) {
            if (ativo != null) {
                ativosAtualizados[posicaoLivre] = ativo;
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ATIVOS_ARQUIVO))) {
            oos.writeObject(ativosAtualizados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
