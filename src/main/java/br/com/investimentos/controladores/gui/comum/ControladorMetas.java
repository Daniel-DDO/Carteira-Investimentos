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
                if (cboxSelecionarCarteira == null) {
                    cboxSelecionarCarteira = new ComboBox<CarteiraUsuario>();
                }
                visualizarCarteirasCbox();
            }
            if (novaTela == 21) {
                if (cboxSelecionarCarteira1 == null) {
                    cboxSelecionarCarteira1 = new ComboBox<CarteiraUsuario>();
                }
                visualizarCarteirasCbox1();
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
        System.out.println("Selecionado");
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
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira1;

    public void visualizarCarteirasCbox1() {
        ArrayList<CarteiraUsuario> carteiraUsuarios = ControladorCarteirasUser.getInstancia()
                .exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());

        if (!carteiraUsuarios.isEmpty()) {
            cboxSelecionarCarteira1.getItems().clear();
            cboxSelecionarCarteira1.setPromptText("Selecione uma carteira");
            cboxSelecionarCarteira1.getItems().addAll(carteiraUsuarios);
        } else {
            System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario() + " não tem carteiras.");
        }
    }


    @FXML
    void criarMetaBotao(ActionEvent event) {

    }

    @FXML
    void voltarBotao0531(ActionEvent event) {
        trocarTela(9);
    }

}
