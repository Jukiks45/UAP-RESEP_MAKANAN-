package org.example.gui;

import org.example.gui.AddRecipePanel;
import org.example.model.Recipe;
import org.example.util.FileHandler;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@SuppressWarnings("unused")
class AddRecipePanelTest {

    @SuppressWarnings("static-access")
    @Test
    void testSaveButtonDenganFieldKosong() {
        // Arrange
        JFrame parentFrame = mock(JFrame.class); // Mocking JFrame
        List<Recipe> recipes = new ArrayList<>();
        Runnable onBack = mock(Runnable.class);
        AddRecipePanel addRecipePanel = new AddRecipePanel(parentFrame, recipes, onBack);

        // Mock JOptionPane to avoid real dialog box
        JOptionPane mockOptionPane = mock(JOptionPane.class);

        try {
            // Set JOptionPane to use mock
            Field field = JOptionPane.class.getDeclaredField("pane");
            field.setAccessible(true);
            field.set(null, mockOptionPane);

            // Act
            // Get the save button and simulate a click on it
            JButton saveButton = (JButton) addRecipePanel.getComponent(3);
            saveButton.doClick();

            // Verify that the mockOptionPane's showMessageDialog was called with the correct message
            verify(mockOptionPane, times(1)).showMessageDialog(
                    eq(parentFrame),
                    eq("Semua field harus diisi!"),
                    eq("Error"),
                    eq(JOptionPane.ERROR_MESSAGE)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
