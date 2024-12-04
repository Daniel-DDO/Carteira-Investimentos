package br.com.investimentos.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ControladorGeral {
    //Código para telas de transição, para otimizar a criação de controladores

    public static void alertaErro(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Carteira Investimentos");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);
        alert.show();
    }

    public static void alertaInformacao(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Carteira Investimentos");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);
        alert.show();
    }

    public static void alertaConfirmacao(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Carteira Investimentos");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);
        alert.show();
    }
}