module com.wellnessapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.wellnessapp to javafx.fxml;
    exports com.wellnessapp;
    exports com.wellnessapp.controller;
    exports com.wellnessapp.model;
    opens com.wellnessapp.controller to javafx.fxml;
}