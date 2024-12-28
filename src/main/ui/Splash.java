package ui;

import javax.swing.*;
import java.awt.*;

public class Splash {
    private JFrame frame;
    private JLabel label;

    public Splash() {
        frame = new JFrame("Splash Screen");
        frame.setSize(775, 500);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        ImageIcon splashImage = new ImageIcon("data\\Splash.jpeg");

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
