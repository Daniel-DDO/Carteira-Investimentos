package br.com.investimentos.financas;

public enum EnumStatusMetas {
    EM_ANDAMENTO, ATINGIDA, ABAIXO_DO_ESPERADO, EXPIRADA;

    @Override
    public String toString() {
        switch (this) {
            case EM_ANDAMENTO:
                return "Em andamento";
            case ATINGIDA:
                return "Atingida";
            case EXPIRADA:
                return "Expirada";
            case ABAIXO_DO_ESPERADO:
                return "Abaixo do esperado";
            default:
                return super.toString();
        }
    }
}
