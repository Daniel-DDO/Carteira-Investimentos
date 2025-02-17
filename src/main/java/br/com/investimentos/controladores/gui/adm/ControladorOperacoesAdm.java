package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.financas.ExtratoOperacoes;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorOperacoesAdm implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 22) {
            if (UsuarioLogado.getInstancia().getUsuarioAdministrador() != null) {
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
    private Button botaoConfirmar0524;

    @FXML
    private Button botaoVoltar0524;

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

    @FXML
    private TableColumn<UsuarioComum, String> usuarioColuna;

    private final ObservableList<ExtratoOperacoes> extratos = FXCollections.observableArrayList();

    @FXML
    void confirmarBotao0524(ActionEvent event) {

    }

    @FXML
    void voltarBotao05224(ActionEvent event) {
        if (tableExtrato != null) {
            tableExtrato.getItems().clear();
        }
        trocarTela(6);
    }

    public void visualizarCarteirasCbox() {
        ArrayList<CarteiraUsuario> carteiraUsuarios = ControladorCarteirasUser.getInstancia().exibirCarteirasAll();

        if (!carteiraUsuarios.isEmpty()) {
            cboxSelecionarCarteira.getItems().clear();
            cboxSelecionarCarteira.setPromptText("Selecione uma carteira");
            cboxSelecionarCarteira.getItems().addAll(carteiraUsuarios);
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