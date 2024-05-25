package com.wellnessapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The starting point of the application.
 */
public class Main extends Application {
    /**
     * The Title of the application window.
     */
    public static final String TITLE = "Home";
    /**
     * The Width of the application window.
     */
    public static final int WIDTH = 900;
    /**
     * The Height of the application window.
     */
    public static final int HEIGHT = 700;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}