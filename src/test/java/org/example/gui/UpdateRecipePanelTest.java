package org.example.gui;

import org.example.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SuppressWarnings("unused")
public class UpdateRecipePanelTest {

    private JFrame mockFrame;
    private List<Recipe> recipes;
    private Recipe mockRecipe;
    private Runnable mockOnBack;

    @BeforeEach
    public void siapkan() {
        // Mock objek dan data yang dibutuhkan untuk pengujian
        mockFrame = mock(JFrame.class);
        recipes = new ArrayList<>();
        mockRecipe = new Recipe(1, "Resep Uji", "Bahan Uji", "Langkah Uji", null);
        recipes.add(mockRecipe);
        mockOnBack = mock(Runnable.class);
    }

    @SuppressWarnings("static-access")
    @Test
    public void ujiFieldTidakBolehKosong() {
        // Membuat panel dan mencari field input
        UpdateRecipePanel panel = new UpdateRecipePanel(mockFrame, recipes, mockRecipe, mockOnBack);
        JPanel formPanel = (JPanel) panel.getComponent(1);
        JTextField nameField = (JTextField) formPanel.getComponent(1);
        JTextArea ingredientsArea = (JTextArea) ((JScrollPane) formPanel.getComponent(3)).getViewport().getView();
        JTextArea stepsArea = (JTextArea) ((JScrollPane) formPanel.getComponent(5)).getViewport().getView();

        // Kosongkan field dan tes validasi
        nameField.setText("");
        ingredientsArea.setText("");
        stepsArea.setText("");
        
        // Verifikasi bahwa pesan error ditampilkan jika ada field kosong
        JOptionPane spyOptionPane = spy(JOptionPane.class);
        JButton saveButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(0);
        String name = nameField.getText().trim();
        String ingredients = ingredientsArea.getText().trim();
        String steps = stepsArea.getText().trim();

        if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
            verify(spyOptionPane, times(1)).showMessageDialog(any(Component.class), eq("Semua field harus diisi!"), eq("Error"), eq(JOptionPane.ERROR_MESSAGE));
        }
    }
}
