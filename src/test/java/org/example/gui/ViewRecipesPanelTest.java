package org.example.gui;

import org.example.model.Recipe;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewRecipesPanelTest {
    private JFrame testFrame;
    private List<Recipe> testRecipes;

    @BeforeEach
    void setUp() {
        testFrame = new JFrame();
        testRecipes = new ArrayList<>();
        testRecipes.add(new Recipe("Recipe 1", "Ingredients 1", "Steps 1", null));
        testRecipes.add(new Recipe("Recipe 2", "Ingredients 2", "Steps 2", null));
    }

    @AfterEach
    void tearDown() {
        testFrame.dispose();
    }

    @Test
    void testPanelInitialization() {
        ViewRecipesPanel panel = new ViewRecipesPanel(testFrame, testRecipes);

        // Check title
        JLabel titleLabel = (JLabel) panel.getComponent(0);
        assertEquals("Daftar Resep", titleLabel.getText(), "Title label should display 'Daftar Resep'.");

        // Check for recipe grid or no-recipe message
        JPanel centerPanel = (JPanel) panel.getComponent(1);
        Component centerContent = centerPanel.getComponent(0);
        assertTrue(centerContent instanceof JScrollPane || centerContent instanceof JPanel,
                "Center panel should display recipe grid or no-recipe message.");
    }

    @Test
    void testNoRecipesMessage() {
        ViewRecipesPanel panel = new ViewRecipesPanel(testFrame, new ArrayList<>());

        JPanel centerPanel = (JPanel) panel.getComponent(1);
        Component centerContent = centerPanel.getComponent(0);

        assertTrue(centerContent instanceof JPanel, "Center panel should contain a 'no recipes' message panel.");
        JLabel noRecipesLabel = (JLabel) ((JPanel) centerContent).getComponent(0);
        assertEquals("Tidak ada resep. Tambahkan resep baru!", noRecipesLabel.getText(),
                "No-recipes label should display the correct message.");
    }

    @Test
    void testBackButtonAction() {
        ViewRecipesPanel panel = new ViewRecipesPanel(testFrame, testRecipes);

        JPanel backButtonPanel = (JPanel) panel.getComponent(2);
        JButton backButton = (JButton) backButtonPanel.getComponent(0);

        // Mock MainMenuPanel replacement
        backButton.doClick();
        Component newContent = testFrame.getContentPane().getComponent(0);

        assertTrue(newContent instanceof MainMenuPanel, "Back button should navigate to the main menu panel.");
    }

    @Test
    void testDeleteButtonAction() {
        ViewRecipesPanel panel = new ViewRecipesPanel(testFrame, testRecipes);

        JPanel centerPanel = (JPanel) panel.getComponent(1);
        JScrollPane scrollPane = (JScrollPane) centerPanel.getComponent(0);
        JPanel gridPanel = (JPanel) scrollPane.getViewport().getView();

        // Simulate delete button click for the first recipe
        JPanel firstRecipeCard = (JPanel) gridPanel.getComponent(0);
        JPanel buttonPanel = (JPanel) firstRecipeCard.getComponent(2);
        JButton deleteButton = (JButton) buttonPanel.getComponent(1);
        deleteButton.doClick();

        // Verify recipe removal
        assertEquals(1, testRecipes.size(), "One recipe should be removed after delete action.");
        assertEquals("Recipe 2", testRecipes.get(0).getName(), "The remaining recipe should be 'Recipe 2'.");
    }
}
