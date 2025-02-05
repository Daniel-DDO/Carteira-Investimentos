package br.com.investimentos.financas;

public enum EnumTipoMoeda {
    BRL, //Real Brasileiro
    USD, //Dólar Americano
    EUR, //Euro
    GBP, //Libra Esterlina
    JPY, //Iene Japonês
    AUD, //Dólar Australiano
    CAD, //Dólar Canadense
    CHF, //Franco Suíço
    CNY, //Yuan Chinês
    MXN; //Peso Mexicano

    @Override
    public String toString() {
        switch (this) {
            case BRL:
                return "BRL - Real Brasileiro";
            case USD:
                return "USD - Dólar Americano";
            case EUR:
                return "EUR - Euro";
            case GBP:
                return "GBP - Libra Esterlina";
            case JPY:
                return "JPY - Iene Japonês";
            case AUD:
                return "AUD - Dólar Australiano";
            case CAD:
                return "CAD - Dólar Canadense";
            case CHF:
                return "CHF - Franco Suíço";
            case CNY:
                return "CNY - Yuan Chinês";
            case MXN:
                return "MXN - Peso Mexicano";
            default:
                return super.toString();
        }
    }
}
