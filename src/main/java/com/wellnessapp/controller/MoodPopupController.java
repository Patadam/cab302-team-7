package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.annotations.ApplyStylesheet;
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

@ApplyStylesheet("mood.css")
public class MoodPopupController extends BaseController {
    @FXML private Button createEntryBtn;
    @FXML private Button cancelEntryBtn;
    @FXML private Spinner<Integer> timeHours;
    @FXML private Spinner<Integer> timeMinutes;
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

        final int hours = now.getHour(); //now.format(DateTimeFormatter.ofPattern("HH"));
        final int minutes = now.getMinute(); //now.format(DateTimeFormatter.ofPattern("mm"));
        timeHours.getValueFactory().setValue(hours);
        timeMinutes.getValueFactory().setValue(minutes);
        //timeHours.setText(hours);
        //timeMinutes.setText(minutes);
        datePicker.setValue(LocalDate.now());


    }

    @FXML
    protected void onCreateEntry() throws SQLException {
        final boolean isInputValid = validateInputs();
        if (isInputValid) {
            final MoodType mood = MoodType.valueOf(moodValue.getValue().toString().toUpperCase());
            String hours = timeHours.getValue().toString();
            String minutes = timeMinutes.getValue().toString();
            if (minutes.length() == 1) {
                minutes = "0" + minutes;
            }
            if (hours.length() == 1) {
                hours = "0" + hours;
            }
            final LocalDate date = datePicker.getValue();
            final LocalDateTime dateTime = LocalDateTime.parse(date + " " + hours + ":" + minutes, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            final String comment = commentField.getText();

            MoodEntry entry = new MoodEntry(mood, dateTime, comment);
            moodManager.addMoodEntry(entry);
            getStage().hide();
        }
    }

    private boolean validateInputs() {
        if (moodValue.getValue() == null) {
            errorText.setText("You must select a mood");
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
