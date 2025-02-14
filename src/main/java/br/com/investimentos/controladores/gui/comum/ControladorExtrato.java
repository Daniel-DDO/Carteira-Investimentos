package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.ControladorRelatorios;
import br.com.investimentos.controladores.Fachada;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.financas.ExtratoOperacoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorExtrato implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 20) {
            if (UsuarioLogado.getInstancia().getUsuarioComum() != null) {
                cboxSelecionarCarteira.getItems().clear();
                visualizarCarteirasCbox();

                dataColuna.setCellValueFactory(new PropertyValueFactory<>("dataOperacao"));
                operacoesColuna.setCellValueFactory(new PropertyValueFactory<>("operacao"));
                informacoesColuna.setCellValueFactory(new PropertyValueFactory<>("informacoes"));

                tableExtrato.setItems(extratos);
            }
        }
    }

    @FXML
    private Button botaoVoltar;

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private TableColumn<ExtratoOperacoes, String> dataColuna;

    @FXML
    private TableColumn<ExtratoOperacoes, String> operacoesColuna;

    @FXML
    private TableColumn<ExtratoOperacoes, String> informacoesColuna;

    @FXML
    private TableView<ExtratoOperacoes> tableExtrato;

    private final ObservableList<ExtratoOperacoes> extratos = FXCollections.observableArrayList();

    @FXML
    void confirmarBotao0524(ActionEvent event) {
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira.getValue();
        if (carteiraSelecionada != null) {
            Stage stage = (Stage) tableExtrato.getScene().getWindow();
            Fachada.getInstancia().criarNovoRelatorio(carteiraSelecionada, stage);
        } else {
            System.err.println("Nenhuma carteira foi selecionada.");
        }
    }

    @FXML
    void voltarBotao05224(ActionEvent event) {
        trocarTela(8);
    }

    public void visualizarCarteirasCbox() {
        ArrayList<CarteiraUsuario> carteiraUsuarios = ControladorCarteirasUser.getInstancia()
                .exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());

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

        ExtratoOperacoes[] historico = carteira.retornarOperacoes();
        for (ExtratoOperacoes operacao : historico) {
            if (operacao != null) {
                extratos.add(operacao);
            }
        }

        tableExtrato.setItems(extratos);
    }
}
