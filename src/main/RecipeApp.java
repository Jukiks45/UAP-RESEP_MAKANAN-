package main;

import main.gui.MainMenuPanel;

import javax.swing.*;

public class RecipeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aplikasi Resep Makanan");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setContentPane(new MainMenuPanel(frame));
            frame.setVisible(true);
        });
    }
}
