package com.example.mealmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class User {
    private final DatabaseHelper dbHelper;

    public User(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addUser(String username, String email, String phoneNo, String password, String gender, String address, String birthday, String userLevel, double latitude, double longitude, byte[] profilePicture) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Email", email);
        values.put("Phone_No", phoneNo);
        values.put("Password", password);
        values.put("Gender", gender);
        values.put("Address", address);
        values.put("Birthday", birthday);
        values.put("User_Level", userLevel);
        values.put("Latitude", latitude);
        values.put("Longitude", longitude);
        values.put("Profile_Picture", profilePicture);
        long id = db.insert("Users", null, values);
        db.close();
        return id;
    }

    public Cursor getUser(long userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Users", null, "User_ID=?", new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllUsers() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Users", null, null, null, null, null, null);
        ArrayList<Cursor> allUsers = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allUsers.add(cursor);
            } while (cursor.moveToNext());
        }
        return allUsers;
    }

    public int updateUser(long userId, String username, String email, String phoneNo, String password, String gender, String address, String birthday, String userLevel, double latitude, double longitude, byte[] profilePicture) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Email", email);
        values.put("Phone_No", phoneNo);
        values.put("Password", password);
        values.put("Gender", gender);
        values.put("Address", address);
        values.put("Birthday", birthday);
        values.put("User_Level", userLevel);
        values.put("Latitude", latitude);
        values.put("Longitude", longitude);
        values.put("Profile_Picture", profilePicture);
        int rowsAffected = db.update("Users", values, "User_ID=?", new String[]{String.valueOf(userId)});
        db.close();
        return rowsAffected;
    }

    public int deleteUser(long userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("Users", "User_ID=?", new String[]{String.valueOf(userId)});
        db.close();
        return rowsAffected;
    }

    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Users", null, "Username=? AND Password=?", new String[]{username, password}, null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }
}