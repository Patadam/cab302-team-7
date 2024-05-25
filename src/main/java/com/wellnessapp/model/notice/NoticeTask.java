package com.wellnessapp.model.notice;

import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.util.Arrays;
import java.util.TimerTask;

/**
 * NoticeTask is a TimerTask implementation that displays notifications based on a NoticeBO.
 * @see TimerTask
 * @see NoticeBO
 */
public class NoticeTask extends TimerTask {
    private final NoticeBO notice;
    /**
     * Constructs a NoticeTask with the provided NoticeBO.
     * @param notice The NoticeBO containing title and text for the notification.
     */
    public NoticeTask(NoticeBO notice) {
        this.notice = notice;
    }
    /**
     * Runs the task, displaying a notification with the notice's title and text.
     * Uses controlFX/JavaFX notifications if available, falls back to system tray notifications if not.
     */
    @Override
    public void run() {
        Platform.runLater(()->{
            try {
                Notifications.create()
                        .title(notice.getTitle())
                        .text(notice.getText())
                        .onAction(actionEvent -> {
                            System.out.println(actionEvent);
                        })
                        .show();
            } catch (Exception ex) {
                TrayIcon icon = Arrays.stream(SystemTray.getSystemTray().getTrayIcons())
                        .findFirst().orElse(null);
                if (icon != null) {
                    icon.displayMessage(notice.getTitle(), notice.getText(), TrayIcon.MessageType.NONE);
                } else {
                    throw new RuntimeException("Could not show notification, no owner window was set and no tray icon was detected.");
                }
            }
        });
    }
}
