package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.annotations.ApplyStylesheet;
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

@ApplyStylesheet("mood.css")
public class MoodChartController extends BaseController {
    @FXML private AreaChart chart;
    private MoodManager moodManager;
    @FXML
    public void initialize(){
        this.moodManager = new MoodManager(new MoodDAO());

        final NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        //yAxis.rang
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
            @Override
            public String toString(Number object) {
                int value = object.intValue();
                if (value == 1) {
                    return "Happy";
                } else if (value == 0) {
                    return "Neutral";
                } else if (value == -1) {
                    return "Sad";
                }
                return super.toString(object);
            }
        });

        //yAxis.set(FXCollections.observableList(Arrays.stream(MoodType.values()).map(Enum::toString).toList()));

        populateChart();
    }

    private Series getPreparedChartData() {
        final List<MoodEntry> entries = moodManager.getAllEntries();
        Series moodSeries = new Series<>();
        moodSeries.setName("Moods");
        entries.forEach(entry -> {
            moodSeries.getData().add(new Data<>(
                            entry.getTimestamp().toString(),
                            entry.getMood().ordinal() - 1
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
