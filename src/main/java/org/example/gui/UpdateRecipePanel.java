package org.example.gui;

import org.example.util.FileHandler;
import org.example.model.Recipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class UpdateRecipePanel extends JPanel {
    public UpdateRecipePanel(JFrame parentFrame, List<Recipe> recipes, Recipe recipeToUpdate, Runnable onBack) {
        try {
            setLayout(new BorderLayout());
            setBackground(new Color(250, 250, 250)); // Bright white background
            setBorder(new EmptyBorder(20, 40, 20, 40)); // Margins around the content

            // Title
            JLabel titleLabel = new JLabel("Update Resep", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
            titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
            titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
            add(titleLabel, BorderLayout.NORTH);

            // Form Input
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(new Color(250, 250, 250));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Margin between form elements
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Recipe Name
            JLabel nameLabel = createStyledLabel("Nama Resep:");
            JTextField nameField = new JTextField(recipeToUpdate.getName());
            addFormElement(formPanel, nameLabel, nameField, gbc, 0);

            // Ingredients
            JLabel ingredientsLabel = createStyledLabel("Bahan-Bahan:");
            JTextArea ingredientsArea = createStyledTextArea();
            ingredientsArea.setText(recipeToUpdate.getIngredients());
            JScrollPane ingredientsScroll = new JScrollPane(ingredientsArea);
            addFormElement(formPanel, ingredientsLabel, ingredientsScroll, gbc, 1);

            // Steps
            JLabel stepsLabel = createStyledLabel("Langkah-Langkah:");
            JTextArea stepsArea = createStyledTextArea();
            stepsArea.setText(recipeToUpdate.getSteps());
            JScrollPane stepsScroll = new JScrollPane(stepsArea);
            addFormElement(formPanel, stepsLabel, stepsScroll, gbc, 2);

            add(formPanel, BorderLayout.CENTER);

            // Save and Back Buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(250, 250, 250));

            JButton saveButton = new JButton("Simpan");
            styleButton(saveButton, new Color(0, 153, 76), new Color(0, 102, 51));
            saveButton.addActionListener(event -> {
                try {
                    String name = nameField.getText().trim();
                    String ingredients = ingredientsArea.getText().trim();
                    String steps = stepsArea.getText().trim();

                    if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
                        if (parentFrame != null) {
                            JOptionPane.showMessageDialog(parentFrame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            System.out.println("parentFrame bernilai null. Dialog tidak ditampilkan.");
                        }
                        return;
                    }

                    recipeToUpdate.setName(name);
                    recipeToUpdate.setIngredients(ingredients);
                    recipeToUpdate.setSteps(steps);

                    FileHandler.saveRecipes(recipes);
                    if (parentFrame != null) {
                        JOptionPane.showMessageDialog(parentFrame, "Resep berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        System.out.println("parentFrame bernilai null. Dialog tidak ditampilkan.");
                    }
                    onBack.run();
                } catch (Exception ex) {
                    if (parentFrame != null) {
                        JOptionPane.showMessageDialog(parentFrame, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("parentFrame bernilai null. Dialog tidak ditampilkan.");
                    }
                }
            });

            JButton backButton = new JButton("Kembali");
            styleButton(backButton, new Color(204, 0, 0), new Color(153, 0, 0));
            backButton.addActionListener(e -> {
                try {
                    if (parentFrame != null) {
                        // Replace the current panel with ViewRecipesPanel
                        parentFrame.getContentPane().removeAll();
                        parentFrame.getContentPane().add(new RecipeDetailPanel(parentFrame, recipes, recipeToUpdate, onBack));
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    } else {
                        System.out.println("parentFrame bernilai null. Panel tidak diperbarui.");
                    }
                } catch (Exception ex) {
                    if (parentFrame != null) {
                        JOptionPane.showMessageDialog(parentFrame, "Error while going back: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("parentFrame bernilai null. Dialog tidak ditampilkan.");
                    }
                }
            });

            buttonPanel.add(saveButton);
            buttonPanel.add(backButton);
            add(buttonPanel, BorderLayout.SOUTH);
        } catch (Exception ex) {
            if (parentFrame != null) {
                JOptionPane.showMessageDialog(parentFrame, "Terjadi kesalahan saat memuat panel: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("parentFrame bernilai null. Dialog tidak ditampilkan.");
            }
        }
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(0, 102, 204));
        return label;
    }

    private JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private void addFormElement(JPanel formPanel, JComponent label, JComponent input, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;
        formPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        formPanel.add(input, gbc);
    }

    private void styleButton(JButton button, Color bgColor, Color hoverColor) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(hoverColor, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
}
