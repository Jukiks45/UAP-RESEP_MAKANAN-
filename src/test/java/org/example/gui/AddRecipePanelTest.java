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
        
        JFrame parentFrame = mock(JFrame.class); 
        List<Recipe> recipes = new ArrayList<>(); 
        Runnable onBack = mock(Runnable.class); 
        AddRecipePanel addRecipePanel = new AddRecipePanel(parentFrame, recipes, onBack); 

        // Mock JOptionPane
        JOptionPane mockOptionPane = mock(JOptionPane.class);

        try {
            // Mengatur JOptionPane menggunakan mock
            Field field = JOptionPane.class.getDeclaredField("pane");
            field.setAccessible(true);
            field.set(null, mockOptionPane);

            // Simulasi klik tombol save
            JButton saveButton = (JButton) addRecipePanel.getComponent(3);
            saveButton.doClick();

            // Memastikan dialog error ditampilkan
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
