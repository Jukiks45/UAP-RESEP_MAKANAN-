package util;

import main.model.Recipe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "recipes.txt";

    public static List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 4);
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String ingredients = parts[2];
                    String steps = parts[3];
                    recipes.add(new Recipe(id, name, ingredients, steps));
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
                writer.write(recipe.getId() + "|" + recipe.getName() + "|" + recipe.getIngredients() + "|" + recipe.getSteps());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
}
