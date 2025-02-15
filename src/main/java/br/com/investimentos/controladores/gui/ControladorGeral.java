package br.com.investimentos.controladores.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

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

    public static boolean alertaConfirmacaoComRes(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Carteira Investimentos");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }

}