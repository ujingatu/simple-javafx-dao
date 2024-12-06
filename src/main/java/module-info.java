module com.logindemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.logindemo;
    exports com.logindemo;
}