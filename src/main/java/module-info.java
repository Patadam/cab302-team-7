module com.wellnessapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.wellnessapp.controller to javafx.fxml;
    opens com.wellnessapp to javafx.fxml;

    exports com.wellnessapp;
    exports com.wellnessapp.controller;
    exports com.wellnessapp.model;
    exports com.wellnessapp.model.notice;
    exports com.wellnessapp.model.hydration;
    exports com.wellnessapp.model.mood;
}