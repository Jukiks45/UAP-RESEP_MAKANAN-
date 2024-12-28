package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {

    @Test
    public void testPembuatanRecipe() {
        // Membuat objek Recipe untuk pengujian
        Recipe recipe = new Recipe(1, "Nasi Goreng", "Nasi, Bumbu", "Masak nasi dan bumbu", "nasi.jpg");
        
        // Memastikan nilai properti objek sesuai dengan input yang diberikan
        assertEquals(1, recipe.getId());  
        assertEquals("Nasi Goreng", recipe.getName());  
        assertEquals("Nasi, Bumbu", recipe.getIngredients());  
        assertEquals("Masak nasi dan bumbu", recipe.getSteps());  
        assertEquals("nasi.jpg", recipe.getImagePath());  
    }
}
