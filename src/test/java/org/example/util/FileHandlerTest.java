package org.example.util;

import org.example.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private static final String FILE_PATH = "recipes.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Mengosongkan file sebelum setiap tes dijalankan.
        Files.write(Paths.get(FILE_PATH), new byte[0]);
    }

    @Test
    public void testLoadRecipes_ShouldLoadCorrectly() throws IOException {
        // Menyimpan data ke dalam file dan memverifikasi pemuatan resep
        FileHandler.saveRecipes(Arrays.asList(
                new Recipe(1, "nasigoreng", "nasi", "digoreng", "D:\\nasigoreng.jpg"),
                new Recipe(2, "mieayam", "mie", "direbus", "D:\\mieayam.jpg")));
        List<Recipe> recipes = FileHandler.loadRecipes();
        assertEquals(2, recipes.size());
    }

    @Test
    public void testLoadRecipes_ShouldReturnEmptyListForEmptyFile() {
        try {
            // Mengosongkan file dan memverifikasi list kosong
            Files.write(Paths.get(FILE_PATH), new byte[0]);
            List<Recipe> recipes = FileHandler.loadRecipes();
            assertTrue(recipes.isEmpty());
        } catch (IOException e) {
            fail("Terjadi kesalahan: " + e.getMessage());
        }
    }

    @Test
    public void testSaveRecipes_ShouldSaveCorrectly() throws IOException {
        // Menyimpan resep dan memverifikasi data yang disimpan
        Recipe recipe = new Recipe(1, "soto", "daging, sayur", "Rebus daging dan sayur", "D:\\soto.jpg");
        FileHandler.saveRecipes(Arrays.asList(recipe));
        List<Recipe> recipes = FileHandler.loadRecipes();
        assertEquals(1, recipes.size());
    }

    @Test
    public void testSaveRecipes_ShouldOverwriteFile() throws IOException {
        // Menyimpan resep baru yang akan menimpa data lama
        Recipe recipe1 = new Recipe(1, "soto", "daging, sayur", "Rebus daging dan sayur", "D:\\soto.jpg");
        FileHandler.saveRecipes(Arrays.asList(recipe1));
        Recipe recipe2 = new Recipe(2, "mie", "mie, bumbu", "Rebus mie dan tambahkan bumbu", "D:\\mie.jpg");
        FileHandler.saveRecipes(Arrays.asList(recipe2));
        List<Recipe> recipes = FileHandler.loadRecipes();
        assertEquals(1, recipes.size()); // Pastikan hanya satu resep yang tersimpan
    }
}
