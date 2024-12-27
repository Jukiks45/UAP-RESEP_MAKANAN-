package org.example.gui;

import org.example.util.FileHandler;
import org.example.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private JFrame parentFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private List<Recipe> recipes;

    // @SuppressWarnings("unused")
    public MainMenuPanel(JFrame frame) {
        this.parentFrame = frame;

        // Memuat resep dengan exception handling
        try {
            this.recipes = FileHandler.loadRecipes();
        } catch (Exception e) {
            // Jika terjadi kesalahan saat memuat resep
            JOptionPane.showMessageDialog(parentFrame, "Terjadi kesalahan saat memuat resep: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            // this.recipes = List.of(); // Jika gagal, set daftar resep menjadi kosong
        }

        // Set title of the application
        parentFrame.setTitle("Aplikasi Resep Makanan");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel utama (menu)
        JPanel mainMenuPanel = new JPanel(new GridBagLayout());
        mainMenuPanel.setBackground(Color.WHITE); // Background putih

        // Add application title at the top
        JLabel titleLabel = new JLabel("Aplikasi Resep Makanan", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 51));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Spasi antar elemen
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Center alignment with single column
        gbc.anchor = GridBagConstraints.CENTER; // Align center both horizontally and vertically
        mainMenuPanel.add(titleLabel, gbc);

        JButton addRecipeButton = createStyledButton("Tambah Resep");
        JButton viewRecipesButton = createStyledButton("Lihat Resep");

        addRecipeButton.addActionListener(event -> cardLayout.show(cardPanel, "addRecipe"));
        viewRecipesButton.addActionListener(event -> {
            try {
                ViewRecipesPanel viewRecipesPanel = new ViewRecipesPanel(parentFrame, recipes);
                parentFrame.getContentPane().removeAll(); // Menghapus komponen yang ada
                parentFrame.getContentPane().add(viewRecipesPanel); // Menambahkan panel baru
                parentFrame.revalidate();
                parentFrame.repaint();
            } catch (Exception e) {
                // Menangani error saat menampilkan panel Lihat Resep
                JOptionPane.showMessageDialog(parentFrame,
                        "Terjadi kesalahan saat menampilkan resep: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Untuk debugging
            }
        });

        gbc.gridy = 1;
        mainMenuPanel.add(addRecipeButton, gbc);

        gbc.gridy = 2;
        mainMenuPanel.add(viewRecipesButton, gbc);

        // Tambahkan panel ke CardLayout
        cardPanel.add(mainMenuPanel, "mainMenu");
        cardPanel.add(new AddRecipePanel(parentFrame, recipes, () -> cardLayout.show(cardPanel, "mainMenu")),
                "addRecipe");

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
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
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
