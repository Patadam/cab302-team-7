package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.MoodEntry;
import com.wellnessapp.model.ReminderDAO;
import com.wellnessapp.model.ReminderEntry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Reminder {

    @FXML private DatePicker datePicker;
    @FXML private TextField Title;
    @FXML private Spinner hourSpinner;
    @FXML private Spinner minuteSpinner;
    @FXML private TextField Notes;
    @FXML private TextField Source;
    @FXML private ListView<ReminderEntry> ReminderListView;


    ReminderDAO reminderDAO = new ReminderDAO();

    public void initialize() {
        // Optional: You can set an initial date for the DatePicker
        datePicker.setValue(LocalDate.now());

        ReminderListView.setCellFactory(this::renderCell);
        syncReminders();
    }


    // Method to retrieve the selected date and perform some action
    public void handleDateSelection() {
        LocalDate selectedDate = datePicker.getValue();
        System.out.println("Selected date: " + selectedDate);
        // You can perform further actions with the selected date here
    }

    @FXML
    protected void onConfirmButtonClick() throws SQLException {
        String title = Title.getText();
        Date date = Date.valueOf(datePicker.getValue());
        String hours = hourSpinner.getValue().toString();
        String minutes = minuteSpinner.getValue().toString();
        String time = hours + ":" + minutes;
        String comments = Notes.getText();
        String url = Source.getText();
        ReminderEntry entry = new ReminderEntry(title, date, time, comments, url);
        reminderDAO.Create(entry);

        syncReminders();
        ReminderListView.getSelectionModel().select(entry);
    }

    private ListCell<ReminderEntry> renderCell(ListView<ReminderEntry> listView) {
        return new ListCell<>(){
            @Override
            protected void updateItem(ReminderEntry entry, boolean empty) {
                super.updateItem(entry, empty);
                if (empty || entry == null || entry.getTime() == null) {
                    setText(null);
                } else {
                    setText(entry.getTitle());
                }
            };
        };
    }

    private void syncReminders() {
        ReminderListView.getItems().clear();
        List<ReminderEntry> reminders = reminderDAO.getAllEntries();
        final boolean hasReminders = !reminders.isEmpty();
        if (hasReminders) {
            ReminderListView.getItems().addAll(reminders);
        }
    }
}
