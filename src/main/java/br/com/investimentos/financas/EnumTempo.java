package br.com.investimentos.financas;

public enum EnumTempo {
    Semanas, Meses, Anos;

    @Override
    public String toString() {
        switch (this) {
            case Semanas:
                return "Semanas";
            case Meses:
                return "Meses";
            case Anos:
                return "Anos";
            default:
                return super.toString();
        }
    }
}
