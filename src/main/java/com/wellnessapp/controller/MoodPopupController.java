package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.mood.MoodDAO;
import com.wellnessapp.model.mood.MoodEntry;
import com.wellnessapp.model.mood.MoodManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MoodPopupController extends BaseController {
    @FXML private Button createEntryBtn;
    @FXML private Button cancelEntryBtn;
    @FXML private TextField timeHours;
    @FXML private TextField timeMinutes;
    @FXML private ImageView moodIcon;
    @FXML private ComboBox moodValue;
    @FXML private DatePicker datePicker;
    @FXML private TextArea commentField;
    @FXML private Label errorText;
    private MoodManager moodManager;
    final private LocalDateTime now = LocalDateTime.now();

    /**
     * Starter method that runs on page mount
     */
    @FXML
    public void initialize(){
        moodManager = new MoodManager(new MoodDAO());

        final String hours = now.format(DateTimeFormatter.ofPattern("HH"));
        final String minutes = now.format(DateTimeFormatter.ofPattern("mm"));
        timeHours.setText(hours);
        timeMinutes.setText(minutes);
        datePicker.setValue(LocalDate.now());

        loadImages();
    }

    private void loadImages() {
        Image image = new Image(Main.class.getResourceAsStream("/Images/mood-icon.png"));
        moodIcon.setImage(image);
    }

    @FXML
    protected void onCreateEntry() throws SQLException {
        final boolean isInputValid = validateInputs();
        if (isInputValid) {
            final MoodType mood = MoodType.valueOf(moodValue.getValue().toString().toUpperCase());
            String hours = timeHours.getText();
            String minutes = timeMinutes.getText();
            if (hours.length() == 1) { hours = "0"+hours; }
            if (minutes.length() == 1) { minutes = "0"+minutes; }
            final LocalDate date = datePicker.getValue();
            final LocalDateTime dateTime = LocalDateTime.parse(date + " " + hours + ":" + minutes, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            final String comment = commentField.getText();

            MoodEntry entry = new MoodEntry(mood, dateTime, comment);
            moodManager.addMoodEntry(entry);
            getStage().hide();
        }
    }

    private boolean validateInputs() {
        final int hours = Integer.parseInt(timeHours.getText());
        final int minutes = Integer.parseInt(timeHours.getText());

        if (moodValue.getValue() == null) {
            errorText.setText("You must select a mood");
            return false;
        }
        if (hours > 23 || hours < 0) {
            errorText.setText("The set hour cannot exceed 23 or be under 0");
            return false;
        }
        if (minutes > 59 || minutes < 0) {
            errorText.setText("The set minute cannot exceed 59 or be under 0");
            return false;
        }
        errorText.setText("");
        return true;
    }

    @FXML
    protected void onCancelEntry() {
        getStage().hide();
    }
}
