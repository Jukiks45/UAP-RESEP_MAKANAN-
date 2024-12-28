package org.example.gui;

import org.example.model.Recipe;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeDetailPanelTest {
    private JFrame testFrame;  
    private Recipe testRecipe;  
    private List<Recipe> recipeList;  
    private Runnable mockOnBack;  

    @BeforeEach
    void setUp() {
        // Inisialisasi objek-objek yang digunakan dalam pengujian
        testFrame = new JFrame();
        testRecipe = new Recipe("Test Recipe", "Ingredients", "Steps", null);
        recipeList = new ArrayList<>();
        recipeList.add(testRecipe);

        mockOnBack = Mockito.mock(Runnable.class);  
    }

    @AfterEach
    void tearDown() {
        // Menutup JFrame setelah setiap pengujian selesai
        testFrame.dispose();
    }

    @Test
    void testPanelInitialization() {
        // Menguji apakah panel diinisialisasi dengan benar
        RecipeDetailPanel panel = new RecipeDetailPanel(testFrame, recipeList, testRecipe, mockOnBack);

        // Memeriksa apakah label judul panel sesuai dengan nama resep
        JLabel titleLabel = (JLabel) panel.getComponent(0);
        assertEquals("Detail Resep: Test Recipe", titleLabel.getText(), "Title label should display recipe name.");

        // Memeriksa apakah panel tombol ada dan memiliki tiga tombol
        JPanel buttonPanel = (JPanel) panel.getComponent(2);
        assertNotNull(buttonPanel, "Button panel should exist.");
        assertEquals(3, buttonPanel.getComponentCount(), "Button panel should contain three buttons.");
    }

    @Test
    void testDeleteButtonAction() {
        // Menguji apakah tombol hapus berfungsi dengan benar
        RecipeDetailPanel panel = new RecipeDetailPanel(testFrame, recipeList, testRecipe, mockOnBack);

        // Mengklik tombol hapus
        JButton deleteButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(1);
        deleteButton.doClick();

        // Memeriksa apakah resep telah dihapus dari daftar
        assertFalse(recipeList.contains(testRecipe), "Recipe should be removed from the list.");
    }

    @Test
    void testBackButtonAction() {
        // Menguji apakah tombol kembali berfungsi dengan benar
        RecipeDetailPanel panel = new RecipeDetailPanel(testFrame, recipeList, testRecipe, mockOnBack);

        // Mengklik tombol kembali
        JButton backButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(2);
        backButton.doClick();

        // Memastikan bahwa tindakan kembali (back) dipanggil
        Mockito.verify(mockOnBack, Mockito.times(1)).run();
    }
}
