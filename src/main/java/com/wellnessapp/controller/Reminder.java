package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.model.ReminderDAO;
import com.wellnessapp.model.ReminderEntry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Responsible for managing the logic for the reminder view.
 */
public class Reminder {

    @FXML private DatePicker datePicker;
    @FXML private TextField Title;
    @FXML private Spinner hourSpinner;
    @FXML private Spinner minuteSpinner;
    @FXML private TextArea Notes;
    @FXML private TextArea Source;
    @FXML private ListView<ReminderEntry> ReminderListView;
    @FXML private Button cancelButton;


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

        ReminderEntry selectedReminder = ReminderListView.getSelectionModel().getSelectedItem();
        if (selectedReminder != null){
            selectedReminder.setTitle(Title.getText());
            selectedReminder.setDate(Date.valueOf(datePicker.getValue()));
            selectedReminder.setTime(hourSpinner.getValue().toString() + ":" + minuteSpinner.getValue().toString());
            selectedReminder.setComments(Notes.getText());
            selectedReminder.setUrl(Source.getText());
            reminderDAO.updateReminder(selectedReminder);
            syncReminders();
        }
        selectReminder(selectedReminder);
//        String title = Title.getText();
//        Date date = Date.valueOf(datePicker.getValue());
//        String hours = hourSpinner.getValue().toString();
//        String minutes = minuteSpinner.getValue().toString();
//        String time = hours + ":" + minutes;
//        String comments = Notes.getText();
//        String url = Source.getText();
//        ReminderEntry entry = new ReminderEntry(title, date, time, comments, url);
//        reminderDAO.Create(entry);
//
//        syncReminders();
//        ReminderListView.getSelectionModel().select(entry);
    }
    @FXML
    private void onDelete() {
        // Get the selected contact from the list view
        ReminderEntry selectedReminder = ReminderListView.getSelectionModel().getSelectedItem();
        if (selectedReminder != null){
            reminderDAO.deleteReminder(selectedReminder);
            syncReminders();
        }
    }

    @FXML
    private void onAdd() {
        // Default values for a new contact
        final String DEFAULT_TITLE = "New";
        final Date DEFAULT_DATE = Date.valueOf(LocalDate.now());
        final String DEFAULT_TIME = "0:00";
        final String DEFAULT_NOTES = "";
        final String DEFAULT_URL = "";
        ReminderEntry newReminder = new ReminderEntry(DEFAULT_TITLE, DEFAULT_DATE, DEFAULT_TIME, DEFAULT_NOTES, DEFAULT_URL);
        try {
            reminderDAO.Create(newReminder);
            syncReminders();
            selectReminder(newReminder);
            Title.requestFocus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }
    private void selectReminder(ReminderEntry reminder) {
        ReminderListView.getSelectionModel().select(reminder);
        Title.setText(reminder.getTitle());
        datePicker.setValue(reminder.getDate().toLocalDate());
        String[] times = reminder.getTime().split(":");
        hourSpinner.getValueFactory().setValue(Integer.parseInt(times[0]));
        minuteSpinner.getValueFactory().setValue(Integer.parseInt(times[1]));
        Notes.setText(reminder.getComments());
        Source.setText(reminder.getUrl());
    }

    private ListCell<ReminderEntry> renderCell(ListView<ReminderEntry> listView) {
        return new ListCell<>(){
            private void onReminderSelected(MouseEvent mouseEvent) {
                ListCell<ReminderEntry> clickedCell = (ListCell<ReminderEntry>) mouseEvent.getSource();
                // Get the selected contact from the list view
                ReminderEntry selectedReminder = clickedCell.getItem();
                if (selectedReminder != null) selectReminder(selectedReminder);
            }
            /**
             * Updates the item in the cell by setting the text to the contact's full name.
             * @param reminder The contact to update the cell with.
             * @param empty Whether the cell is empty.
             */
            @Override
            protected void updateItem(ReminderEntry reminder, boolean empty) {
                super.updateItem(reminder, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || reminder == null || reminder.getTitle() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onReminderSelected);
                } else {
                    setText(reminder.getTitle());
                }
            }
        };
    }

    private void syncReminders() {
        ReminderListView.getItems().clear();
        List<ReminderEntry> reminders = reminderDAO.getAllEntries();
        final boolean hasReminders = !reminders.isEmpty();
        ReminderEntry currentReminder = ReminderListView.getSelectionModel().getSelectedItem();
        if (hasReminders) {
            ReminderListView.getItems().addAll(reminders);

            ReminderEntry nextReminder = reminders.contains(currentReminder) ? currentReminder : reminders.get(0);
            ReminderListView.getSelectionModel().select(nextReminder);
            selectReminder(nextReminder);
        }
    }
    public void on9AM() {
        hourSpinner.getValueFactory().setValue(9);
        minuteSpinner.getValueFactory().setValue(0);

    }

    public void on11AM() {
        hourSpinner.getValueFactory().setValue(11);
        minuteSpinner.getValueFactory().setValue(0);

    }

    public void on1PM() {
        hourSpinner.getValueFactory().setValue(13);
        minuteSpinner.getValueFactory().setValue(0);

    }
    public void on3PM() {
        hourSpinner.getValueFactory().setValue(15);
        minuteSpinner.getValueFactory().setValue(0);

    }
    public void on5PM() {
        hourSpinner.getValueFactory().setValue(17);
        minuteSpinner.getValueFactory().setValue(0);

    }
}
