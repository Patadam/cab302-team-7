package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.enums.MoodType;

import com.wellnessapp.model.MoodDAO;
import com.wellnessapp.model.MoodEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MoodPopupController {
    @FXML private Button createEntryBtn;
    @FXML private Button cancelEntryBtn;
    @FXML private TextField timeHours;
    @FXML private TextField timeMinutes;
    @FXML private ImageView moodIcon;

    private Stage stage;

    //private Stage stage;

    final private LocalDateTime now = LocalDateTime.now();

    /**
     * Starter method that runs on page mount
     */
    public void initialize(){
        //System.out.println("started");
        System.out.println("[mood] created new popup");
        initialiseDatabase();

        final String hours = now.format(DateTimeFormatter.ofPattern("hh"));
        final String minutes = now.format(DateTimeFormatter.ofPattern("mm"));
        timeHours.setText(hours);
        timeMinutes.setText(minutes);

        //stage = (Stage) cancelEntryBtn.getScene().getWindow();
        loadImages();
    }

    private void loadImages() {
        Image image = new Image(Main.class.getResourceAsStream("/com/wellnessapp/mood-icon.png"));
        moodIcon.setImage(image);
    }

    private void initialiseDatabase() {
        MoodDAO moodDAO = new MoodDAO();
    }

    @FXML
    protected void onCreateEntry() {
        // run code
        //System.out.println("Btn Clicked");
        MoodEntry entry = new MoodEntry(MoodType.HAPPY);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        cancelEntryBtn.setOnAction(event -> {
            stage.hide();
        });
    }

}
