package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorExtrato implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 20) {
            if (UsuarioLogado.getInstancia().getUsuarioComum() != null) {
                cboxSelecionarCarteira.getItems().clear();
                visualizarCarteirasCbox();
            }
        }
    }

    @FXML
    private Button botaoVoltar;

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private TableColumn<String, String> dataColuna;

    @FXML
    private TableColumn<String, String> operacoesColuna;

    @FXML
    private TableView<String> tableExtrato;

    private final ObservableList<String> extratos = FXCollections.observableArrayList();

    @FXML
    void confirmarBotao0524(ActionEvent event) {

    }

    @FXML
    void voltarBotao05224(ActionEvent event) {
        trocarTela(8);
    }

    public void visualizarCarteirasCbox() {
        ArrayList<CarteiraUsuario> carteiraUsuarios = ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());

        if (!carteiraUsuarios.isEmpty()) {
            cboxSelecionarCarteira.getItems().clear();
            cboxSelecionarCarteira.setPromptText("Selecione uma carteira");
            cboxSelecionarCarteira.getItems().addAll(carteiraUsuarios);
        } else {
            System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario() + " não tem carteiras.");
        }
    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira.getValue();
        if (carteiraSelecionada != null) {
            atualizarExtrato(carteiraSelecionada);
        }
    }

    private void atualizarExtrato(CarteiraUsuario carteira) {
        extratos.clear();

        String[] historico = carteira.getHistoricoOperacoes();
        for (int i = 0; i < carteira.getPosicao1(); i++) {
            if (historico[i] != null) {
                extratos.add(historico[i]);
            }
        }
        tableExtrato.setItems(extratos);
    }

}
