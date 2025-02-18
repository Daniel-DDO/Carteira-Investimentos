package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.usuarios.CarteiraUsuario;

import java.io.*;

public class RepositorioCarteiras {
    private static RepositorioCarteiras instancia;
    private static int tamanho = 1000; //quantidade máxima de carteiras
    private CarteiraUsuario[] carteiras = new CarteiraUsuario[tamanho];
    private int posicao = 0;

    private RepositorioCarteiras() {
        CarteiraUsuario[] carteirasCarregadas = lerCarteira();
        if (carteirasCarregadas != null) {
            for (CarteiraUsuario carteiraUsuario : carteirasCarregadas) {
                if (carteiraUsuario != null) {
                    carteiras[posicao] = carteiraUsuario;
                    posicao++;
                }
            }
        }
    }

    public void adicionarCarteira(CarteiraUsuario carteira) {
        if (posicao < tamanho) {
            carteiras[posicao] = carteira;
            posicao++;
            escreverCarteira(carteira);
        } else {

        }
    }

    public void buscarCarteira(CarteiraUsuario carteira) {
        for (int i = 0; i < tamanho; i++) {
            if (carteiras[i] != null) {
                if (carteiras[i].equals(carteira)) {
                    //encontrado
                }
            }
        }
    }

    public void excluirCarteira(CarteiraUsuario carteiraUsuario) {
        for (int i = 0; i < tamanho; i++) {
            if (carteiras[i] != null && carteiras[i].equals(carteiraUsuario)) {
                carteiras[i] = carteiras[posicao-1];
                carteiras[posicao-1] = null;
                posicao--;
                break;
            }
        }
        atualizarCarteiras(carteiras);
    }

    public void atualizarCarteira(CarteiraUsuario carteiraUsuario) {
        for (int i = 0; i < tamanho; i++) {
            if (carteiras[i] != null && carteiras[i].equals(carteiraUsuario)) {
                carteiras[i] = carteiraUsuario;
                break;
            }
        }
        atualizarCarteiras(carteiras);
    }

    public boolean verificarIdCriarCarteira(String idNova) {
        boolean idIgual = false;
        for (int a = 0; a < tamanho; a++) {
            if (carteiras[a] != null) {
                if (idNova.equals(carteiras[a].getCarteiraID())) {
                    idIgual = true;
                }
            }
        }
        return idIgual;
    }

    public static RepositorioCarteiras getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioCarteiras();
        }
        return instancia;
    }

    public static void setInstancia(RepositorioCarteiras instancia) {
        RepositorioCarteiras.instancia = instancia;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public CarteiraUsuario[] getCarteiras() {
        return carteiras;
    }

    public void setCarteiras(CarteiraUsuario[] carteiras) {
        this.carteiras = carteiras;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    //Arquivos
    private static final String CARTEIRAS_ARQUIVO = "Carteiras.dat";

    public static void escreverCarteira(CarteiraUsuario carteira) {
        CarteiraUsuario[] carteiras = lerCarteira();

        if (carteiras == null) {
            carteiras = new CarteiraUsuario[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < carteiras.length; i++) {
            if (carteiras[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            carteiras[posicaoLivre] = carteira;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARTEIRAS_ARQUIVO))) {
                oos.writeObject(carteiras);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array das carteiras está cheio.");
        }
    }

    public static CarteiraUsuario[] lerCarteira() {
        File carteiraArquivo = new File(CARTEIRAS_ARQUIVO);
        if (!carteiraArquivo.exists()) {
            return new CarteiraUsuario[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(carteiraArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof CarteiraUsuario[]) {
                return (CarteiraUsuario[]) obj;
            } else {
                return new CarteiraUsuario[tamanho];
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new CarteiraUsuario[tamanho];
        }
    }

    public static void atualizarCarteiras(CarteiraUsuario[] carteiras) {
        CarteiraUsuario[] carteirasAtualizadas = new CarteiraUsuario[carteiras.length];
        int posicaoLivre = 0;

        for (int i = 0; i < carteiras.length; i++) {
            if (carteiras[i] != null) {
                carteirasAtualizadas[posicaoLivre] =  carteiras[i];
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARTEIRAS_ARQUIVO))) {
            oos.writeObject(carteirasAtualizadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
