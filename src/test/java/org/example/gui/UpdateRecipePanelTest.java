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

public class UpdateRecipePanelTest {

    private JFrame mockFrame;
    private List<Recipe> recipes;
    private Recipe mockRecipe;
    private Runnable mockOnBack;

    @BeforeEach
    public void setUp() {
        mockFrame = mock(JFrame.class);  // Mock JFrame untuk dialog
        recipes = new ArrayList<>();
        mockRecipe = new Recipe(1, "Test Recipe", "Test Ingredients", "Test Steps", null);
        recipes.add(mockRecipe);
        mockOnBack = mock(Runnable.class);
    }

    @Test
    public void testFieldsCannotBeEmpty() {
        // Membuat UpdateRecipePanel
        UpdateRecipePanel panel = new UpdateRecipePanel(mockFrame, recipes, mockRecipe, mockOnBack);

        // Temukan komponen JTextField dan JTextArea dengan menelusuri komponen panel
        JPanel formPanel = (JPanel) panel.getComponent(1);  // Panel form biasanya ada di komponen kedua
        JTextField nameField = (JTextField) formPanel.getComponent(1);  // Baris pertama
        JTextArea ingredientsArea = (JTextArea) ((JScrollPane) formPanel.getComponent(3)).getViewport().getView();  // Baris kedua
        JTextArea stepsArea = (JTextArea) ((JScrollPane) formPanel.getComponent(5)).getViewport().getView();  // Baris ketiga

        // Set semua field ke nilai kosong
        nameField.setText("");
        ingredientsArea.setText("");
        stepsArea.setText("");

        // Mock JOptionPane untuk mencegah interaksi dengan frame asli
        JOptionPane spyOptionPane = spy(JOptionPane.class);

        // Panggil saveButton yang di-trigger untuk memicu aksi tombol
        JButton saveButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(0);
        
        // Cek validasi manual tanpa klik tombol
        String name = nameField.getText().trim();
        String ingredients = ingredientsArea.getText().trim();
        String steps = stepsArea.getText().trim();

        if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
            // Verifikasi bahwa JOptionPane menunjukkan pesan kesalahan
            verify(spyOptionPane, times(1)).showMessageDialog(any(Component.class), eq("Semua field harus diisi!"), eq("Error"), eq(JOptionPane.ERROR_MESSAGE));
        }
    }
}
