package br.com.investimentos.controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Programa extends Application {

    private static Stage stage;

    //Telas:
    private static Scene telaInicial01;
    private static Scene telaCadastro02;

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
        }
    }

    public static void main(String[] args) {
        launch();
    }
}