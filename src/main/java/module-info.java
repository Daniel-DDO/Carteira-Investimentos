module br.com.investimentos.controladores {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.investimentos.controladores to javafx.fxml;
    opens br.com.investimentos.controladores.comum to javafx.fxml;
    opens br.com.investimentos.controladores.adm to javafx.fxml;
    exports br.com.investimentos.controladores;
    exports br.com.investimentos.controladores.comum;
    exports br.com.investimentos.controladores.adm;
    exports br.com.investimentos.main;
    opens br.com.investimentos.main to javafx.fxml;
}