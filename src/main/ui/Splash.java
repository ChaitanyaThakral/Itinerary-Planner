package ui;

import javax.swing.*;
import java.awt.*;

public class Splash {
    private JFrame frame;
    private JLabel label;

    public Splash() {
        frame = new JFrame("Splash Screen");
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        ImageIcon splashImage = new ImageIcon("data\\SplashScreenImage.png");

        label = new JLabel(splashImage);
        frame.add(label, BorderLayout.CENTER);
    }

    public void showSplash() {
        frame.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        frame.setVisible(false);
    }
}
