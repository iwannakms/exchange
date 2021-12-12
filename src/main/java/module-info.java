module com.example.jama {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.jama to javafx.fxml;
    exports com.example.jama;
}