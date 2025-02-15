package br.com.investimentos.controladores;

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
        }
    }

    public void atualizarTodasMetas(List<CarteiraUsuario> carteiras) {
        for (CarteiraUsuario carteira : carteiras) {
            atualizarMetasCarteira(carteira);
        }
    }
}
