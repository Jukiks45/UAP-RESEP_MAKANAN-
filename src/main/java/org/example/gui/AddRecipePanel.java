package org.example.gui;

import org.example.util.FileHandler;
import org.example.model.Recipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AddRecipePanel extends JPanel {
    @SuppressWarnings("unused")
    public AddRecipePanel(JFrame parentFrame, List<Recipe> recipes, Runnable onBack) {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 250)); // Warna latar putih terang
        setBorder(new EmptyBorder(20, 40, 20, 40)); // Margin di sekitar konten

        // Judul
        JLabel titleLabel = new JLabel("Tambah Resep Baru", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 102, 204)); // Warna biru
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form input
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin antar elemen form
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nama Resep
        JLabel nameLabel = createStyledLabel("Nama Resep:");
        JTextField nameField = new JTextField();
        addFormElement(formPanel, nameLabel, nameField, gbc, 0);

        // Bahan-Bahan
        JLabel ingredientsLabel = createStyledLabel("Bahan-Bahan:");
        JTextArea ingredientsArea = createStyledTextArea(); // Hanya JTextArea tanpa JScrollPane
        ingredientsArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        ingredientsArea.setPreferredSize(new Dimension(400, 120)); // Ukuran lebar dan tinggi diperbesar
        addFormElement(formPanel, ingredientsLabel, ingredientsArea, gbc, 1);

        // Langkah-Langkah
        JLabel stepsLabel = createStyledLabel("Langkah-Langkah:");
        JTextArea stepsArea = createStyledTextArea(); // Hanya JTextArea tanpa JScrollPane
        stepsArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        stepsArea.setPreferredSize(new Dimension(400, 120)); // Ukuran lebar dan tinggi diperbesar
        addFormElement(formPanel, stepsLabel, stepsArea, gbc, 2);

        add(formPanel, BorderLayout.CENTER);

        // Image selection and preview
        JLabel imageLabel = createStyledLabel("Gambar Resep:");
        JButton imageButton = new JButton("Pilih Gambar");
        JLabel imagePathLabel = new JLabel("Belum ada gambar");
        JLabel imagePreview = new JLabel(); // To display the image preview
        imagePreview.setHorizontalAlignment(SwingConstants.CENTER);
        imagePreview.setPreferredSize(new Dimension(200, 200));
        imagePreview.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        imageButton.addActionListener(event -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
                    String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                    imagePathLabel.setText(selectedPath);

                    // Set image preview
                    ImageIcon icon = new ImageIcon(selectedPath);
                    Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imagePreview.setIcon(new ImageIcon(scaledImage));
                }
            } catch (Exception e) {
                // Menangani error jika terjadi masalah saat memilih atau menampilkan gambar
                JOptionPane.showMessageDialog(parentFrame, "Terjadi kesalahan saat memilih gambar: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Untuk membantu debugging
            }
        });

        addFormElement(formPanel, imageLabel, imageButton, gbc, 3);
        addFormElement(formPanel, new JLabel(""), imagePathLabel, gbc, 4);
        gbc.gridy = 5;
        gbc.gridx = 1;
        formPanel.add(imagePreview, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Tombol Simpan dan Kembali
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton saveButton = new JButton("Simpan");
        styleButton(saveButton, new Color(0, 153, 76), new Color(0, 102, 51));
        saveButton.addActionListener(event -> {
            try {
                String name = nameField.getText().trim();
                String ingredients = ingredientsArea.getText().trim();
                String steps = stepsArea.getText().trim();
                String imagePath = imagePathLabel.getText().equals("Belum ada gambar") ? null
                        : imagePathLabel.getText();

                // Validasi input
                if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Semua field harus diisi!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Menyimpan resep baru
                Recipe recipe = new Recipe(recipes.size() + 1, name, ingredients, steps, imagePath);
                recipes.add(recipe);
                FileHandler.saveRecipes(recipes); // Menyimpan ke file

                JOptionPane.showMessageDialog(parentFrame, "Resep berhasil disimpan!", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                onBack.run(); // Kembali ke panel sebelumnya

            } catch (Exception e) {
                // Menangani semua exception yang terjadi dan menampilkan pesan error
                JOptionPane.showMessageDialog(parentFrame, "Terjadi kesalahan saat menyimpan resep: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Untuk membantu debugging, log ke console
            }
        });

        JButton backButton = new JButton("Kembali");
        styleButton(backButton, new Color(204, 0, 0), new Color(153, 0, 0));
        backButton.addActionListener(event -> onBack.run());

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(0, 102, 204));
        return label;
    }

    private JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea(10, 30); // Ukuran area yang lebih besar
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
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
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
