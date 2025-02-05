package br.com.investimentos.controladores;

import br.com.investimentos.excecoes.AtivoJaExisteException;
import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.repositorios.RepositorioAtivos;
import br.com.investimentos.usuarios.UsuarioAdministrador;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class ControladorAtivosFinanceiros {

    private static ControladorAtivosFinanceiros instancia;

    public static ControladorAtivosFinanceiros getInstancia() {
        if (instancia == null) {
            instancia = new ControladorAtivosFinanceiros();
        }
        return instancia;
    }

    public void criarNovoAtivo(String nomeAtivo, String tipoAtivo, String codigo, double valorAtual, double valorNominal, double rentabilidade, double risco, double liquidez, String moeda, LocalDate dataInicial, int quantidadeDisponivel) {
        AtivosFinanceiros novoAtivo = UsuarioAdministrador.criarAtivo(nomeAtivo, tipoAtivo, codigo, valorAtual, valorNominal, rentabilidade, risco, liquidez, moeda, dataInicial, quantidadeDisponivel);
        RepositorioAtivos.getInstancia().adicionarAtivos(novoAtivo);
        RepositorioAtivos.getInstancia().exibirTodosAtivos();
    }

    public boolean verificarNomeAtivo(String nomeAtivo) throws AtivoJaExisteException {
        AtivosFinanceiros[] ativosFinanceiros = RepositorioAtivos.getInstancia().getAtivosFinanceiros();
        boolean nomeAtivoIgual = false;

        for (int i = 0; i < RepositorioAtivos.getInstancia().getTamanho(); i++) {
            if (ativosFinanceiros[i] != null) {
                if (ativosFinanceiros[i].getNomeAtivo().equals(nomeAtivo)) {
                    nomeAtivoIgual = true;
                    throw new AtivoJaExisteException(nomeAtivo);
                }
            }
        }

        return nomeAtivoIgual;
    }

    private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/";

    public static double converter(String moedaOrigem, String moedaDestino, double valor) throws Exception {
        String urlStr = API_URL + moedaOrigem + "-" + moedaDestino;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Erro na requisição: Código " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        String key = moedaOrigem + moedaDestino;
        double taxa = jsonObject.getAsJsonObject(key).get("bid").getAsDouble();

        return valor * taxa;
    }

}
