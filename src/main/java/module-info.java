module com.wellnessapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;

    opens com.wellnessapp.controller to javafx.fxml;
    opens com.wellnessapp to javafx.fxml;

    exports com.wellnessapp;
    exports com.wellnessapp.controller;
    exports com.wellnessapp.model;
}
