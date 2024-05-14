package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.enums.MoodType;
import com.wellnessapp.model.mood.MoodDAO;
import com.wellnessapp.model.mood.MoodEntry;
import com.wellnessapp.model.mood.MoodManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MoodChartController extends BaseController {
    @FXML private AreaChart chart;
    private MoodManager moodManager;
    public void initialize(){
        this.moodManager = new MoodManager(new MoodDAO());

        final CategoryAxis yAxis = (CategoryAxis) chart.getYAxis();
        yAxis.setCategories(FXCollections.observableList(Arrays.stream(MoodType.values()).map(Enum::toString).toList()));

        populateChart();
    }

    private Series getPreparedChartData() {
        final List<MoodEntry> entries = moodManager.getAllEntries();
        Series moodSeries = new Series<>();
        moodSeries.setName("Moods");
        entries.forEach(entry -> {
            moodSeries.getData().add(new Data<>(
                            entry.getTimestamp().toString(),
                            entry.getMood().toString()
            ));
        });
        return moodSeries;
    }

    private void populateChart() {
        chart.getData().addAll(getPreparedChartData());
    }

    @FXML
    protected void onHomeBtn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        getStage().setScene(scene);
        getStage().setTitle("Home");
        getStage().show();
        getStage().centerOnScreen();
    }
}
