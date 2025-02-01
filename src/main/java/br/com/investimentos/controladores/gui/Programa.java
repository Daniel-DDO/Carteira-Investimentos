package br.com.investimentos.controladores.gui;

import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.adm.ControladorAtivosAdm;
import br.com.investimentos.controladores.gui.adm.ControladorGerenciarAtivosAdm;
import br.com.investimentos.controladores.gui.adm.ControladorPerfilAdm;
import br.com.investimentos.controladores.gui.comum.ControladorGerenciarCarteiras;
import br.com.investimentos.controladores.gui.comum.ControladorPerfil;
import br.com.investimentos.controladores.gui.comum.ControladorVerCarteiras;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Programa extends Application {

    private static Stage stage;

    //Telas:
    private static Scene telaInicial01;
    private static Scene telaCadastro02;
    private static Scene cadastroComum;
    private static Scene cadastroAdmin;
    private static Scene principalComum;
    private static Scene principalAdm;
    private static Scene simularInvestimentosTela;
    private static Scene minhasCarteirasTela;
    private static Scene projecoesRentabilidadeTela;
    private static Scene fundosImobiliariosTela;
    private static Scene rendaTela;
    private static Scene perfilComumTela;
    private static Scene criarCarteiraTela;
    private static Scene visualizarCarteirasTela;
    private static Scene gerenciarCarteirasTela;
    private static Scene perfilAdmTela;
    private static Scene ativosAdmTela;
    private static Scene criarAtivosAdmTela;
    private static Scene gerenciarAtivosAdmTela;

    private static ControladorPerfil controladorPerfil;
    private static ControladorPerfilAdm controladorPerfilAdm;
    private static ControladorAtivosAdm controladorAtivosAdm;
    private static ControladorGerenciarAtivosAdm controladorGerenciarAtivosAdm;

    @Override
    public void start(Stage stage) throws IOException {
        Programa.stage = stage;
        stage.setResizable(false);
        stage.setTitle("Carteira de Investimentos");
        String localFxml = "/br/com/investimentos/controladores/";

        Parent fxmlTelaInicial01 = FXMLLoader.load(getClass().getResource(localFxml+"01-tela-inicial.fxml"));
        telaInicial01 = new Scene(fxmlTelaInicial01, 1000, 600);

        Parent fxmlTelaCadastro02 = FXMLLoader.load(getClass().getResource(localFxml+"02-tela-cadastro.fxml"));
        telaCadastro02 = new Scene(fxmlTelaCadastro02, 1000, 600);

        Parent fxmlTelaCadastro03 = FXMLLoader.load(getClass().getResource(localFxml+"03-cadastro-comum.fxml"));
        cadastroComum = new Scene(fxmlTelaCadastro03, 1000, 600);

        Parent fxmlTelaCadastro04 = FXMLLoader.load(getClass().getResource(localFxml+"04-cadastro-adm.fxml"));
        cadastroAdmin = new Scene(fxmlTelaCadastro04, 1000, 600);

        Parent fxmlTelaPrincipalComum = FXMLLoader.load(getClass().getResource(localFxml+"05-principal-comum.fxml"));
        principalComum = new Scene(fxmlTelaPrincipalComum, 1000, 600);

        Parent fxmlTelaPrincipalAdm = FXMLLoader.load(getClass().getResource(localFxml+"06-principal-adm.fxml"));
        principalAdm = new Scene(fxmlTelaPrincipalAdm, 1000, 600);

        Parent fxmlTelaSimularInvestimentos = FXMLLoader.load(getClass().getResource(localFxml+"05-1-simular-investimentos.fxml"));
        simularInvestimentosTela = new Scene(fxmlTelaSimularInvestimentos, 1000, 600);

        Parent fxmlTelaMinhasCarteiras = FXMLLoader.load(getClass().getResource(localFxml+"05-2-minhas-carteiras.fxml"));
        minhasCarteirasTela = new Scene(fxmlTelaMinhasCarteiras, 1000, 600);

        Parent fxmlTelaProjecoesRentabilidade = FXMLLoader.load(getClass().getResource(localFxml+"05-3-projecoes-rentabilidade.fxml"));
        projecoesRentabilidadeTela = new Scene(fxmlTelaProjecoesRentabilidade, 1000, 600);

        Parent fxmlTelaFundosImobiliarios = FXMLLoader.load(getClass().getResource(localFxml+"05-4-fundos-imobiliarios.fxml"));
        fundosImobiliariosTela = new Scene(fxmlTelaFundosImobiliarios, 1000, 600);

        Parent fxmlTelaRenda = FXMLLoader.load(getClass().getResource(localFxml+"05-5-renda.fxml"));
        rendaTela = new Scene(fxmlTelaRenda, 1000, 600);

        FXMLLoader loaderPerfilComum = new FXMLLoader(getClass().getResource(localFxml+"05-6-perfil-comum.fxml"));
        Parent fxmlTelaPerfilComum = loaderPerfilComum.load();
        perfilComumTela = new Scene(fxmlTelaPerfilComum, 1000, 600);
        controladorPerfil = loaderPerfilComum.getController();

        Parent fxmlCriarCarteira = FXMLLoader.load(getClass().getResource(localFxml+"05-2-1-criar-carteira.fxml"));
        criarCarteiraTela = new Scene(fxmlCriarCarteira, 1000, 600);

        FXMLLoader loaderVisualizarCarteiras = new FXMLLoader(getClass().getResource(localFxml + "05-2-2-visualizar-carteiras.fxml"));
        Parent fxmlVisualizarCarteiras = loaderVisualizarCarteiras.load();
        visualizarCarteirasTela = new Scene(fxmlVisualizarCarteiras, 1000, 600);
        ControladorVerCarteiras controladorVerCarteiras = loaderVisualizarCarteiras.getController();
        adicionarMudancaTela(controladorVerCarteiras);

        FXMLLoader loaderGerenciarCarteiras = new FXMLLoader(getClass().getResource(localFxml + "05-2-3-gerenciar-carteiras.fxml"));
        Parent fxmlGerenciarCarteiras = loaderGerenciarCarteiras.load();
        gerenciarCarteirasTela = new Scene(fxmlGerenciarCarteiras, 1000, 600);
        ControladorGerenciarCarteiras controladorGerenciarCarteiras = loaderGerenciarCarteiras.getController();
        adicionarMudancaTela(controladorGerenciarCarteiras);

        FXMLLoader loaderPerfilAdm = new FXMLLoader(getClass().getResource(localFxml+ "06-6-perfil-adm.fxml"));
        Parent fxmlPerfilAdm = loaderPerfilAdm.load();
        perfilAdmTela = new Scene(fxmlPerfilAdm, 1000, 600);
        controladorPerfilAdm = loaderPerfilAdm.getController();
        adicionarMudancaTela(controladorPerfilAdm);

        FXMLLoader loaderAtivosAdm = new FXMLLoader(getClass().getResource(localFxml+"06-1-ativos-financeiros.fxml"));
        Parent fxmlAtivosAdm = loaderAtivosAdm.load();
        ativosAdmTela = new Scene(fxmlAtivosAdm, 1000, 600);
        controladorAtivosAdm = loaderAtivosAdm.getController();
        adicionarMudancaTela(controladorAtivosAdm);

        FXMLLoader loaderCriarAtivosAdm = new FXMLLoader(getClass().getResource(localFxml+"06-1-1-criar-ativos.fxml"));
        Parent fxmlCriarAtivosAdm = loaderCriarAtivosAdm.load();
        criarAtivosAdmTela = new Scene(fxmlCriarAtivosAdm, 1000, 600);
        controladorAtivosAdm = loaderCriarAtivosAdm.getController();
        adicionarMudancaTela(controladorAtivosAdm);

        FXMLLoader loaderGerenciarAtivosAdm = new FXMLLoader(getClass().getResource(localFxml+"06-1-2-gerenciar-ativos.fxml"));
        Parent fxmlGerenciarAtivosAdm = loaderGerenciarAtivosAdm.load();
        gerenciarAtivosAdmTela = new Scene(fxmlGerenciarAtivosAdm, 1000, 600);
        controladorGerenciarAtivosAdm = loaderGerenciarAtivosAdm.getController();
        adicionarMudancaTela(controladorGerenciarAtivosAdm);

        stage.setScene(telaInicial01);
        stage.show();
    }

    public static void trocarTela(int novaTela, Object objeto) {
        switch (novaTela) {
            case 1:
                stage.setScene(telaInicial01);
                informarMudancaTela(1, objeto);
                UsuarioLogado.getInstancia().limparSessao();
                break;
            case 2:
                stage.setScene(telaCadastro02);
                informarMudancaTela(2, objeto);
                break;
            case 3:
                stage.setScene(cadastroComum);
                informarMudancaTela(3, objeto);
                break;
            case 4:
                stage.setScene(cadastroAdmin);
                informarMudancaTela(4, objeto);
                break;
            case 5:
                stage.setScene(principalComum);
                informarMudancaTela(5, objeto);
                break;
            case 6:
                stage.setScene(principalAdm);
                informarMudancaTela(6, objeto);
                break;
            case 7:
                stage.setScene(simularInvestimentosTela);
                informarMudancaTela(7, objeto);
                break;
            case 8:
                stage.setScene(minhasCarteirasTela);
                informarMudancaTela(8, objeto);
                break;
            case 9:
                stage.setScene(projecoesRentabilidadeTela);
                informarMudancaTela(9, objeto);
                break;
            case 10:
                stage.setScene(fundosImobiliariosTela);
                informarMudancaTela(10, objeto);
                break;
            case 11:
                stage.setScene(rendaTela);
                informarMudancaTela(11, objeto);
                break;
            case 12:
                stage.setScene(perfilComumTela);
                informarMudancaTela(12, objeto);
                break;
            case 13:
                stage.setScene(criarCarteiraTela);
                informarMudancaTela(13, objeto);
                break;
            case 14:
                stage.setScene(visualizarCarteirasTela);
                informarMudancaTela(14, objeto);
                break;
            case 15:
                stage.setScene(gerenciarCarteirasTela);
                informarMudancaTela(15, objeto);
                break;
            case 16:
                stage.setScene(perfilAdmTela);
                informarMudancaTela(16, objeto);
                break;
            case 17:
                stage.setScene(ativosAdmTela);
                informarMudancaTela(17, objeto);
                break;
            case 18:
                stage.setScene(criarAtivosAdmTela);
                informarMudancaTela(18, objeto);
                break;
            case 19:
                stage.setScene(gerenciarAtivosAdmTela);
                informarMudancaTela(19, objeto);
                break;
        }
    }

    public static void trocarTela(int novaTela) {
        trocarTela(novaTela, null);
    }

    public static void inserirTextoLabel(Label label, String texto) {
        label.setText(texto);
    }

    public static ControladorPerfil getControladorPerfil() {
        return controladorPerfil;
    }

    public static ControladorPerfilAdm getControladorPerfilAdm() {
        return controladorPerfilAdm;
    }

    //Passar dados na troca de tela

    private static int maxTelas = 150;
    private static int posicao = 0;
    private static MudancaTela[] mudancaTelas = new MudancaTela[maxTelas];

    private static void addTela(MudancaTela tela) {
        if (posicao < maxTelas) {
            mudancaTelas[posicao] = tela;
            posicao++;
        } else {
            //Atingiu o máximo de telas
        }
    }

    public static void adicionarMudancaTela(MudancaTela novaTela) {
        addTela(novaTela);
    }

    private static void informarMudancaTela(int novaTela, Object objeto) {
        for (MudancaTela m : mudancaTelas) {
            if (m != null) {
                m.mudancaTela(novaTela, objeto);
            }
        }
    }

    //fim

    public static void main(String[] args) {
        launch();
    }
}