package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.Fachada;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.ExtratoOperacoes;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

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
    @FXML
    private Button excluirCarteiraBotao;

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
        trocarTela(8);
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
        CarteiraUsuario carteiraUsuario = cboxSelecionarCarteira.getValue();

        if (novoSaldo <= 0) {
            ControladorGeral.alertaErro("Depósito na carteira", "O valor para depósito deve ser positivo.");
            return;
        }

        ExtratoOperacoes operacaoDeposito = new ExtratoOperacoes("Depósito", LocalDate.now(), "Depósito na carteira: "+carteiraUsuario.getCarteiraID()+" | Valor: "+String.format("%.2f", novoSaldo)+" "+carteiraUsuario.getEnumTipoMoeda(), novoSaldo);

        carteiraUsuario.adicionarAoExtrato(operacaoDeposito);
        Fachada.getInstancia().adicionarSaldo(novoSaldo, carteiraUsuario);
        RepositorioCarteiras.getInstancia().atualizarCarteira(carteiraUsuario);
        infoCarteira();
        ControladorGeral.alertaInformacao("Depósito na carteira", "Depósito de "+String.format("%.2f",novoSaldo)+" "+carteiraUsuario.getEnumTipoMoeda()+" realizado com sucesso.");
        salddAddField.clear();
    }

    @FXML
    public void botaoExcluirCarteira(ActionEvent actionEvent) {
        if (cboxSelecionarCarteira.getValue() != null) {
            boolean confirmar = ControladorGeral.alertaConfirmacaoComRes("Excluir carteira", "Ao confirmar a operação, tudo referente a carteira selecionada será excluído e perdido. É um processo irreversível. Se deseja continuar, clique em OK. Se deseja abortar a operação, clique em CANCELAR.");
            if (confirmar) {
                CarteiraUsuario carteiraUsuario = cboxSelecionarCarteira.getValue();
                confirmarExclusaoCarteira(carteiraUsuario);
                trocarTela(8);
                ControladorGeral.alertaInformacao("Carteira excluída", "Exclusão feita com sucesso.");
            }
        } else {
            ControladorGeral.alertaErro("Atenção", "Para excluir uma carteira, primeiro selecione.");
        }
    }

    public void confirmarExclusaoCarteira(CarteiraUsuario carteiraUsuario) {
        Fachada.getInstancia().excluirCarteira(carteiraUsuario);
    }
}
