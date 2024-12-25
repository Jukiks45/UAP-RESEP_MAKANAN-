package main.model;

public class Recipe {
    public int id;
    public String name;
    public String ingredients;
    public String steps;

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

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
