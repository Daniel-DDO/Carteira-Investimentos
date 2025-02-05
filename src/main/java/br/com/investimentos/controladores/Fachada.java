package br.com.investimentos.controladores;

import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.EnumTipoInvestidor;
import br.com.investimentos.usuarios.UsuarioComum;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fachada {
    private static Fachada instancia;

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    private ControladorAtivosFinanceiros controladorAtivosFinanceiros  = ControladorAtivosFinanceiros.getInstancia();
    private ControladorCarteirasUser controladorCarteirasUser = ControladorCarteirasUser.getInstancia();
    private ControladorContaUsuario controladorContaUsuario = ControladorContaUsuario.getInstancia();

    //Carteiras

    public void criarNovaCarteira(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor, EnumTipoMoeda enumTipoMoeda, UsuarioComum usuario) {
        controladorCarteirasUser.criarNovaCarteira(nomeCarteira, saldoDisponivel, dataCriacao, objetivoInvestimento, enumTipoInvestidor, enumTipoMoeda, usuario);
    }

    public boolean verificarSeTemCarteira(UsuarioComum usuario) {
        return controladorCarteirasUser.verificarSeTemCarteira(usuario);
    }

    public ArrayList<CarteiraUsuario> exibirCarteirasDoUser(UsuarioComum usuario) {
        return controladorCarteirasUser.exibirCarteirasDoUser(usuario);
    }

    public void exibirTodasCarteiras() {
        controladorCarteirasUser.exibirTodasCarteiras();
    }

    public void adicionarSaldo(double novoSaldo, CarteiraUsuario carteira) {
        controladorCarteirasUser.adicionarSaldo(novoSaldo, carteira);
    }

    /*
    private Controle controle;

    public void inserirCarteira() {
        controle.inserirCarteira();
    }

    public void removerCarteira() {
        controle.removerCarteira();
    }
    */
}
