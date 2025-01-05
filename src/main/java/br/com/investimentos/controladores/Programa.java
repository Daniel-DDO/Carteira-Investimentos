package br.com.investimentos.controladores;

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

        Parent fxmlTelaPerfilComum = FXMLLoader.load(getClass().getResource(localFxml+"05-6-perfil-comum.fxml"));
        perfilComumTela = new Scene(fxmlTelaPerfilComum, 1000, 600);

        stage.setScene(telaInicial01);
        stage.show();
    }

    public static void trocarTela(int novaTela) {
        switch (novaTela) {
            case 1:
                stage.setScene(telaInicial01);
                break;
            case 2:
                stage.setScene(telaCadastro02);
                break;
            case 3:
                stage.setScene(cadastroComum);
                break;
            case 4:
                stage.setScene(cadastroAdmin);
                break;
            case 5:
                stage.setScene(principalComum);
                break;
            case 6:
                stage.setScene(principalAdm);
                break;
            case 7:
                stage.setScene(simularInvestimentosTela);
                break;
            case 8:
                stage.setScene(minhasCarteirasTela);
                break;
            case 9:
                stage.setScene(projecoesRentabilidadeTela);
                break;
            case 10:
                stage.setScene(fundosImobiliariosTela);
                break;
            case 11:
                stage.setScene(rendaTela);
                break;
            case 12:
                stage.setScene(perfilComumTela);
                break;
        }
    }

    public static Label inserirTextoLabel(Label label, String texto) {
        label.setText(texto);
        return label;
    }

    public static void main(String[] args) {
        launch();
    }
}