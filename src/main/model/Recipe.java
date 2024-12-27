package main.model;

public class Recipe {
    private int id;
    private String name;
    private String ingredients;
    private String steps;
    private String imagePath; // New field for image path

    public Recipe(int id, String name, String ingredients, String steps, String imagePath) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nama: " + name + "\n" +
                "Bahan-Bahan: " + ingredients + "\n" +
                "Langkah-Langkah: " + steps + "\n" +
                "Gambar: " + (imagePath == null ? "Tidak ada" : imagePath);
    }
}
