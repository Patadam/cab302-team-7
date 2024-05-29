package com.wellnessapp;

import com.wellnessapp.model.notice.NoticeBO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NoticeBOTest {
    private NoticeBO notice;
    @BeforeEach
    public void setUp() {
        notice = NoticeBO.builder()
                .title("Test Title")
                .text("Test Text")
                .build();
    }
    @Test
    public void testGetTitle() {
        String title = notice.getTitle();
        assertEquals("Test Title", title);
    }
    @Test
    public void testGetText() {
        String text = notice.getText();
        assertEquals("Test Text", text);
    }
    @Test
    public void testBuilderWithTitle() {
        NoticeBO n = NoticeBO.builder().title("Title").build();
        assertNotNull(n);
    }
}
