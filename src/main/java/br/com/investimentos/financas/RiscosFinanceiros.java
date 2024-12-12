package br.com.investimentos.financas;

public class RiscosFinanceiros {

    private String tipoRisco;
    private double probabilidade;
    private double impactoFinanceiro;
    private String descricao;
    private String responsavel;
    private String status;
    private String prazoResolucao;

    public String getTipoRisco() {
        return tipoRisco;
    }

    public void setTipoRisco(String tipoRisco) {
        this.tipoRisco = tipoRisco;
    }

    public double getProbabilidade() {
        return probabilidade;
    }

    public void setProbabilidade(double probabilidade) {
        this.probabilidade = probabilidade;
    }

    public double getImpactoFinanceiro() {
        return impactoFinanceiro;
    }

    public void setImpactoFinanceiro(double impactoFinanceiro) {
        this.impactoFinanceiro = impactoFinanceiro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrazoResolucao() {
        return prazoResolucao;
    }

    public void setPrazoResolucao(String prazoResolucao) {
        this.prazoResolucao = prazoResolucao;
    }
}
