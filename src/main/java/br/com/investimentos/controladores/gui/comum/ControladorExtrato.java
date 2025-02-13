package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorExtrato implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 20) {
            System.out.println("T20");
            if (UsuarioLogado.getInstancia().getUsuarioComum() != null) {
                cboxSelecionarCarteira.getItems().clear();
                visualizarCarteirasCbox();
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
    private TableColumn<?, ?> dataColuna;

    @FXML
    private TableColumn<?, ?> informacoesColuna;

    @FXML
    private TableColumn<?, ?> operacoesColuna;

    @FXML
    private TableView<?> tableExtrato;

    @FXML
    void confirmarBotao0524(ActionEvent event) {

    }

    @FXML
    void selecionarCarteiraCbox(ActionEvent event) {

    }

    @FXML
    void voltarBotao05224(ActionEvent event) {
        trocarTela(8);
    }

    public void visualizarCarteirasCbox() {
        ArrayList<CarteiraUsuario> carteiraUsuarios = ControladorCarteirasUser.getInstancia().exibirCarteirasDoUser(UsuarioLogado.getInstancia().getUsuarioComum());

        boolean temCarteira = false;

        for (int i = 0; i < carteiraUsuarios.size(); i++) {
            if (carteiraUsuarios.get(i) != null) {
                if (carteiraUsuarios.get(i).getUsuario().getNomeUsuario().equals(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario())) {
                    System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario());
                    System.out.println(carteiraUsuarios.get(i).getUsuario().getNomeUsuario());

                    for (int a = 0; a < carteiraUsuarios.size(); a++) {
                        if (carteiraUsuarios.get(a) != null) {
                            if (carteiraUsuarios.get(a).getUsuario().getNomeUsuario().equals(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario())) {
                                System.out.println(carteiraUsuarios.get(a));
                            }
                        }
                    }
                    temCarteira = true;

                    if (cboxSelecionarCarteira != null) {
                        cboxSelecionarCarteira.getItems().clear();
                        cboxSelecionarCarteira.setPromptText("Selecione uma carteira");
                        cboxSelecionarCarteira.getItems().addAll(carteiraUsuarios);
                    }

                }
            }
        }

        if (!temCarteira) {
            System.out.println(UsuarioLogado.getInstancia().getUsuarioComum().getNomeUsuario()+" não tem carteiras.");
        }
    }
}
