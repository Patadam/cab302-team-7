module com.example.activity4prac {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.activity4prac to javafx.fxml;
    exports com.example.activity4prac;
}