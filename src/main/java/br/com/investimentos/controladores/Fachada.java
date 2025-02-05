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

    private ControladorArquivos controladorArquivos;
    private ControladorAtivosFinanceiros controladorAtivosFinanceiros;
    private ControladorCarteirasUser controladorCarteirasUser;
    private ControladorContaUsuario controladorContaUsuario;

    //Carteiras

    public void criarNovaCarteira(String nomeCarteira, double saldoDisponivel, LocalDate dataCriacao, String objetivoInvestimento, EnumTipoInvestidor enumTipoInvestidor, EnumTipoMoeda enumTipoMoeda, UsuarioComum usuario) {
        ControladorCarteirasUser.getInstancia().criarNovaCarteira(nomeCarteira, saldoDisponivel, dataCriacao, objetivoInvestimento, enumTipoInvestidor, enumTipoMoeda, usuario);
    }

    public boolean verificarSeTemCarteira(UsuarioComum usuario) {
        return ControladorCarteirasUser.getInstancia().verificarSeTemCarteira(usuario);
    }

    public ArrayList<CarteiraUsuario> exibirCarteirasDoUser(UsuarioComum usuario) {
        return ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(usuario);
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
