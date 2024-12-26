package main.gui;

import util.FileHandler;
import main.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private JFrame parentFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private List<Recipe> recipes;

    public MainMenuPanel(JFrame frame) {
        this.parentFrame = frame;
        this.recipes = FileHandler.loadRecipes();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel utama (menu)
        JPanel mainMenuPanel = new JPanel(new GridBagLayout());
        mainMenuPanel.setBackground(Color.WHITE); // Background putih

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Spasi antar tombol

        JButton addRecipeButton = createStyledButton("Tambah Resep");
        JButton viewRecipesButton = createStyledButton("Lihat Resep");

        addRecipeButton.addActionListener(event -> cardLayout.show(cardPanel, "addRecipe"));
        viewRecipesButton.addActionListener(event -> {
            ViewRecipesPanel viewRecipesPanel = new ViewRecipesPanel(parentFrame, recipes);
            parentFrame.getContentPane().removeAll(); // Menghapus komponen yang ada
            parentFrame.getContentPane().add(viewRecipesPanel); // Menambahkan panel baru
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainMenuPanel.add(addRecipeButton, gbc);

        gbc.gridy = 1;
        mainMenuPanel.add(viewRecipesButton, gbc);

        // Tambahkan panel ke CardLayout
        cardPanel.add(mainMenuPanel, "mainMenu");
        cardPanel.add(new AddRecipePanel(parentFrame, recipes, () -> cardLayout.show(cardPanel, "mainMenu")), "addRecipe");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        // Tampilkan panel menu utama
        cardLayout.show(cardPanel, "mainMenu");
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(0, 153, 76)); // Warna hijau
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 51), 3),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 51));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 76));
            }
        });
        return button;
    }
}
