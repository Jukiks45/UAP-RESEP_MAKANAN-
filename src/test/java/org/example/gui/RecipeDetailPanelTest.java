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
        testFrame = new JFrame();
        testRecipe = new Recipe("Test Recipe", "Ingredients", "Steps", null);
        recipeList = new ArrayList<>();
        recipeList.add(testRecipe);

        mockOnBack = Mockito.mock(Runnable.class);
    }

    @AfterEach
    void tearDown() {
        testFrame.dispose();
    }

    @Test
    void testPanelInitialization() {
        RecipeDetailPanel panel = new RecipeDetailPanel(testFrame, recipeList, testRecipe, mockOnBack);

        // Check title
        JLabel titleLabel = (JLabel) panel.getComponent(0);
        assertEquals("Detail Resep: Test Recipe", titleLabel.getText(), "Title label should display recipe name.");

        // Check button panel
        JPanel buttonPanel = (JPanel) panel.getComponent(2);
        assertNotNull(buttonPanel, "Button panel should exist.");
        assertEquals(3, buttonPanel.getComponentCount(), "Button panel should contain three buttons.");
    }

    @Test
    void testDeleteButtonAction() {
        RecipeDetailPanel panel = new RecipeDetailPanel(testFrame, recipeList, testRecipe, mockOnBack);

        JButton deleteButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(1);
        deleteButton.doClick();

        // Verify the recipe is removed
        assertFalse(recipeList.contains(testRecipe), "Recipe should be removed from the list.");
    }

    @Test
    void testBackButtonAction() {
        RecipeDetailPanel panel = new RecipeDetailPanel(testFrame, recipeList, testRecipe, mockOnBack);

        JButton backButton = (JButton) ((JPanel) panel.getComponent(2)).getComponent(2);
        backButton.doClick();

        // Verify the onBack callback is called
        Mockito.verify(mockOnBack, Mockito.times(1)).run();
    }
}
