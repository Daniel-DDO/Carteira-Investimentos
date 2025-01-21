package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.ControladorArquivos;
import br.com.investimentos.usuarios.CarteiraUsuario;

public class RepositorioCarteiras {
    private static RepositorioCarteiras instancia;
    private int tamanho = 1000; //quantidade máxima de carteiras
    private CarteiraUsuario[] carteiras = new CarteiraUsuario[tamanho];
    private int posicao = 0;

    private RepositorioCarteiras() {
        CarteiraUsuario[] carteirasCarregadas = ControladorArquivos.lerCarteira();
        if (carteirasCarregadas == null) {
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
            ControladorArquivos.escreverCarteira(carteira);
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


}
