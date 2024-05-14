package com.wellnessapp.model.notice;

import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.util.Arrays;
import java.util.TimerTask;

public class NoticeTask extends TimerTask {
    private final NoticeBO notice;

    public NoticeTask(NoticeBO notice) {
        this.notice = notice;
    }

    @Override
    public void run() {
        Platform.runLater(()->{
            try {
                Notifications.create()
                        .title(notice.getTitle())
                        .text(notice.getText())
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
