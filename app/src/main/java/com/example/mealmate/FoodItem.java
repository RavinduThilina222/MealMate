package com.example.mealmate;

public class FoodItem {
    private String name;
    private double price;
    private int imageResId; // Use int for resource ID

    public FoodItem(String name, double price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}