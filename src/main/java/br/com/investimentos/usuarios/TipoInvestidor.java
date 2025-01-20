package br.com.investimentos.usuarios;

public enum TipoInvestidor {
    CONSERVADOR, MODERADO, AGRESSIVO;

    @Override
    public String toString() {
        switch (this) {
            case CONSERVADOR: return "Conservador";
            case MODERADO: return "Moderado";
            case AGRESSIVO: return "Agressivo";
            default: throw new IllegalArgumentException();
        }
    }
}
