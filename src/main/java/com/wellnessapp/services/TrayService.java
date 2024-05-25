package com.wellnessapp.services;

import com.wellnessapp.Main;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
/**
 * The TrayService class provides methods to handle system tray
 * operations such as minimizing to tray and returning from tray.
 */
public class TrayService {

    private static SystemTray systemTray;
    private static TrayIcon trayIcon;

    public TrayService() {}

    /**
     * Method to exit the application and force close without minimising to system tray.
     * Performs tray cleanup
     */
    public static void handleExit() {
        if (trayIcon != null) {
            systemTray.remove(trayIcon);
        }
        Platform.exit();
        System.exit(0);
    }

    /**
     * Sets up the application to minimise to system tray by default when closed.
     * @param stage The stage to set minimise when closed rule.
     * Used in {@link com.wellnessapp.controller.BaseController}
     */
    public static void handleCloseWithSystemTray(Stage stage) {
        if (SystemTray.isSupported()) {
            if (trayIcon == null) {
                Platform.setImplicitExit(false);
                systemTray = SystemTray.getSystemTray();

                Image icon;
                try {
                    icon = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/wt_logo.png").toURI().toURL()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                PopupMenu popup = new PopupMenu();

                MenuItem exitItem = new MenuItem("Exit");
                popup.add(exitItem);
                exitItem.addActionListener(e -> handleExit());

                trayIcon = new TrayIcon(icon, "WellTrack App", popup);

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
