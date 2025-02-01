package br.com.investimentos.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {

    private static final String API_KEY = "PZRJA4TYHQXYDI9H";
    private static final String SYMBOL = "AAPL";
    private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
            + SYMBOL + "&interval=1min&apikey=" + API_KEY;

    public static void main(String[] args) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine).append("\n");
                }
                in.close();

                // Exibe a resposta direta no console
                System.out.println("Resposta da API:");
                System.out.println(response.toString());
            } else {
                System.out.println("Erro na requisição: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
