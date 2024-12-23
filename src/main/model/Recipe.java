package main.model;

public class Recipe {
    private int id;
    private String name;
    private String ingredients;
    private String steps;

    public Recipe(int id, String name, String ingredients, String steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

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

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
            "Nama: " + name + "\n" +
            "Bahan-Bahan: " + ingredients + "\n" +
            "Langkah-Langkah: " + steps;
    }
}
