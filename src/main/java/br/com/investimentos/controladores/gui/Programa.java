package br.com.investimentos.controladores.gui;

import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.adm.ControladorAtivosAdm;
import br.com.investimentos.controladores.gui.adm.ControladorGerenciarAtivosAdm;
import br.com.investimentos.controladores.gui.adm.ControladorOperacoesAdm;
import br.com.investimentos.controladores.gui.adm.ControladorPerfilAdm;
import br.com.investimentos.controladores.gui.comum.*;
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
    private static Scene extratoCarteirasTela;
    private static Scene criarMetaTela;
    private static Scene carteirasOperacoesAdmTela;

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

        FXMLLoader loaderPrincipalComum = new FXMLLoader(getClass().getResource(localFxml+"05-principal-comum.fxml"));
        Parent fxmlTelaPrincipalComum = loaderPrincipalComum.load();
        principalComum = new Scene(fxmlTelaPrincipalComum, 1000, 600);

        FXMLLoader loaderPrincipalAdm = new FXMLLoader(getClass().getResource(localFxml+"06-principal-adm.fxml"));
        Parent fxmlTelaPrincipalAdm = loaderPrincipalAdm.load();
        principalAdm = new Scene(fxmlTelaPrincipalAdm, 1000, 600);

        FXMLLoader loaderSimularInvestimentos = new FXMLLoader(getClass().getResource(localFxml+"05-1-simular-investimentos.fxml"));
        Parent fxmlTelaSimularInvestimentos = loaderSimularInvestimentos.load();
        simularInvestimentosTela = new Scene(fxmlTelaSimularInvestimentos, 1000, 600);
        ControladorSimularInvestimentos controladorSimularInvestimentos = loaderSimularInvestimentos.getController();
        adicionarMudancaTela(controladorSimularInvestimentos);

        FXMLLoader loaderMinhasCarteiras = new FXMLLoader(getClass().getResource(localFxml+"05-2-minhas-carteiras.fxml"));
        Parent fxmlTelaMinhasCarteiras = loaderMinhasCarteiras.load();
        minhasCarteirasTela = new Scene(fxmlTelaMinhasCarteiras, 1000, 600);

        FXMLLoader loaderProjecoesRentabilidade = new FXMLLoader(getClass().getResource(localFxml+"05-3-projecoes-rentabilidade.fxml"));
        Parent fxmlTelaProjecoesRentabilidade = loaderProjecoesRentabilidade.load();
        projecoesRentabilidadeTela = new Scene(fxmlTelaProjecoesRentabilidade, 1000, 600);
        ControladorMetas controladorMetas1 = loaderProjecoesRentabilidade.getController();
        adicionarMudancaTela(controladorMetas1);

        FXMLLoader loaderFundosImobiliarios = new FXMLLoader(getClass().getResource(localFxml+"05-4-fundos-imobiliarios.fxml"));
        Parent fxmlTelaFundosImobiliarios = loaderFundosImobiliarios.load();
        fundosImobiliariosTela = new Scene(fxmlTelaFundosImobiliarios, 1000, 600);
        ControladorProjecoes controladorProjecoes = loaderFundosImobiliarios.getController();
        adicionarMudancaTela(controladorProjecoes);

        FXMLLoader loaderRenda = new FXMLLoader(getClass().getResource(localFxml+"05-5-renda.fxml"));
        Parent fxmlTelaRenda = loaderRenda.load();
        rendaTela = new Scene(fxmlTelaRenda, 1000, 600);

        FXMLLoader loaderPerfilComum = new FXMLLoader(getClass().getResource(localFxml+"05-6-perfil-comum.fxml"));
        Parent fxmlTelaPerfilComum = loaderPerfilComum.load();
        perfilComumTela = new Scene(fxmlTelaPerfilComum, 1000, 600);
        controladorPerfil = loaderPerfilComum.getController();
        adicionarMudancaTela(controladorPerfil);

        FXMLLoader loaderCriarCarteira = new FXMLLoader(getClass().getResource(localFxml+"05-2-1-criar-carteira.fxml"));
        Parent fxmlCriarCarteira = loaderCriarCarteira.load();
        criarCarteiraTela = new Scene(fxmlCriarCarteira, 1000, 600);
        ControladorCarteiras controladorCarteiras = loaderCriarCarteira.getController();
        adicionarMudancaTela(controladorCarteiras);

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

        FXMLLoader loaderExtratoCarteiras = new FXMLLoader(getClass().getResource(localFxml+"05-2-4-extrato-carteiras.fxml"));
        Parent fxmlExtratoCarteiras = loaderExtratoCarteiras.load();
        extratoCarteirasTela = new Scene(fxmlExtratoCarteiras, 1000, 600);
        ControladorExtrato controladorExtrato = loaderExtratoCarteiras.getController();
        adicionarMudancaTela(controladorExtrato);

        FXMLLoader loaderCriarMeta = new FXMLLoader(getClass().getResource(localFxml+"05-3-1-criar-meta.fxml"));
        Parent fxmlCriarMeta = loaderCriarMeta.load();
        criarMetaTela = new Scene(fxmlCriarMeta, 1000, 600);
        ControladorMetas controladorMetas = loaderCriarMeta.getController();
        adicionarMudancaTela(controladorMetas);

        FXMLLoader loaderCarteirasOperacoes = new FXMLLoader(getClass().getResource(localFxml+"06-2-carteiras-operacoes.fxml"));
        Parent fxmlCarteirasOperacoes = loaderCarteirasOperacoes.load();
        carteirasOperacoesAdmTela = new Scene(fxmlCarteirasOperacoes, 1000, 600);
        ControladorOperacoesAdm controladorOperacoesAdm = loaderCarteirasOperacoes.getController();
        adicionarMudancaTela(controladorOperacoesAdm);

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
            case 20:
                stage.setScene(extratoCarteirasTela);
                informarMudancaTela(20, objeto);
                break;
            case 21:
                stage.setScene(criarMetaTela);
                informarMudancaTela(21, objeto);
                break;
            case 22:
                stage.setScene(carteirasOperacoesAdmTela);
                informarMudancaTela(22, objeto);
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