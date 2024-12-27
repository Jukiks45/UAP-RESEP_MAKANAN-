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
        mockFrame = Mockito.mock(JFrame.class);
    }

    @Test
    public void testMainMenuPanel() {
        MainMenuPanel panel = new MainMenuPanel(mockFrame);
        
        assertNotNull(panel);
        // Anda bisa melanjutkan untuk menguji interaksi tombol
    }
}
