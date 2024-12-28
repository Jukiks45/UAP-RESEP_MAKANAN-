package org.example.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainMenuPanelTest {

    private JFrame mockFrame;  

    @BeforeEach
    public void setUp() {
        //mockFrame diinisialisasi menggunakan Mockito untuk meniru perilaku JFrame
        mockFrame = Mockito.mock(JFrame.class); 
    }

    @Test
    public void testMainMenuPanel() {
        // Menguji pembuatan objek MainMenuPanel dengan parameter mockFrame
        MainMenuPanel panel = new MainMenuPanel(mockFrame);
        
        // Memastikan bahwa objek MainMenuPanel yang dibuat tidak bernilai null
        // Ini memastikan bahwa konstruksi MainMenuPanel berhasil dengan mockFrame yang diberikan
        assertNotNull(panel); 
    }
}

