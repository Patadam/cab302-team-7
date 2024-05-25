package com.wellnessapp;

import com.wellnessapp.annotations.ApplyStylesheet;
import com.wellnessapp.annotations.ApplyTitle;
import com.wellnessapp.controller.MoodChartController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotationsTest {
    @Test
    public void testStylesheetAnnotation() {
        assertTrue(MoodChartController.class.isAnnotationPresent(ApplyStylesheet.class));
        ApplyStylesheet annotation = MoodChartController.class.getAnnotation(ApplyStylesheet.class);
        assertEquals(String.class, annotation.value().getClass());
    }
    @Test
    public void testTitleAnnotation() {
        assertTrue(MoodChartController.class.isAnnotationPresent(ApplyTitle.class));
        ApplyTitle annotation = MoodChartController.class.getAnnotation(ApplyTitle.class);
        assertEquals(String.class, annotation.value().getClass());
    }
}
