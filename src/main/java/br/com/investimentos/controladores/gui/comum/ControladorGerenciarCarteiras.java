package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ControladorGerenciarCarteiras implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 15) {
            inicializarComboBox();
        }
    }

    @FXML
    public ComboBox<CarteiraUsuario> cboxSelecionarCarteira;
    @FXML
    public Label infoCarteiraSelecionadaLabel;
    @FXML
    public Button botaoVoltar0523;
    @FXML
    public Button botaoConfirmar0523;
    @FXML
    private TableView<?> acoesDisponiveisTable;

    @FXML
    public void voltarBotao0523(ActionEvent actionEvent) {
        Programa.trocarTela(8);
    }

    @FXML
    public void confirmarBotao0523(ActionEvent actionEvent) {
    }

    @FXML
    public void selecionarCarteiraCbox(ActionEvent actionEvent) {
        infoCarteiraSelecionadaLabel.setText(infoCarteira());
    }

    @FXML
    private TableColumn<?, ?> codigoAcao;

    @FXML
    private TableColumn<?, ?> codigoMinhasAcoes;

    @FXML
    private Button comprarBotao;

    @FXML
    private TableView<?> mihasAcoesTable;

    @FXML
    private TableColumn<?, ?> precoAbertura;

    @FXML
    private TableColumn<?, ?> precoAtual;

    @FXML
    private TableColumn<?, ?> precoMaior;

    @FXML
    private TableColumn<?, ?> precoMedioMinhasAcoes;

    @FXML
    private TableColumn<?, ?> precoMenor;

    @FXML
    private TableColumn<?, ?> quantidadeMinhasAcoes;

    @FXML
    private Button venderBotao;

    @FXML
    void botaoComprar(ActionEvent event) {
        abrirCompraVenda();
    }

    @FXML
    void botaoVender(ActionEvent event) {
        abrirCompraVenda();
    }

    private Stage novaJanela;

    public void abrirCompraVenda() {
        if (novaJanela != null && novaJanela.isShowing()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/investimentos/controladores/confirmar-compra-venda.fxml"));
            Parent root = loader.load();
            novaJanela = new Stage();
            novaJanela.setTitle("Digite a quantidade:");

            Scene sceneNovaJanela = new Scene(root, 400, 250);
            novaJanela.setResizable(false);
            novaJanela.setScene(sceneNovaJanela);
            novaJanela.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Tela compra ou venda

    @FXML
    private Label compraVendaLabel;

    @FXML
    private Button confirmarCompraVendaBotao;

    @FXML
    private TextField quantidadeCompVendField;

    @FXML
    void botaoConfirmarCompraVenda(ActionEvent event) {

    }

    //final dessa tela extra


    public String infoCarteira() {
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira.getSelectionModel().getSelectedItem();
        String informacoes;

        if (carteiraSelecionada != null) {
            informacoes = carteiraSelecionada.exibirNoGerenciarCarteiras();
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
}
