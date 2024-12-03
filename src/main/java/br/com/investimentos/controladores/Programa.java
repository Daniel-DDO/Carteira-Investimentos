package br.com.investimentos.controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Programa extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String fxmlEndereco = "/resources/br/com/investimentos/cotroladores/";
        FXMLLoader fxmlLoader = new FXMLLoader(Programa.class.getResource("01-tela-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setResizable(false);
        stage.setTitle("Carteira de Investimentos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}