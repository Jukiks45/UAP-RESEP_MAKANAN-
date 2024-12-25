package main.gui;

import util.FileHandler;
import main.model.Recipe;

import javax.swing.*;
import java.util.List;

public class DeleteRecipeDialog extends JDialog {
    public DeleteRecipeDialog(JFrame parent, List<Recipe> recipes, Recipe recipeToDelete) {
        super(parent, "Hapus Resep", true);
        int confirm = JOptionPane.showConfirmDialog(
                parent,
                "Apakah Anda yakin ingin menghapus resep ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            recipes.remove(recipeToDelete); // Remove the recipe from the list
            FileHandler.saveRecipes(recipes); // Save updated recipes to file
            JOptionPane.showMessageDialog(parent, "Resep berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
