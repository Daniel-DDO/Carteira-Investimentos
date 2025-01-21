package br.com.investimentos.repositorios;

import br.com.investimentos.controladores.gui.ControladorArquivos;
import br.com.investimentos.usuarios.CarteiraUsuario;

public class RepositorioCarteiras {
    private static RepositorioCarteiras instancia;
    private int tamanho = 100; //quantidade máxima de carteiras
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

    public static RepositorioCarteiras getInstancia() {
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
