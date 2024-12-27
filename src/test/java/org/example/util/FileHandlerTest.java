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
        // Mengosongkan file sebelum setiap tes dijalankan
        Files.write(Paths.get(FILE_PATH), new byte[0]);
    }

    @Test
    public void testLoadRecipes_ShouldLoadCorrectly() throws IOException {
        // Menyiapkan data yang akan dimuat
        @SuppressWarnings("unused")
        String testData = "1|nasigoreng|nasi|digoreng|D:\\nasigoreng.jpg\n" +
                "2|mieayam|mie|direbus|D:\\mieayam.jpg";

        // Simulasikan pembuatan file dengan data
        @SuppressWarnings("unused")
        File file = new File(FILE_PATH);
        FileHandler.saveRecipes(Arrays.asList(
                new Recipe(1, "nasigoreng", "nasi", "digoreng", "D:\\nasigoreng.jpg"),
                new Recipe(2, "mieayam", "mie", "direbus", "D:\\mieayam.jpg")));

        // Memuat resep dari file
        List<Recipe> recipes = FileHandler.loadRecipes();

        // Verifikasi
        assertEquals(2, recipes.size());
        assertEquals("nasigoreng", recipes.get(0).getName());
        assertEquals("mieayam", recipes.get(1).getName());
    }

    @Test
    public void testLoadRecipes_ShouldReturnEmptyListForEmptyFile() {
        try {
            // Mengosongkan file sebelum tes
            Files.write(Paths.get(FILE_PATH), new byte[0]);

            // Panggil metode untuk memuat resep
            List<Recipe> recipes = FileHandler.loadRecipes();

            // Verifikasi bahwa hasilnya adalah list kosong
            assertTrue(recipes.isEmpty(), "List seharusnya kosong jika file kosong");
        } catch (IOException e) {
            fail("Terjadi kesalahan saat mengosongkan file: " + e.getMessage());
        }
    }

    @Test
    public void testSaveRecipes_ShouldSaveCorrectly() throws IOException {
        // Buat data resep baru
        Recipe recipe = new Recipe(1, "soto", "daging, sayur", "Rebus daging dan sayur", "D:\\soto.jpg");

        // Simpan resep ke file
        FileHandler.saveRecipes(Arrays.asList(recipe));

        // Muat ulang file dan pastikan data sama
        List<Recipe> recipes = FileHandler.loadRecipes();

        // Verifikasi bahwa data yang disimpan benar
        assertEquals(1, recipes.size());
        assertEquals("soto", recipes.get(0).getName());
        assertEquals("daging, sayur", recipes.get(0).getIngredients());
        assertEquals("Rebus daging dan sayur", recipes.get(0).getSteps());
        assertEquals("D:\\soto.jpg", recipes.get(0).getImagePath());
    }

    @Test
    public void testSaveRecipes_ShouldOverwriteFile() throws IOException {
        // Menyimpan resep pertama
        Recipe recipe1 = new Recipe(1, "soto", "daging, sayur", "Rebus daging dan sayur", "D:\\soto.jpg");
        FileHandler.saveRecipes(Arrays.asList(recipe1));

        // Simulasi data baru yang akan disimpan (overwrite)
        Recipe recipe2 = new Recipe(2, "mie", "mie, bumbu", "Rebus mie dan tambahkan bumbu", "D:\\mie.jpg");
        FileHandler.saveRecipes(Arrays.asList(recipe2));

        // Memuat resep setelah overwrite
        List<Recipe> recipes = FileHandler.loadRecipes();

        // Verifikasi
        assertEquals(1, recipes.size()); // Harusnya hanya ada satu resep setelah overwrite
        assertEquals("mie", recipes.get(0).getName()); // Resep yang tersimpan adalah resep yang baru
    }
}
