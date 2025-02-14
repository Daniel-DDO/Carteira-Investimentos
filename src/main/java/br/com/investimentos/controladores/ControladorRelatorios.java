package br.com.investimentos.controladores;

import br.com.investimentos.financas.ExtratoOperacoes;
import br.com.investimentos.usuarios.CarteiraUsuario;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class ControladorRelatorios {

    private static ControladorRelatorios instancia;

    public static ControladorRelatorios getInstancia() {
        if (instancia == null) {
            instancia = new ControladorRelatorios();
        }
        return instancia;
    }

    public void gerarRelatorioExtrato(CarteiraUsuario carteira, Stage stage) {
        if (carteira == null) {
            System.err.println("Erro: Nenhuma carteira selecionada.");
            return;
        }

        ExtratoOperacoes[] historico = carteira.retornarOperacoes();
        if (historico == null || historico.length == 0) {
            System.err.println("Nenhuma operação registrada para essa carteira.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório de Extrato");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("Extrato_" + carteira.getNomeCarteira().replaceAll(" ", "_") + ".pdf");

        File arquivoSelecionado = fileChooser.showSaveDialog(stage);
        if (arquivoSelecionado == null) {
            System.out.println("Operação cancelada pelo usuário.");
            return;
        }

        try {
            PdfWriter writer = new PdfWriter(arquivoSelecionado);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Extrato de Operações - " + carteira.getNomeCarteira()).setBold().setFontSize(16));
            document.add(new Paragraph("Data de Geração: " + LocalDate.now()));
            document.add(new Paragraph("Saldo Atual: "+carteira.getEnumTipoMoeda()+" "+String.format("%.2f", carteira.getSaldoDisponivel())));
            document.add(new Paragraph("\n"));

            Table tabela = new Table(3);
            tabela.addCell("Data");
            tabela.addCell("Operação");
            tabela.addCell("Detalhes");

            for (ExtratoOperacoes operacao : historico) {
                tabela.addCell(operacao.getDataOperacao().toString());
                tabela.addCell(operacao.getOperacao());
                tabela.addCell(operacao.getInformacoes());
            }

            document.add(tabela);
            document.close();
            System.out.println("Relatório de extrato gerado com sucesso: " + arquivoSelecionado.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não pôde ser criado.");
        }
    }
}
