package br.com.investimentos.controladores;

import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.EnumTipoInvestidor;
import br.com.investimentos.usuarios.UsuarioComum;

import java.time.LocalDate;

public class ControladorCarteirasUser {

    private static ControladorCarteirasUser instacia;

    public static ControladorCarteirasUser getInstancia() {
        if (instacia == null) {
            instacia = new ControladorCarteirasUser();
        }
        return instacia;
    }

    public void criarNovaCarteira(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor, UsuarioComum usuario) {
        CarteiraUsuario carteira = new CarteiraUsuario(nomeCarteira, saldoDisponivel, dataCriacao, objetivoInvestimento, enumTipoInvestidor, usuario);

        RepositorioCarteiras.getInstancia().adicionarCarteira(carteira);
        exibirTodasCarteiras();
    }

    public void exibirCarteirasDoUser() {

    }

    public void exibirTodasCarteiras() {
        CarteiraUsuario[] carteiras = RepositorioCarteiras.getInstancia().getCarteiras();

        for (int i = 0; i < RepositorioCarteiras.getInstancia().getTamanho(); i++) {
            if (carteiras[i] != null) {
                System.out.println(carteiras[i]);
            }
        }
    }

}
