package main.gui;

import main.model.Recipe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewRecipesDialog extends JDialog {

    public ViewRecipesDialog(JFrame parentFrame, List<Recipe> recipes) {
        super(parentFrame, "Lihat Resep", true);

        setLayout(new BorderLayout());
        setSize(600, 400);

        // Custom table model to make cells non-editable
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Nama Resep", "Bahan-Bahan", "Langkah-Langkah"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        JTable recipeTable = new JTable(tableModel);

        // Populate the table with recipes
        for (Recipe recipe : recipes) {
            tableModel.addRow(new Object[]{
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getIngredients(),
                    recipe.getSteps()
            });
        }

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(recipeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel with buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton updateButton = new JButton("Update Resep");
        JButton deleteButton = new JButton("Hapus Resep");

        updateButton.addActionListener(event -> {
            int selectedRow = recipeTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih resep terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Recipe selectedRecipe = recipes.get(selectedRow);
            new UpdateRecipeDialog(parentFrame, recipes, selectedRecipe);

            // Refresh the table after updating
            refreshTable(tableModel, recipes);
        });

        deleteButton.addActionListener(event -> {
            int selectedRow = recipeTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih resep terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Recipe selectedRecipe = recipes.get(selectedRow);
            new DeleteRecipeDialog(parentFrame, recipes, selectedRecipe);

            // Refresh the table after deletion
            refreshTable(tableModel, recipes);
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void refreshTable(DefaultTableModel tableModel, List<Recipe> recipes) {
        tableModel.setRowCount(0); // Clear the table
        for (Recipe recipe : recipes) {
            tableModel.addRow(new Object[]{
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getIngredients(),
                    recipe.getSteps()
            });
        }
    }
}
