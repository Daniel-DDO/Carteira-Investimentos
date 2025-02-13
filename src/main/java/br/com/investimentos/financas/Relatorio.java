package br.com.investimentos.financas;

import java.time.LocalDate;

public class Relatorio {

    private String titulo;
    private LocalDate dataGeracao;
    private String conteudo;
    private String tipo;
    private String descricao;

    public Relatorio(String titulo, LocalDate dataGeracao, String conteudo, String tipo, String descricao) {
        this.titulo = titulo;
        this.dataGeracao = dataGeracao;
        this.conteudo = conteudo;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
