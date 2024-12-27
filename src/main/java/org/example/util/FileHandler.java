package org.example.util;

import org.example.model.Recipe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Default file path
    private static final String FILE_PATH = "recipes.txt";

    public static List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            // Jika file tidak ada atau kosong, langsung return list kosong
            return recipes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 5);

                // Validasi jumlah kolom
                if (parts.length >= 4) {
                    try {
                        int id = Integer.parseInt(parts[0]); // Parsing ID
                        String name = parts[1];
                        String ingredients = parts[2];
                        String steps = parts[3];
                        String imagePath = parts.length == 5 ? parts[4] : null;

                        // Tambahkan recipe ke list jika valid
                        recipes.add(new Recipe(id, name, ingredients, steps, imagePath));
                    } catch (NumberFormatException e) {
                        // Handle invalid ID format (bukan angka)
                        System.out.println("Invalid ID format in line: " + line);
                    }
                } else {
                    // Handle invalid line format
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }

        return recipes;
    }

    public static void saveRecipes(List<Recipe> recipes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Recipe recipe : recipes) {
                // Write recipe data to file, handle null imagePath by using an empty string
                writer.write(recipe.getId() + "|" + recipe.getName() + "|" + recipe.getIngredients() + "|" +
                        recipe.getSteps() + "|" + (recipe.getImagePath() != null ? recipe.getImagePath() : ""));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
}
