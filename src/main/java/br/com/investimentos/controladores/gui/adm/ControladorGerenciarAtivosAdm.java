package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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
            inicializar();
        }
    }

    @FXML
    private TableView<AtivosFinanceiros> acoesDisponiveisTable;

    @FXML
    private TableColumn<AtivosFinanceiros, String> codigoAcao;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoAtual;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoAbertura;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoMaior;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoMenor;

    @FXML
    private TableColumn<AtivosFinanceiros, String> moedaLocal;

    public void inicializar() {
        RepositorioAtivos repositorio = RepositorioAtivos.getInstancia();
        AtivosFinanceiros[] ativos = repositorio.getAtivosFinanceiros();

        acoesDisponiveisTable.getItems().clear();
        for (AtivosFinanceiros ativo : ativos) {
            if (ativo != null) {
                acoesDisponiveisTable.getItems().add(ativo);
            }
        }

        codigoAcao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        precoAtual.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrecoAtual()).asObject());
        precoAbertura.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrecoAbertura()).asObject());
        precoMaior.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMaiorPreco()).asObject());
        precoMenor.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMenorPreco()).asObject());
        moedaLocal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMoeda()));
    }

    @FXML
    private TableView<?> acoesUsuariosTable;

    @FXML
    private TableColumn<?, ?> usuarioAcoesAdq;

    @FXML
    private TableColumn<?, ?> codigoAcoesUsuarios;

    @FXML
    private TableColumn<?, ?> quantidadeAcoesUsuarios;

    @FXML
    private TableColumn<?, ?> precoMedioAcoesUsuarios;

    @FXML
    private Button botaoConfirmar0612;

    @FXML
    private Button botaoVoltar0612;

    @FXML
    void confirmarBotao0612(ActionEvent event) {

    }

    @FXML
    void voltarBotao0612(ActionEvent event) {
        Programa.trocarTela(17);
    }
}
