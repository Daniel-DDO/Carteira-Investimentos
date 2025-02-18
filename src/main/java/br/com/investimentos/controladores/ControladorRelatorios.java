package br.com.investimentos.controladores;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.financas.ExtratoOperacoes;
import br.com.investimentos.repositorios.RepositorioAtivos;
import br.com.investimentos.usuarios.CarteiraUsuario;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorRelatorios {

    private static ControladorRelatorios instancia;

    public static ControladorRelatorios getInstancia() {
        if (instancia == null) {
            instancia = new ControladorRelatorios();
        }
        return instancia;
    }

    public void gerarRelatorioTodasCarteiras(List<CarteiraUsuario> carteiras, Stage stage) {
        if (carteiras == null || carteiras.isEmpty()) {
            System.err.println("Nenhuma carteira registrada.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório de Todas as Carteiras");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("Relatorio_Todas_Carteiras.pdf");

        File arquivoSelecionado = fileChooser.showSaveDialog(stage);
        if (arquivoSelecionado == null) {
            System.out.println("Operação cancelada pelo usuário.");
            return;
        }

        try {
            PdfWriter writer = new PdfWriter(arquivoSelecionado);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Relatório de Todas as Carteiras").setBold().setFontSize(16));
            document.add(new Paragraph("Data de Geração: " + LocalDate.now()));
            document.add(new Paragraph("\n"));

            for (CarteiraUsuario carteira : carteiras) {
                document.add(new Paragraph("Usuário: " + carteira.getUsuario().getNomeUsuario())
                        .setBold()
                        .setFontSize(14));
                document.add(new Paragraph("Carteira: " + carteira.getNomeCarteira()));
                document.add(new Paragraph("Saldo: " + carteira.getEnumTipoMoeda() + " "
                        + String.format("%.2f", carteira.getSaldoDisponivel())));
                document.add(new Paragraph("\n"));

                Table tabela = new Table(3);
                tabela.addCell(new Cell().add(new Paragraph("Data")).setBold());
                tabela.addCell(new Cell().add(new Paragraph("Operação")).setBold());
                tabela.addCell(new Cell().add(new Paragraph("Detalhes")).setBold());

                ExtratoOperacoes[] historico = carteira.retornarOperacoes();
                if (historico == null || historico.length == 0) {
                    tabela.addCell(" - ");
                    tabela.addCell("Nenhuma operação registrada.");
                    tabela.addCell(" - ");
                } else {
                    for (ExtratoOperacoes operacao : historico) {
                        tabela.addCell(operacao.getDataOperacao().toString());
                        tabela.addCell(operacao.getOperacao());
                        tabela.addCell(operacao.getInformacoes());
                    }
                }

                document.add(tabela);
                document.add(new Paragraph("\n--------------------------------------\n"));
            }

            document.close();
            System.out.println("Relatório de todas as carteiras gerado com sucesso: " + arquivoSelecionado.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não pôde ser criado.");
        }
    }

    public void gerarRelatorioExtrato(CarteiraUsuario carteira, Stage stage) {
        if (carteira == null) {
            System.err.println("Erro: Nenhuma carteira selecionada.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório Detalhado");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("Relatorio_Detalhado_" + carteira.getNomeCarteira().replaceAll(" ", "_") + ".pdf");

        File arquivoSelecionado = fileChooser.showSaveDialog(stage);
        if (arquivoSelecionado == null) {
            System.out.println("Operação cancelada pelo usuário.");
            return;
        }

        try {
            PdfWriter writer = new PdfWriter(arquivoSelecionado);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Relatório - " + carteira.getNomeCarteira()).setBold().setFontSize(16));
            document.add(new Paragraph("Data de Geração: " + LocalDate.now()));

            double valorInvestido = carteira.calcularValorInvestido();
            double saldoAtual = carteira.getSaldoDisponivel();
            double rentabilidade = carteira.calcularRentabilidade();

            document.add(new Paragraph("Valor total investido: " + carteira.getEnumTipoMoeda() + " " + String.format("%.2f", valorInvestido)));
            document.add(new Paragraph("Saldo atual: " + carteira.getEnumTipoMoeda() + " " + String.format("%.2f", saldoAtual)));
            document.add(new Paragraph("Rentabilidade atual: " + String.format("%.2f", rentabilidade) + "%"));
            document.add(new Paragraph("\n"));

            Table tabela = new Table(5);
            tabela.addCell("Data");
            tabela.addCell("Ativo");
            tabela.addCell("Operação");
            tabela.addCell("Quantidade");
            tabela.addCell("Preço Pago");

            for (ExtratoOperacoes operacao : carteira.retornarOperacoes()) {
                tabela.addCell(operacao.getDataOperacao().toString());
                tabela.addCell(operacao.getAtivo().getCodigo());
                tabela.addCell(operacao.getOperacao());
                tabela.addCell(String.valueOf(operacao.getQuantidade()));
                tabela.addCell(carteira.getEnumTipoMoeda() + " " + String.format("%.2f", operacao.getPrecoUn()));
            }

            document.add(tabela);
            document.close();
            System.out.println("Relatório Detalhado gerado com sucesso: " + arquivoSelecionado.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não pôde ser criado.");
        }
    }

    public void gerarRelatorioComparativo(CarteiraUsuario carteira, Stage stage) {
        if (carteira == null) {
            System.err.println("Erro: Nenhuma carteira selecionada.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório Comparativo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("Relatorio_Comparativo_" + carteira.getNomeCarteira().replaceAll(" ", "_") + ".pdf");

        File arquivoSelecionado = fileChooser.showSaveDialog(stage);
        if (arquivoSelecionado == null) {
            System.out.println("Operação cancelada pelo usuário.");
            return;
        }

        try {
            PdfWriter writer = new PdfWriter(arquivoSelecionado);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Relatório Comparativo - " + carteira.getNomeCarteira()).setBold().setFontSize(16));
            document.add(new Paragraph("Data de Geração: " + LocalDate.now()).setFontSize(12));
            document.add(new Paragraph("\n"));

            double valorInvestido = carteira.calcularValorInvestido();
            double saldoAtual = carteira.getSaldoDisponivel();
            double rentabilidadeCarteira = carteira.calcularRentabilidade();
            double rentabilidadeIndex = obterRentabilidadeIndex();

            document.add(new Paragraph("Valor Total Investido: " + carteira.getEnumTipoMoeda() + " " + String.format("%.2f", valorInvestido)));
            document.add(new Paragraph("Saldo Atual: " + carteira.getEnumTipoMoeda() + " " + String.format("%.2f", saldoAtual)));
            document.add(new Paragraph("Rentabilidade da Carteira: " + String.format("%.2f", rentabilidadeCarteira) + "%"));
            document.add(new Paragraph("Rentabilidade do IFIX: " + String.format("%.2f", rentabilidadeIndex) + "%"));
            document.add(new Paragraph("\n"));

            Table tabela = new Table(2);
            tabela.addCell("Métrica");
            tabela.addCell("Valor");

            tabela.addCell("Rentabilidade da Carteira");
            tabela.addCell(String.format("%.2f", rentabilidadeCarteira) + "%");

            tabela.addCell("Rentabilidade do IBOV");
            tabela.addCell(String.format("%.2f", rentabilidadeIndex) + "%");

            document.add(tabela);
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Operações de Compra e Venda").setBold().setFontSize(14));
            Table tabelaOperacoes = new Table(4);
            tabelaOperacoes.addCell("Data");
            tabelaOperacoes.addCell("Tipo");
            tabelaOperacoes.addCell("Ativo");
            tabelaOperacoes.addCell("Quantidade");

            if (carteira.getExtratoOperacoes() != null) {
                for (ExtratoOperacoes operacao : carteira.getExtratoOperacoes()) {
                    if (operacao != null) {
                        tabelaOperacoes.addCell(operacao.getDataOperacao().toString());
                        tabelaOperacoes.addCell(operacao.getOperacao());
                        tabelaOperacoes.addCell(operacao.getAtivo().getCodigo());
                        tabelaOperacoes.addCell(String.valueOf(operacao.getQuantidade()));
                    }
                }
            }

            document.add(tabelaOperacoes);

            document.close();
            System.out.println("Relatório comparativo gerado com sucesso: " + arquivoSelecionado.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não pôde ser criado.");
        }
    }

    private double obterRentabilidadeIndex() {
        String apiKey = "hMags41aWmaF7Df4yoeZAN";
        String ticker = "^BVSP";

        try {
            String urlString = "https://brapi.dev/api/quote/" + ticker + "?token=" + apiKey;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResposta = response.toString();
            if (!jsonResposta.startsWith("{")) {
                System.err.println("Erro: Resposta da API não está no formato esperado.");
                return 0.0;
            }

            JSONObject json = new JSONObject(jsonResposta);
            if (json.has("results")) {
                JSONArray resultados = json.getJSONArray("results");
                if (resultados.length() > 0) {
                    JSONObject dadosTicker = resultados.getJSONObject(0);

                    if (dadosTicker.has("regularMarketPreviousClose") && dadosTicker.has("regularMarketPrice")) {
                        double precoAnterior = dadosTicker.getDouble("regularMarketPreviousClose");
                        double precoAtual = dadosTicker.getDouble("regularMarketPrice");

                        return ((precoAtual - precoAnterior) / precoAnterior) * 100;
                    }
                }
            }
            System.err.println("Erro: Dados do IBOV não encontrados.");
        } catch (Exception e) {
            System.err.println("Erro ao obter rentabilidade do IBOV: " + e.getMessage());
        }
        return 0.0;
    }
}