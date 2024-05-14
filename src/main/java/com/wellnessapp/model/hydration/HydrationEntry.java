package com.wellnessapp.model.hydration;

import java.time.LocalDateTime;

public class HydrationEntry {
    private final LocalDateTime dateTime;
    private int id;
    public HydrationEntry() {
        dateTime = LocalDateTime.now();
    }
    public HydrationEntry(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
