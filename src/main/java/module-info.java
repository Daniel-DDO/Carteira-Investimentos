module br.com.investimentos.controladores {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql.rowset;


    opens br.com.investimentos.controladores to javafx.fxml;
    opens br.com.investimentos.controladores.gui.comum to javafx.fxml;
    opens br.com.investimentos.controladores.gui.adm to javafx.fxml;
    opens br.com.investimentos.usuarios to javafx.fxml;
    opens br.com.investimentos.financas to javafx.fxml;
    opens br.com.investimentos.repositorios to javafx.fxml;
    exports br.com.investimentos.controladores;
    exports br.com.investimentos.controladores.gui;
    exports br.com.investimentos.controladores.gui.comum;
    exports br.com.investimentos.controladores.gui.adm;
    exports br.com.investimentos.main;
    exports br.com.investimentos.usuarios;
    exports br.com.investimentos.repositorios;
    exports br.com.investimentos.financas;
    opens br.com.investimentos.main to javafx.fxml;
    opens br.com.investimentos.controladores.gui to javafx.fxml;
}