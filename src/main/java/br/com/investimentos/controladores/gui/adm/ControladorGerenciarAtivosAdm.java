package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.controladores.gui.Programa;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;
import br.com.investimentos.repositorios.RepositorioCarteiras;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.ContaUsuario;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
            inicializarAcoesUsuarios();
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


    private void inicializarAcoesUsuarios() {
        RepositorioCarteiras repositorioCarteiras = RepositorioCarteiras.getInstancia();
        CarteiraUsuario[] carteiras = repositorioCarteiras.getCarteiras();

        acoesUsuariosTable.getItems().clear();
        if (carteiras != null) {
            for (CarteiraUsuario carteira : carteiras) {
                if (carteira != null) {
                    UsuarioComum usuario = carteira.getUsuario();
                    AtivosFinanceiros[] ativosDaCarteira = carteira.getAtivosFinanceiros();

                    if (ativosDaCarteira != null) {
                        for (AtivosFinanceiros ativo : ativosDaCarteira) {
                            if (ativo != null && ativo.getQuantidade() > 0) {
                                ativo.setNomeAtivo(usuario.getNomeUsuario());
                                acoesUsuariosTable.getItems().add(ativo);
                            }
                        }
                    }
                }
            }
        }

        usuarioAcoesAdq.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomeAtivo()));
        codigoAcoesUsuarios.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        quantidadeAcoesUsuarios.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantidade()).asObject());
        precoMedioAcoesUsuarios.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(String.format("%.2f", cellData.getValue().getPrecoMedio()))).asObject());
    }

    @FXML
    private TableView<AtivosFinanceiros> acoesUsuariosTable;

    @FXML
    private TableColumn<AtivosFinanceiros, String> usuarioAcoesAdq;

    @FXML
    private TableColumn<AtivosFinanceiros, String> codigoAcoesUsuarios;

    @FXML
    private TableColumn<AtivosFinanceiros, Integer> quantidadeAcoesUsuarios;

    @FXML
    private TableColumn<AtivosFinanceiros, Double> precoMedioAcoesUsuarios;

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
