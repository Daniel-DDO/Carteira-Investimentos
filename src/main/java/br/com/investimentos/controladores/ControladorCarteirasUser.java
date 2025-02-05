package br.com.investimentos.controladores;

import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.EnumTipoInvestidor;
import br.com.investimentos.usuarios.UsuarioComum;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorCarteirasUser {

    private static ControladorCarteirasUser instacia;

    public static ControladorCarteirasUser getInstancia() {
        if (instacia == null) {
            instacia = new ControladorCarteirasUser();
        }
        return instacia;
    }

    public void criarNovaCarteira(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor, EnumTipoMoeda enumTipoMoeda, UsuarioComum usuario) {
        CarteiraUsuario carteira = new CarteiraUsuario(nomeCarteira, saldoDisponivel, dataCriacao, objetivoInvestimento, enumTipoInvestidor, enumTipoMoeda, usuario);

        RepositorioCarteiras.getInstancia().adicionarCarteira(carteira);
        UsuarioLogado.getInstancia().getUsuarioComum().setQuantidadeCarteiras(UsuarioLogado.getInstancia().getUsuarioComum().getQuantidadeCarteiras() + 1);
        RepositorioContaUsuario.getInstancia().atualizarContas(UsuarioLogado.getInstancia().getUsuarioComum());
        exibirTodasCarteiras();
    }

    public ArrayList<CarteiraUsuario> exibirCarteirasDoUser(UsuarioComum usuario) {
        CarteiraUsuario[] carteiras = RepositorioCarteiras.getInstancia().getCarteiras();
        ArrayList<CarteiraUsuario> carteirastemp = new ArrayList<CarteiraUsuario>();

        for (int i = 0; i < RepositorioCarteiras.getInstancia().getTamanho(); i++) {
            if (carteiras[i] != null) {
                if (carteiras[i].getUsuario().getNomeUsuario().equals(usuario.getNomeUsuario())) {
                    System.out.println(carteiras[i]);
                    carteirastemp.add(carteiras[i]);

                }
            }
        }
        return carteirastemp;
    }

    public void exibirTodasCarteiras() {
        CarteiraUsuario[] carteiras = RepositorioCarteiras.getInstancia().getCarteiras();

        for (int i = 0; i < RepositorioCarteiras.getInstancia().getTamanho(); i++) {
            if (carteiras[i] != null) {
                System.out.println(carteiras[i]);
            }
        }
    }

    public ArrayList<CarteiraUsuario> exibirCarteiraSelecionada(UsuarioComum usuario, long idCarteira) {
        CarteiraUsuario[] carteiras = RepositorioCarteiras.getInstancia().getCarteiras();
        ArrayList<CarteiraUsuario> carteiraSelecionada = new ArrayList<>();

        for (int i = 0; i < RepositorioCarteiras.getInstancia().getTamanho(); i++) {
            if (carteiras[i] != null && carteiras[i].getUsuario().getNomeUsuario().equals(usuario.getNomeUsuario())) {
                if (carteiras[i].getCarteiraID() == idCarteira) {
                    carteiraSelecionada.add(carteiras[i]);
                }
            }
        }

        return carteiraSelecionada;
    }

    public boolean verificarSeTemCarteira(UsuarioComum usuario) {
        boolean temCarteira = false;

        if (UsuarioLogado.getInstancia().getUsuarioComum().getQuantidadeCarteiras() > 0) {
            temCarteira = true;
            exibirCarteirasDoUser(usuario);
        } else {
            System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario()+" não tem carteiras cadastradas.");
        }

        return temCarteira;
    }

    public void adicionarSaldo(double novoSaldo, CarteiraUsuario carteira) {
        CarteiraUsuario[] carteiras = RepositorioCarteiras.getInstancia().getCarteiras();

        for (int i = 0; i < RepositorioCarteiras.getInstancia().getTamanho(); i++) {
            if (carteira.equals(carteiras[i])) {
                carteira.depositarDinheiro(novoSaldo);
            }
        }
    }

}
