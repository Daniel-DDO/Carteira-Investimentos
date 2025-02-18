package br.com.investimentos.controladores;

import br.com.investimentos.controladores.gui.ControladorGeral;
import br.com.investimentos.financas.EnumStatusMetas;
import br.com.investimentos.financas.MetasRentabilidade;
import br.com.investimentos.usuarios.CarteiraUsuario;

import java.util.List;

public class ControladorMetasRentabilidade {

    private static ControladorMetasRentabilidade instancia;

    private ControladorMetasRentabilidade() {}

    public static ControladorMetasRentabilidade getInstancia() {
        if (instancia == null) {
            instancia = new ControladorMetasRentabilidade();
        }
        return instancia;
    }

    public void atualizarMetasCarteira(CarteiraUsuario carteira) {
        if (carteira == null || carteira.getMetasRentabilidade() == null) {
            return;
        }

        MetasRentabilidade meta = carteira.getMetasRentabilidade();
        double novaRentabilidade = carteira.calcularRentabilidade();
        meta.setRentabilidadeAtual(novaRentabilidade);

        if (novaRentabilidade >= meta.getPercentualMeta()) {
            meta.setStatus(EnumStatusMetas.ATINGIDA);
            exibirAlerta("Parabéns! Você atingiu sua meta de rentabilidade: "+meta.getPercentualMeta()+"%.");
        } else if (novaRentabilidade < meta.getPercentualMeta() * 0.8) {
            meta.setStatus(EnumStatusMetas.ABAIXO_DO_ESPERADO);
            exibirAlerta("Atenção! Sua rentabilidade está muito abaixo da meta. Atual: "
                    +String.format("%.2f", novaRentabilidade) + "%, Meta: "+meta.getPercentualMeta()+"%.");
        }
    }

    private void exibirAlerta(String mensagem) {
        ControladorGeral.alertaInformacao("Alerta de Rentabilidade", mensagem);
    }

    public void atualizarTodasMetas(List<CarteiraUsuario> carteiras) {
        for (CarteiraUsuario carteira : carteiras) {
            atualizarMetasCarteira(carteira);
        }
    }
}
