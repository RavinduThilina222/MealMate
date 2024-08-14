package com.example.mealmate.admin_activities;

public class FoodItem {
    private int itemId;
    private String name;
    private String description;
    private double price;
    private boolean availability;
    private byte[] image;

    public FoodItem(int itemId, String name, String description, double price, boolean availability, byte[] image) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.image = image;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}