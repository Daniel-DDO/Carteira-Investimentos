package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControladorVisualizarCarteiras {

    @FXML
    private Button botaoConfirmar052;

    @FXML
    private Button botaoVoltar052;

    @FXML
    public void initialize() {
        if (UsuarioLogado.getInstancia().getUsuarioComum() == null) {
            UsuarioLogado.getInstancia().setUsuarioComum(new UsuarioComum());
        }
        visualizarCarteirasCbox();
    }

    public void listarCarteirasCbox() {
        if (cboxSelecionarCarteira != null) {
            cboxSelecionarCarteira.getItems().addAll(ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum()));
        }
    }

    public void visualizarCarteirasCbox() {
        CarteiraUsuario[] carteiraUsuarios = ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());

        for (CarteiraUsuario carteiraUsuario : carteiraUsuarios) {
            if (carteiraUsuario != null) {
                cboxSelecionarCarteira.getItems().add(carteiraUsuario);
            }
        }
    }

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private Label infoCarteiraSelecionadaLabel;


    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {
        infoCarteiraSelecionadaLabel.setText("Selecionado");
    }

    @FXML
    void confirmarBotao052(ActionEvent event) {
        //selecionarCarteiraCbox(event);
    }

    @FXML
    void voltarBotao052(ActionEvent event) {
        Programa.trocarTela(8);
    }



}
