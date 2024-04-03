module com.example.activity4prac {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;
}