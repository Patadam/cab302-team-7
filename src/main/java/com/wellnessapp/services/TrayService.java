package com.wellnessapp.services;

import com.wellnessapp.Main;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

public class TrayService {

    private static SystemTray systemTray;
    private static TrayIcon trayIcon;

    public TrayService() {}

    public static void handleExit() {
        if (trayIcon != null) {
            systemTray.remove(trayIcon);
        }
        Platform.exit();
        System.exit(0);
    }
    public static void handleCloseWithSystemTray(Stage stage) {
        if (SystemTray.isSupported()) {
            if (trayIcon == null) {
                Platform.setImplicitExit(false);
                systemTray = SystemTray.getSystemTray();

                Image icon = null;
                try {
                    icon = ImageIO.read(Objects.requireNonNull(Main.class.getResource("images/wt_logo.png").toURI().toURL())); //Main.class.getResource("images/wt_logo.jpg")).toURI().toURL());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //Toolkit.getDefaultToolkit().getImage(Main.class.getResource("images/wt_logo.png").toExternalForm());

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
