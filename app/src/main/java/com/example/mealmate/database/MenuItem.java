package com.example.mealmate.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MenuItem {
    private DatabaseHelper databaseHelper;

    public MenuItem(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long addMenuItem(String category, String name, String description, double price, boolean availability, byte[] image) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Category", category);
        values.put("Name", name);
        values.put("Description", description);
        values.put("Price", price);
        values.put("Availability", availability);
        values.put("Image", image);
        long id = db.insert("MenuItems", null, values);
        db.close();
        return id;
    }

    public Cursor getMenuItem(int itemID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MenuItems", null, "Item_ID=?", new String[]{String.valueOf(itemID)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllMenuItems() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MenuItems", null, null, null, null, null, null);
        ArrayList<Cursor> allMenuItems = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allMenuItems.add(cursor);
            } while (cursor.moveToNext());
        }
        return allMenuItems;
    }

    public int updateMenuItem(int itemID, String category, String name, String description, double price, boolean availability, byte[] image) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Category", category);
        values.put("Name", name);
        values.put("Description", description);
        values.put("Price", price);
        values.put("Availability", availability);
        values.put("Image", image);
        int rowsAffected = db.update("MenuItems", values, "Item_ID=?", new String[]{String.valueOf(itemID)});
        db.close();
        return rowsAffected;
    }

    public int deleteUser(long userId){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int rowsAffected = db.delete("Users", "User_ID=?", new String[]{String.valueOf(userId)});
        db.close();
        return rowsAffected;
    }

    public String getDiscription(int itemID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MenuItems", new String[]{"Description"}, "Item_ID=?", new String[]{String.valueOf(itemID)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getString(cursor.getColumnIndexOrThrow("Description"));
    }



}
