package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

//    @FXML
//    private Button TakeText;

    @FXML
    private Label TakeText;

    @FXML
    private CheckBox agreeCheckBox;

    @FXML
    private Button TipButton;

    @FXML
    private ImageView imageView;


    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) TipButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        TipButton.setDisable(!accepted);
    }

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/Images/Computer.png"));
        imageView.setImage(image);
    }


    @FXML
    protected void onTakeButtonClick() { TakeText.setText("Take Test!");}

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("CAB302Team7@qut.edu.au");
    }
    @FXML
    protected void onWellnessTipsButtonClick() throws IOException {
        Stage stage = (Stage) TipButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("wellness-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }


}
