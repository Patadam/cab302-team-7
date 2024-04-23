package com.wellnessapp.controller;

import com.wellnessapp.enums.MoodType;

import com.wellnessapp.model.MoodDAO;
import com.wellnessapp.model.MoodEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class MoodPopupController {
    @FXML
    private Button createEntryBtn;
    @FXML
    private Button cancelEntryBtn;
    @FXML
    private TextField timeHours;
    @FXML
    private TextField timeMinutes;

    final private LocalDateTime now = LocalDateTime.now();

    /**
     * Starter method that runs on page mount
     */
    public void initialize(){
        System.out.println("started");
        initialiseDatabase();

        final String hours = now.format(DateTimeFormatter.ofPattern("hh"));
        final String minutes = now.format(DateTimeFormatter.ofPattern("mm"));
        timeHours.setText(hours);
        timeMinutes.setText(minutes);
    }

    private void initialiseDatabase() {
        MoodDAO moodDAO = new MoodDAO();
    }

    @FXML
    protected void onCreateEntry() {
        // run code
        System.out.println("Btn Clicked");
        MoodEntry entry = new MoodEntry(MoodType.HAPPY);
    }
    @FXML
    protected void onCancelEntry() {
        System.out.println("Mood Popup - cancelled entry");
    }

}
