// FoodItem.java
package com.example.mealmate.admin_activities;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String name;
    private double price;
    private byte[] image;
    private String description;
    private int id;

    public FoodItem(String name, double price, byte[] image) {
        this.name = name;
        this.price = price;
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
}