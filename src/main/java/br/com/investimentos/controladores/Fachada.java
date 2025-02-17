package br.com.investimentos.controladores;

import br.com.investimentos.financas.EnumTipoMoeda;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.EnumTipoInvestidor;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private ControladorRelatorios controladorRelatorios = ControladorRelatorios.getInstancia();
    private ControladorMetasRentabilidade controladorMetasRentabilidade = ControladorMetasRentabilidade.getInstancia();

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

    public ArrayList<CarteiraUsuario> exibirCarteirasAll() {
        return controladorCarteirasUser.exibirCarteirasAll();
    }

        public void adicionarSaldo(double novoSaldo, CarteiraUsuario carteira) {
        controladorCarteirasUser.adicionarSaldo(novoSaldo, carteira);
    }

    public void excluirCarteira(CarteiraUsuario carteiraUsuario) {
        controladorCarteirasUser.excluirCarteira(carteiraUsuario);
    }

    //Ativos

    public static double converter(String moedaOrigem, String moedaDestino, double valor) throws Exception {
        return ControladorAtivosFinanceiros.converter(moedaOrigem, moedaDestino, valor);
    }

    //Relatório

    public void criarNovoRelatorio(CarteiraUsuario carteira, Stage stage) {
        controladorRelatorios.gerarRelatorioExtrato(carteira, stage);
    }

    public void gerarRelatorioGeral(List<CarteiraUsuario> carteiras, Stage stage) {
        controladorRelatorios.gerarRelatorioTodasCarteiras(carteiras, stage);
    }

    //Metas Rentabilidade

    public void atualizarMetasCarteira(CarteiraUsuario carteira) {
        controladorMetasRentabilidade.atualizarMetasCarteira(carteira);
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
