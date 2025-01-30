package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.financas.AtivosFinanceiros;

public class RepositorioAtivos {

    private static RepositorioAtivos instancia;
    private int tamanho = 1000;
    private AtivosFinanceiros[] ativosFinanceiros = new AtivosFinanceiros[tamanho];
    private int posicao = 0;

    public static RepositorioAtivos getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioAtivos();
        }
        return instancia;
    }

    private RepositorioAtivos() {
        AtivosFinanceiros[] ativosCarregados = ControladorArquivos.lerAtivos();
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
            ControladorArquivos.escreverAtivo(ativo);
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
        ControladorArquivos.atualizarAtivos(ativosFinanceiros);
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
}
