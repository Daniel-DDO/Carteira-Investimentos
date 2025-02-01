package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorGerenciarAtivosAdm implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 19) {
            System.out.println("Tela 19");
        }
    }

    @FXML
    private TableView<?> acoesDisponiveisTable;

    @FXML
    private Button botaoConfirmar0612;

    @FXML
    private Button botaoVoltar0612;

    @FXML
    private TableColumn<?, ?> codigoAcao;

    @FXML
    private TableColumn<?, ?> codigoAcoesUsuarios;

    @FXML
    private TableView<?> mihasAcoesTable;

    @FXML
    private TableColumn<?, ?> moedaLocal;

    @FXML
    private TableColumn<?, ?> precoAbertura;

    @FXML
    private TableColumn<?, ?> precoAtual;

    @FXML
    private TableColumn<?, ?> precoMaior;

    @FXML
    private TableColumn<?, ?> precoMedioAcoesUsuarios;

    @FXML
    private TableColumn<?, ?> precoMenor;

    @FXML
    private TableColumn<?, ?> quantidadeAcoesUsuarios;

    @FXML
    private TableColumn<?, ?> usuarioAcoesAdq;

    @FXML
    void confirmarBotao0612(ActionEvent event) {

    }

    @FXML
    void voltarBotao0612(ActionEvent event) {
        Programa.trocarTela(17);
    }
}
