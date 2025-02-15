package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorMetas implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (UsuarioLogado.getInstancia().getUsuarioComum() != null) {
            if (novaTela == 9) {
                System.out.println("9");
                cboxSelecionarCarteira.getItems().clear();
                visualizarCarteirasCbox();
            }
            if (novaTela == 21) {
                System.out.println("21");
                cboxSelecionarCarteira.getItems().clear();
                visualizarCarteirasCbox();
            }
        }
    }

    @FXML
    private Button botaoConfirmar053;

    @FXML
    private Button botaoVoltar053;

    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;

    @FXML
    private Button criarNovasMetasBotao;

    @FXML
    private Label informacoesMetaLabel;

    @FXML
    private Label statusMetaLabel;

    @FXML
    void botaoCriarNovasMetas(ActionEvent event) {
        trocarTela(21);
    }

    @FXML
    void confirmarBotao053(ActionEvent event) {

    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {

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
    void voltarBotao053(ActionEvent event) {
        trocarTela(5);
    }


    //Tela 05-3-1

    @FXML
    private Button botaoCriarMeta;

    @FXML
    private Button botaoVoltar0531;

    @FXML
    private DatePicker dataMetaPicker;

    @FXML
    private TextArea observacoesMetaField;

    @FXML
    private TextField rentabilidadeField;

    @FXML
    void criarMetaBotao(ActionEvent event) {

    }

    @FXML
    void voltarBotao0531(ActionEvent event) {
        trocarTela(9);
    }

}
