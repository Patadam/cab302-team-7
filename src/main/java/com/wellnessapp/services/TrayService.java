package com.wellnessapp.services;

import com.wellnessapp.Main;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrayService {

    private static SystemTray systemTray;
    private static TrayIcon trayIcon;

    public TrayService() {}

    public static void handleExit() {
        systemTray.remove(trayIcon);
        Platform.exit();
    }
    public static void handleCloseWithSystemTray(Stage stage) {
        if (SystemTray.isSupported()) {
            if (trayIcon == null) {
                Platform.setImplicitExit(false);
                systemTray = SystemTray.getSystemTray();
                Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Images/mood-icon.png").toExternalForm());

                PopupMenu popup = new PopupMenu();

                MenuItem exitItem = new MenuItem("Exit");
                popup.add(exitItem);
                exitItem.addActionListener(e -> handleExit());

                trayIcon = new TrayIcon(icon, "WellnessApp", popup);

                trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Handle mouse click event
                        Platform.runLater(()->{
                            stage.show();
                            stage.requestFocus();
                        });
                    }
                });
                try {
                    systemTray.add(trayIcon);
                } catch (AWTException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
