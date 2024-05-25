package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.annotations.ApplyStylesheet;
import com.wellnessapp.annotations.ApplyTitle;
import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.mood.MoodDAO;
import com.wellnessapp.model.mood.MoodEntry;
import com.wellnessapp.model.mood.MoodManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import utils.DateTimeUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Responsible for managing controller logic for the mood chart view.
 */
@ApplyTitle("Mood Chart")
@ApplyStylesheet("mood.css")
public class MoodChartController extends BaseController {
    @FXML private ListView<MoodEntry> moodListView;
    @FXML private AreaChart chart;
    @FXML private DatePicker moodDate;
    @FXML private Spinner<Integer> moodHour;
    @FXML private Spinner<Integer> moodMinute;
    @FXML private TextArea moodComment;
    @FXML private ComboBox<String> moodCombo;

    private final MoodManager moodManager;

    public MoodChartController() {
        MoodDAO dao = new MoodDAO();
        moodManager = new MoodManager(dao);
    }

    @FXML
    public void initialize() {
        updateChart();
        moodListView.setCellFactory(this::renderCell);
        syncEntries();
    }

    private void configureYAxis() {
        final NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
            @Override
            public String toString(Number object) {
                int value = object.intValue();
                return switch (value) {
                    case 1 -> "Happy";
                    case 0 -> "Neutral";
                    case -1 -> "Sad";
                    default -> super.toString(object);
                };
            }
        });
        yAxis.setTickUnit(1);
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-1);
        yAxis.setUpperBound(1);
        yAxis.setTickLength(0);
        yAxis.setTickMarkVisible(false);
        yAxis.setMinorTickVisible(false);
        yAxis.setTickMarkVisible(false);
        yAxis.setMinorTickVisible(false);
    }

    private void configureChart() {
        chart.setAnimated(true);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);
        chart.setLegendVisible(false);
    }

    private Series<String, Integer> getPreparedChartData() {
        final List<MoodEntry> entries = moodManager.getAllEntries();
        final Series<String, Integer> moodSeries = new Series<>();
        entries.forEach(entry -> moodSeries.getData().add(
                new Data<>(String.format("%s %s/%s %s",
                        entry.getTimestamp().getDayOfWeek().toString(),
                        entry.getTimestamp().getDayOfMonth(),
                        entry.getTimestamp().getMonthValue(),
                        entry.getTimestamp().toLocalTime()),
                        entry.getMood().ordinal() - 1
        )));
        return moodSeries;
    }

    private void updateChart() {
        configureYAxis();
        configureChart();
        Series<String, Integer> data = getPreparedChartData();
        chart.getData().clear();
        chart.getData().addAll(data);
    }

    @FXML
    protected void onHomeButton() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        getStage().setScene(scene);
        getStage().setTitle("Home");
        getStage().show();
        getStage().centerOnScreen();
    }

    private void setSelected(MoodEntry entry) {
        moodListView.getSelectionModel().select(entry);
        moodDate.setValue(entry.getTimestamp().toLocalDate());
        moodHour.getValueFactory().setValue(entry.getTimestamp().getHour());
        moodMinute.getValueFactory().setValue(entry.getTimestamp().getMinute());
        moodComment.setText(entry.getComment());
        moodCombo.setValue(entry.getMood().toString());
    }

    private ListCell<MoodEntry> renderCell(ListView<MoodEntry> listView) {
        return new ListCell<>() {
            private void onMoodEntryClicked(MouseEvent event) {
                final ListCell<MoodEntry> cell = (ListCell<MoodEntry>) event.getSource();
                final MoodEntry entry = cell.getItem();
                if (entry != null) setSelected(entry);
            }
            @Override
            protected void updateItem(MoodEntry entry, boolean empty) {
                super.updateItem(entry, empty);
                if (empty || entry == null || entry.getTimestamp() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onMoodEntryClicked);
                } else {
                    setText(String.format("%s: %s",entry.getMood().toString(), entry.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
                }
            }
        };
    }

    private void syncEntries() {
        moodListView.getItems().clear();
        final List<MoodEntry> entries = moodManager.getAllEntries(true);
        final MoodEntry selectedItem = moodListView.getSelectionModel().getSelectedItem();
        if (!entries.isEmpty()) {
            moodListView.getItems().addAll(entries);
            final MoodEntry init = entries.contains(selectedItem) ? selectedItem : entries.get(0);
            moodListView.getSelectionModel().select(init);
            setSelected(init);
        } else {
            moodCombo.getSelectionModel().select(1);
            final LocalDateTime now = LocalDateTime.now();
            moodDate.setValue(now.toLocalDate());
            moodHour.getValueFactory().setValue(now.getHour());
            moodMinute.getValueFactory().setValue(now.getMinute());
        }
        updateChart();
    }

    @FXML
    protected void onUpdate() {
        MoodEntry entry = moodListView.getSelectionModel().getSelectedItem();
        if (entry != null) {
            entry.setMood(MoodType.valueOf(moodCombo.getValue().toUpperCase()));
            final String hour = Integer.toString(moodHour.getValueFactory().getValue());
            final String minute = Integer.toString(moodMinute.getValueFactory().getValue());
            final LocalDate date = moodDate.getValue();
            entry.setTimestamp(DateTimeUtil.forgeLocalDateTime(hour, minute, date));
            entry.setComment(moodComment.getText());
            moodManager.update(entry);
            syncEntries();
            setSelected(entry);
        }
    }

    @FXML
    protected void onDelete() {
        MoodEntry entry = moodListView.getSelectionModel().getSelectedItem();
        if (entry != null) {
            moodManager.delete(entry);
            syncEntries();
        }
    }

    @FXML
    protected void onNewEntry() throws SQLException {
        final MoodType mood = MoodType.valueOf(moodCombo.getValue().toUpperCase());
        final String hour = Integer.toString(moodHour.getValueFactory().getValue());
        final String minute = Integer.toString(moodMinute.getValueFactory().getValue());
        final LocalDate date = moodDate.getValue();
        final LocalDateTime dateTime = DateTimeUtil.forgeLocalDateTime(hour, minute, date);
        final String comment = moodComment.getText();
        final MoodEntry newEntry = new MoodEntry(mood, dateTime, comment);
        moodManager.addMoodEntry(newEntry);
        setSelected(newEntry);
        syncEntries();
    }
}
