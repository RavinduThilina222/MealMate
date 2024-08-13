package com.example.mealmate.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private final DatabaseHelper databaseHelper;

    // Constructor
    public Branch(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void addBranch(String name, String address, double latitude, double longitude) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try {
            String query = "INSERT INTO Branches (name, address, latitude, longitude) VALUES (?, ?, ?, ?)";
            db.execSQL(query, new Object[]{name, address, latitude, longitude});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    @SuppressLint("Range")
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM ShopBranches";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Branch branch = new Branch(databaseHelper);
                branch.setId(cursor.getInt(cursor.getColumnIndex("branch_id")));
                branch.setName(cursor.getString(cursor.getColumnIndex("name")));
                branch.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                branch.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
                branch.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
                branches.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return branches;
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon1 - lon2);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public Branch getNearestBranch(double userLatitude, double userLongitude) {
        List<Branch> branches = getAllBranches();
        Branch nearestBranch = null;
        double minDistance = Double.MAX_VALUE;

        for (Branch branch : branches) {
            double distance = calculateDistance(userLatitude, userLongitude, branch.getLatitude(), branch.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestBranch = branch;
            }
        }

        return nearestBranch;
    }
}