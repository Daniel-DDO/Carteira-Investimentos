package br.com.investimentos.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorCadastro {

    //Tela pré-cadastro

    @FXML
    private Button botaoAdministrador;

    @FXML
    private Button botaoConfirmar02;

    @FXML
    private Button botaoUserComum;

    @FXML
    private Button botaoVoltar02;

    @FXML
    private Label tipoCadastro;

    @FXML
    public void administrradorBotao(ActionEvent event) {
        Programa.trocarTela(4);
    }

    @FXML
    public void confirmarBotao02(ActionEvent event) {

    }

    @FXML
    public void userComumBotao(ActionEvent event) {
        Programa.trocarTela(3);
    }

    @FXML
    public void voltarBotao02(ActionEvent event) {
        Programa.trocarTela(1); //voltar para a tela principal
    }


    //Tela cadastro de usuário comum

    @FXML
    private Button botaoConfirmar03;

    @FXML
    private Button botaoVoltar03;

    @FXML
    public void confirmarBotao03(ActionEvent event) {

    }

    @FXML
    public void voltarBotao03(ActionEvent event) {
        Programa.trocarTela(2);
    }


    //Tela cadastro de usuário adm
}
