module br.com.investimentos.controladores {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.investimentos.controladores to javafx.fxml;
    exports br.com.investimentos.controladores;
}