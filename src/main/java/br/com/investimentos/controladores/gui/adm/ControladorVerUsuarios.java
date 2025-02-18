package br.com.investimentos.controladores.gui.adm;

import br.com.investimentos.controladores.gui.MudancaTela;
import br.com.investimentos.repositorios.RepositorioContaUsuario;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.ContaUsuario;
import br.com.investimentos.usuarios.EnumTipoConta;
import br.com.investimentos.usuarios.UsuarioComum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.util.ArrayList;

import static br.com.investimentos.controladores.gui.Programa.trocarTela;

public class ControladorVerUsuarios implements MudancaTela {

    @Override
    public void mudancaTela(int novaTela, Object objeto) {
        if (novaTela == 23) {
            System.out.println("23");
            infoUserSelecionadoLabel.setText("Informações do usuário selecionado");
            cboxSelecionarUser.setPromptText("Selecione um usuário");
            inicializarCbox();
        }
    }

    @FXML
    private Button botaoConfirmar063;

    @FXML
    private Button botaoVoltar063;

    @FXML
    private ComboBox<ContaUsuario> cboxSelecionarUser;

    @FXML
    private Label infoUserSelecionadoLabel;

    @FXML
    void confirmarBotao063(ActionEvent event) {

    }

    @FXML
    void selecionarUserCbox(ActionEvent event) {
        UsuarioComum contaUsuario = (UsuarioComum) cboxSelecionarUser.getValue();
        if (contaUsuario != null) {
            infoUserSelecionadoLabel.setText(String.valueOf(contaUsuario));
        } else {
            infoUserSelecionadoLabel.setText("Informações do usuário selecionado");
        }
    }

    @FXML
    void voltarBotao063(ActionEvent event) {
        infoUserSelecionadoLabel.setText("Informações do usuário selecionado");
        cboxSelecionarUser.setPromptText("Selecione um usuário");
        trocarTela(6);
    }

    public void inicializarCbox() {
        ContaUsuario[] usuarios = RepositorioContaUsuario.getInstancia().getContas();
        ArrayList<ContaUsuario> contaUsuarios = new ArrayList<>();
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getTipoConta().equals(EnumTipoConta.COMUM)) {
                contaUsuarios.add(usuarios[i]);
            }
        }
        cboxSelecionarUser.getItems().clear();
        cboxSelecionarUser.getItems().addAll(contaUsuarios);

        cboxSelecionarUser.setConverter(new StringConverter<>() {
            @Override
            public String toString(ContaUsuario usuario) {
                return usuario != null ? usuario.getNomeUsuario() : "";
            }

            @Override
            public ContaUsuario fromString(String string) {
                return null;
            }
        });
    }

}
