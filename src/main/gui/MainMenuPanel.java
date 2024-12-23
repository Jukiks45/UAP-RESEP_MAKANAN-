package main.gui;

import util.FileHandler;
import main.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private JFrame parentFrame;
    private List<Recipe> recipes;

    public MainMenuPanel(JFrame frame) {
        this.parentFrame = frame;
        this.recipes = new ArrayList<>(FileHandler.loadRecipes());
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton addRecipeButton = new JButton("Tambah Resep");
        JButton viewRecipesButton = new JButton("Lihat Resep");

        addRecipeButton.addActionListener(event -> new AddRecipeDialog(parentFrame, recipes));
        viewRecipesButton.addActionListener(event -> new ViewRecipesDialog(parentFrame, recipes));

        add(addRecipeButton);
        add(viewRecipesButton);
    }
}
