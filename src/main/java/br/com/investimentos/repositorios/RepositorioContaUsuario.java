package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.gui.ControladorArquivos;
import br.com.investimentos.excecoes.ContaJaExisteExcepction;
import br.com.investimentos.excecoes.ContaNaoExisteException;
import br.com.investimentos.usuarios.ContaUsuario;
import br.com.investimentos.usuarios.EnumTipoConta;

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

    public void atualizarConta(ContaUsuario contaUsuario) {

    }

    public void removerConta(ContaUsuario contaUsuario) {
        boolean encontrado = false;
        for (int i = 0; i < posicao; i++) {
            if (contaUsuario.equals(contaUsuarios[i])) {
                encontrado = true;

                for (int j = i; j < posicao - 1; j++) {
                    contaUsuarios[j] = contaUsuarios[j + 1];
                }
                contaUsuarios[posicao - 1] = null;
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
            if (contaUsuarios[i] != null) {
                System.out.println(contaUsuarios[i]);
            }
        }
    }

    public boolean buscarContaPorNomeUsuario(String nomeUsuario) throws ContaNaoExisteException {
        boolean encontrado = false;
        for (int i = 0; i < tamanho; i++) {
            if (contaUsuarios[i] != null) {
                if (nomeUsuario.equals(contaUsuarios[i].getNomeUsuario())) {
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            throw new ContaNaoExisteException(nomeUsuario);
        }
        return encontrado;
    }

    public boolean buscarContaParaLogar(String usuarioOuEmail, String senha, EnumTipoConta enumTipoConta) throws ContaNaoExisteException {
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
