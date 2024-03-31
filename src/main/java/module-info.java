module com.example.activity4prac {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;
}