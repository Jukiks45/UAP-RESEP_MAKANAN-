package main.gui;

import util.FileHandler;
import main.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UpdateRecipeDialog extends JDialog {
    public UpdateRecipeDialog(JFrame parent, List<Recipe> recipes, Recipe recipeToUpdate) {
        super(parent, "Update Resep", true);
        setSize(300, 400);
        setLayout(new GridLayout(5, 1));

        JTextField nameField = new JTextField(recipeToUpdate.getName());
        JTextArea ingredientsArea = new JTextArea(recipeToUpdate.getIngredients());
        JTextArea stepsArea = new JTextArea(recipeToUpdate.getSteps());

        JButton updateButton = new JButton("Update");

        add(new JLabel("Nama Resep:"));
        add(nameField);
        add(new JLabel("Bahan-Bahan:"));
        add(new JScrollPane(ingredientsArea));
        add(new JLabel("Langkah-Langkah:"));
        add(new JScrollPane(stepsArea));
        add(updateButton);

        updateButton.addActionListener(event -> {
            String name = nameField.getText().trim();
            String ingredients = ingredientsArea.getText().trim();
            String steps = stepsArea.getText().trim();

            if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the recipe object
            int index = recipes.indexOf(recipeToUpdate); // Find the index of the recipe in the list
            if (index != -1) {
                recipes.get(index).setName(name);
                recipes.get(index).setIngredients(ingredients);
                recipes.get(index).setSteps(steps);
            }

            // Save the updated list to the file
            FileHandler.saveRecipes(recipes);

            JOptionPane.showMessageDialog(this, "Resep berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        setVisible(true);
    }
}
