package main.gui;

import main.model.Recipe;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewRecipesPanel extends JPanel {

    private final JFrame parentFrame;
    private List<Recipe> recipes;

    public ViewRecipesPanel(JFrame parentFrame, List<Recipe> recipes) {
        this.parentFrame = parentFrame;
        this.recipes = recipes;

        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("Daftar Resep", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        if (recipes.isEmpty()) {
            // Jika tidak ada resep, tampilkan pesan "Tidak ada resep"
            showNoRecipesMessage();
        } else {
            // Jika ada resep, tampilkan daftar resep
            refreshGrid();
        }
    }

    @SuppressWarnings("unused")
    private void showNoRecipesMessage() {
        JPanel noRecipesPanel = new JPanel();
        noRecipesPanel.setLayout(new BorderLayout());
        noRecipesPanel.setBackground(new Color(250, 250, 250));

        JLabel noRecipesLabel = new JLabel("Tidak ada resep. Tambahkan resep baru!", SwingConstants.CENTER);
        noRecipesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        noRecipesLabel.setForeground(new Color(255, 0, 0));
        noRecipesPanel.add(noRecipesLabel, BorderLayout.CENTER);

        // Tombol Kembali
        JButton backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> goBack());

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel.setBackground(new Color(250, 250, 250));
        backButtonPanel.add(backButton);
        noRecipesPanel.add(backButtonPanel, BorderLayout.SOUTH);

        removeAll(); // Clear current panel
        add(noRecipesPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void goBack() {
        // Tindakan untuk kembali ke panel sebelumnya
        // Misalnya, kembali ke panel utama atau panel yang lebih tinggi
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(new MainMenuPanel(parentFrame));  // Misalnya MainPanel adalah panel utama Anda
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void refreshGrid() {
        JPanel gridPanel = createRecipeGrid(recipes);
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);

        removeAll(); // Clear current panel
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel createRecipeGrid(List<Recipe> recipes) {
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 15, 15)); // 2 columns, dynamic rows
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridPanel.setBackground(new Color(250, 250, 250));

        for (Recipe recipe : recipes) {
            JPanel recipeCard = createRecipeCard(recipe);
            gridPanel.add(recipeCard);
        }

        return gridPanel;
    }

    @SuppressWarnings("unused")
    private JPanel createRecipeCard(Recipe recipe) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        cardPanel.setBackground(Color.WHITE);

        // Title
        JLabel nameLabel = new JLabel(recipe.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(new Color(0, 102, 204));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cardPanel.add(nameLabel, BorderLayout.NORTH);

        // Image
        if (recipe.getImagePath() != null) {
            ImageIcon imageIcon = new ImageIcon(recipe.getImagePath());
            JLabel imageLabel = new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            cardPanel.add(imageLabel, BorderLayout.CENTER);
        }
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton detailButton = new JButton("Lihat Detail");
        styleButton(detailButton, new Color(0, 153, 76), new Color(0, 102, 51));
        detailButton.addActionListener(e -> showRecipeDetail(recipe));

        JButton deleteButton = new JButton("Hapus");
        styleButton(deleteButton, new Color(204, 0, 0), new Color(153, 0, 0));
        deleteButton.addActionListener(e -> deleteRecipe(recipe));

        buttonPanel.add(detailButton);
        buttonPanel.add(deleteButton);

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    private void deleteRecipe(Recipe recipe) {
        int confirmation = JOptionPane.showConfirmDialog(parentFrame, "Apakah Anda yakin ingin menghapus resep ini?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            recipes.remove(recipe);
            FileHandler.saveRecipes(recipes);
            JOptionPane.showMessageDialog(parentFrame, "Resep berhasil dihapus!");
            refreshGrid(); // Refresh the grid after deletion
        }
    }

    private void showRecipeDetail(Recipe recipe) {
        removeAll();
        add(new RecipeDetailPanel(parentFrame, recipes, recipe, this::refreshGrid), BorderLayout.CENTER);
        revalidate();
        repaint();
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
