// FoodItem.java
package com.example.mealmate.admin_activities;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String name;
    private double price;
    private byte[] image;
    private String description;
    private int id;
    private String category;
    private boolean availability;

    public FoodItem(int id, String category, String name, String description, double price, boolean availability, byte[] image) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = this.description;
        this.price = price;
        this.availability = availability;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

}