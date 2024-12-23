package main.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.util.List;
import main.model.Recipe;

public class ViewRecipesDialog extends JDialog {
    private List<Recipe> recipes;

    public ViewRecipesDialog(JFrame parentFrame, List<Recipe> recipes) {
        super(parentFrame, "Lihat Resep", true);
        this.recipes = recipes;

        // Pengaturan tampilan dialog (misalnya ukuran, layout, dll.)
        setLayout(new BorderLayout());
        setSize(400, 300);

        // Menampilkan resep dalam JList
        JList<Recipe> recipeList = new JList<>(recipes.toArray(new Recipe[0]));
        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        setVisible(true);
    }
}
