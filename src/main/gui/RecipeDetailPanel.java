package main.gui;

import main.model.Recipe;
import util.FileHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class RecipeDetailPanel extends JPanel {

    public RecipeDetailPanel(JFrame parentFrame, List<Recipe> recipes, Recipe recipe, Runnable onBack) {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 250));
        setBorder(new EmptyBorder(20, 40, 20, 40));

        // Title
        JLabel titleLabel = new JLabel("Detail Resep: " + recipe.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(250, 250, 250));

        // Add image if available
        if (recipe.getImagePath() != null) {
            JLabel imageLabel = createImageLabel(recipe.getImagePath());
            contentPanel.add(imageLabel, BorderLayout.NORTH);
        }

        JPanel detailsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        detailsPanel.setBackground(new Color(250, 250, 250));
        detailsPanel.add(createLabel("Bahan-Bahan:", recipe.getIngredients()));
        detailsPanel.add(createLabel("Langkah-Langkah:", recipe.getSteps()));

        contentPanel.add(detailsPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton updateButton = new JButton("Update");
        styleButton(updateButton, new Color(0, 153, 76), new Color(0, 102, 51));
        updateButton.addActionListener(e -> {
            removeAll();
            add(new UpdateRecipePanel(parentFrame, recipes, recipe, onBack), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        JButton deleteButton = new JButton("Hapus");
        styleButton(deleteButton, new Color(204, 0, 0), new Color(153, 0, 0));
        deleteButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(parentFrame, "Apakah Anda yakin ingin menghapus resep ini?",
                    "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                recipes.remove(recipe);
                FileHandler.saveRecipes(recipes);
                JOptionPane.showMessageDialog(parentFrame, "Resep berhasil dihapus!");
                onBack.run();
            }
        });

        JButton backButton = new JButton("Kembali");
        styleButton(backButton, new Color(0, 102, 204), new Color(0, 51, 102));
        backButton.addActionListener(e -> onBack.run());

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createImageLabel(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        return imageLabel;
    }

    private JPanel createLabel(String title, String content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea contentArea = new JTextArea(content);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(contentArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void styleButton(JButton button, Color bgColor, Color hoverColor) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(hoverColor, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
}
