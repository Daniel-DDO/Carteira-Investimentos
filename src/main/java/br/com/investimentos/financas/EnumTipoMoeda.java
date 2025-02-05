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
                return "BRL";
            case USD:
                return "USD";
            case EUR:
                return "EUR";
            case GBP:
                return "GBP";
            case JPY:
                return "JPY";
            case AUD:
                return "AUD";
            case CAD:
                return "CAD";
            case CHF:
                return "CHF";
            case CNY:
                return "CNY";
            case MXN:
                return "MXN";
            default:
                return super.toString();
        }
    }
}
