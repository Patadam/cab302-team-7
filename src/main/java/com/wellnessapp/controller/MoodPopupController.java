package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.MoodDAO;
import com.wellnessapp.model.MoodEntry;
import com.wellnessapp.model.MoodManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MoodPopupController extends BaseController {
    @FXML private Button createEntryBtn;
    @FXML private Button cancelEntryBtn;
    @FXML private TextField timeHours;
    @FXML private TextField timeMinutes;
    @FXML private ImageView moodIcon;
    @FXML private ComboBox moodValue;
    private MoodManager moodManager;
    final private LocalDateTime now = LocalDateTime.now();

    /**
     * Starter method that runs on page mount
     */
    public void initialize(){
        moodManager = new MoodManager(new MoodDAO());

        final String hours = now.format(DateTimeFormatter.ofPattern("hh"));
        final String minutes = now.format(DateTimeFormatter.ofPattern("mm"));
        timeHours.setText(hours);
        timeMinutes.setText(minutes);

        loadImages();
    }

    private void loadImages() {
        Image image = new Image(Main.class.getResourceAsStream("/com/wellnessapp/mood-icon.png"));
        moodIcon.setImage(image);
    }

    @FXML
    protected void onCreateEntry() throws SQLException {
        final boolean isInputValid = validateInputs();
        if (isInputValid) {
            System.out.println();
            final MoodType mood = MoodType.valueOf(moodValue.getValue().toString().toUpperCase());
            MoodEntry entry = new MoodEntry(mood);
            moodManager.addMoodEntry(entry);
            getStage().hide();
        }
    }

    private boolean validateInputs() {
        if (moodValue.getValue() == null) {
            return false;
        }
        return true;
    }

    @FXML
    protected void onCancelEntry() {
        getStage().hide();
    }

    @FXML
    protected void onValidateHours(){
        //String text = timeHours.getText();
        //if (text.matches("^\\\\d{1,2}D\\\\d{1,2}H\\\\d{1,2}M$")) {
        //    System.out.println("Valid hours");
        //} else {
        //   final String hours = now.format(DateTimeFormatter.ofPattern("hh"));
        //    timeHours.setText(hours);
        //}
    }
}
