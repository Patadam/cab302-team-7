package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label Navigete_to_1;
    @FXML
    private Label Navigete_to_2;
    @FXML
    private Label Navigete_to_3;
    @FXML
    private Label Navigete_to_4;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onHelloButtonClick1() {
        Navigete_to_1.setText("Take me");
    }
    @FXML
    protected void onHelloButtonClick2() {
        Navigete_to_2.setText("Take me");
    }
    @FXML
    protected void onHelloButtonClick3() {
        Navigete_to_3.setText("Take me");
    }
    @FXML
    protected void onHelloButtonClick4() {
        Navigete_to_4.setText("Take me");
    }
}