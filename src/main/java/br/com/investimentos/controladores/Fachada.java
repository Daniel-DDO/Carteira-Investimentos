package br.com.investimentos.controladores;

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
