package br.com.investimentos.financas;

public enum EnumStatusMetas {
    EM_ANDAMENTO, ATINGIDA, EXPIRADA;

    @Override
    public String toString() {
        switch (this) {
            case EM_ANDAMENTO:
                return "Em andamento";
            case ATINGIDA:
                return "Atingida";
            case EXPIRADA:
                return "Expirada";
            default:
                return super.toString();
        }
    }
}
