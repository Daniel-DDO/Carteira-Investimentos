package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.Fachada;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ControladorVerCarteiras implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 14) {
            inicializarComboBox();
            salddAddField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    salddAddField.setText(oldValue);
                }
            });
        }
    }

    @FXML
    private Button botaoVoltar052;
    @FXML
    private Button botaoConfirmar052;
    @FXML
    private ComboBox<CarteiraUsuario> cboxSelecionarCarteira;
    @FXML
    private Label infoCarteiraSelecionadaLabel;
    @FXML
    private Button adicionarSaldoBotao;
    @FXML
    private TextField salddAddField;

    public void selecionarCarteiraCbox(ActionEvent actionEvent){
        infoCarteiraSelecionadaLabel.setText("Detalhes da carteira selecionada:\n\n"
                + infoCarteira());
    }

    public void confirmarBotao052(ActionEvent actionEvent) {
        ControladorGeral.alertaInformacao("Carteiras", "Aqui nessa página você pode visualizar suas carteiras.");
    }

    @FXML
    public void voltarBotao0522(ActionEvent actionEvent) {
        cboxSelecionarCarteira.getItems().clear();
        cboxSelecionarCarteira.setPromptText("Selecione uma carteira");
        Programa.trocarTela(8);
    }

    public String infoCarteira() {
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira.getSelectionModel().getSelectedItem();
        String informacoes;

        if (carteiraSelecionada != null) {
            informacoes = carteiraSelecionada.exibirInformacoesCarteira();
            infoCarteiraSelecionadaLabel.setText(informacoes);
        } else {
            informacoes = "Selecione uma carteira para verificar as informações.";
        }
        return informacoes;
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

    public void inicializarComboBox() {
        if (UsuarioLogado.getInstancia().getUsuarioComum() != null) {
            cboxSelecionarCarteira.getItems().clear();
            visualizarCarteirasCbox();
        }
    }

    @FXML
    public void botaoAdicionarSaldo(ActionEvent event) {
        double novoSaldo = Double.parseDouble(salddAddField.getText());

        Fachada.getInstancia().adicionarSaldo(novoSaldo, cboxSelecionarCarteira.getValue());
        RepositorioCarteiras.getInstancia().atualizarCarteira(cboxSelecionarCarteira.getValue());
        infoCarteira();
    }
}
