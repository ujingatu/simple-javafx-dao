module com.logindemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;

    opens com.logindemo;
    exports com.logindemo;
}