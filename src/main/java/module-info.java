module com.example.activity4prac {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.wellnessapp to javafx.fxml;
    exports com.wellnessapp;
    exports com.wellnessapp.controller;
    opens com.wellnessapp.controller to javafx.fxml;
}