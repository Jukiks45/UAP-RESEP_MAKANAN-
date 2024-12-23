package main.gui;

import util.FileHandler;
import main.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddRecipeDialog extends JDialog {
    public AddRecipeDialog(JFrame parent, List<Recipe> recipes) {
        super(parent, "Tambah Resep", true);
        setSize(300, 400);
        setLayout(new GridLayout(5, 1));

        JTextField nameField = new JTextField();
        JTextArea ingredientsArea = new JTextArea();
        JTextArea stepsArea = new JTextArea();

        JButton saveButton = new JButton("Simpan");

        add(new JLabel("Nama Resep:"));
        add(nameField);
        add(new JLabel("Bahan-Bahan:"));
        add(new JScrollPane(ingredientsArea));
        add(new JLabel("Langkah-Langkah:"));
        add(new JScrollPane(stepsArea));
        add(saveButton);

        saveButton.addActionListener(event -> {
            String name = nameField.getText();
            String ingredients = ingredientsArea.getText();
            String steps = stepsArea.getText();

            if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Recipe recipe = new Recipe(recipes.size() + 1, name, ingredients, steps);
            recipes.add(recipe);
            FileHandler.saveRecipes(recipes);
            JOptionPane.showMessageDialog(this, "Resep berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        setVisible(true);
    }
}
