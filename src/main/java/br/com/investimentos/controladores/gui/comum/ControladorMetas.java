package br.com.investimentos.controladores.gui.comum;

import br.com.investimentos.controladores.ControladorCarteirasUser;
import br.com.investimentos.controladores.UsuarioLogado;
import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.financas.EnumStatusMetas;
import br.com.investimentos.financas.MetasRentabilidade;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
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
        ControladorGeral.alertaInformacao("Atenção!", "Se você já tem uma meta definida na carteira selecionada, ao criar uma nova meta para ela, a antiga será substituida. Após criar, o processo é irreversível.");
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
    public void criarMetaBotao(ActionEvent event) {
        if (rentabilidadeField.getText().isEmpty()) {
            ControladorGeral.alertaErro("Rentabilidade", "Digite um valor para a rentabilidade.");
            return;
        }
        double rentabilidadeDesejada;
        try {
            rentabilidadeDesejada = Double.parseDouble(rentabilidadeField.getText());
        } catch (NumberFormatException e) {
            ControladorGeral.alertaErro("Rentabilidade", "Digite um número válido para a rentabilidade.");
            return;
        }
        if (rentabilidadeDesejada <= 0) {
            ControladorGeral.alertaErro("Rentabilidade", "Você não pode colocar uma rentabilidade negativa ou igual a 0.");
            return;
        }
        else if (dataMetaPicker.getValue() == null) {
            ControladorGeral.alertaErro("Data", "Você precisa selecionar uma data para continuar.");
            return;
        }

        LocalDate dataSelecionada = dataMetaPicker.getValue();
        LocalDate hoje = LocalDate.now();

        if (!dataSelecionada.isAfter(hoje)) {
            ControladorGeral.alertaErro("Data", "A data precisa ser futura.");
            return;
        }
        else if (observacoesMetaField.getText().isEmpty() || observacoesMetaField.getText().length() < 10) {
            ControladorGeral.alertaErro("Observações", "Você deve digitar pelo menos 10 caracteres na observação.");
            return;
        }
        else if (cboxSelecionarCarteira1.getValue() == null) {
            ControladorGeral.alertaErro("Carteira", "Selecione uma carteira para prosseguir.");
            return;
        }

        String observacoesMeta = observacoesMetaField.getText();
        CarteiraUsuario carteiraSelecionada = cboxSelecionarCarteira1.getValue();

        MetasRentabilidade metasRentabilidade = new MetasRentabilidade(rentabilidadeDesejada, dataSelecionada, observacoesMeta, carteiraSelecionada, EnumStatusMetas.EM_ANDAMENTO, 0.0);
        carteiraSelecionada.setMetasRentabilidade(metasRentabilidade);

        RepositorioCarteiras.getInstancia().atualizarCarteira(carteiraSelecionada);
        ControladorGeral.alertaInformacao("Metas", "Nova meta criada com sucesso.");
        trocarTela(9);
    }


    @FXML
    void voltarBotao0531(ActionEvent event) {
        trocarTela(9);
    }

}
