package com.wellnessapp.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReminderService {
    private ScheduledExecutorService scheduler;
    private boolean remindersEnabled;
    private int reminderInterval; // in minutes

    public ReminderService() {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.remindersEnabled = false;
        this.reminderInterval = 60; // default interval: 1 hour
    }

    public void setRemindersEnabled(boolean enabled) {
        this.remindersEnabled = enabled;
        if (enabled) {
            startReminders();
        } else {
            stopReminders();
        }
    }

    public boolean areRemindersEnabled() {
        return remindersEnabled;
    }

    public void setReminderInterval(int interval) {
        this.reminderInterval = interval;
        if (remindersEnabled) {
            stopReminders();
            startReminders();
        }
    }

    public int getReminderInterval() {
        return reminderInterval;
    }

    private void startReminders() {
        scheduler.scheduleAtFixedRate(this::showReminder, 0, reminderInterval, TimeUnit.MINUTES);
    }

    private void stopReminders() {
        scheduler.shutdown();
    }

    private void showReminder() {
        System.out.println("Take a break from screen time!");
        // You can replace this with your desired reminder notification
    }
}
