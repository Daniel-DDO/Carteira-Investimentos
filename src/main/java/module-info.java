module br.com.investimentos.controladores {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.investimentos.controladores to javafx.fxml;
    opens br.com.investimentos.controladores.gui.comum to javafx.fxml;
    opens br.com.investimentos.controladores.gui.adm to javafx.fxml;
    exports br.com.investimentos.controladores;
    exports br.com.investimentos.controladores.gui;
    exports br.com.investimentos.controladores.gui.comum;
    exports br.com.investimentos.controladores.gui.adm;
    exports br.com.investimentos.main;
    opens br.com.investimentos.main to javafx.fxml;
    opens br.com.investimentos.controladores.gui to javafx.fxml;
}