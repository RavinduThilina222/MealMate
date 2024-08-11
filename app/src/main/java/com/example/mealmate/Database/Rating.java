package com.example.mealmate.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Rating {
    private DatabaseHelper dbHelper;

    public Rating(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addRating(long userId, long itemId, int rating) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_ID", userId);
        values.put("Item_ID", itemId);
        values.put("Rating", rating);
        long id = db.insert("Ratings", null, values);
        db.close();
        return id;
    }

    public int updateRating(long userId, long itemId, int rating) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Rating", rating);
        int count = db.update("Ratings", values, "User_ID=? AND Item_ID=?", new String[]{String.valueOf(userId), String.valueOf(itemId)});
        db.close();
        return count;
    }

    public int deleteRating(long userId, long itemId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("Ratings", "User_ID=? AND Item_ID=?", new String[]{String.valueOf(userId), String.valueOf(itemId)});
        db.close();
        return count;
    }

    public Cursor getRating(long userId, long itemId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Ratings", new String[]{"Rating"}, "User_ID=? AND Item_ID=?", new String[]{String.valueOf(userId), String.valueOf(itemId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllRatingsByUser(long userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Ratings", new String[]{"Item_ID", "Rating"}, "User_ID=?", new String[]{String.valueOf(userId)}, null, null, null);
        ArrayList<Cursor> allRatings = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allRatings.add(cursor);
            } while (cursor.moveToNext());
        }
        return allRatings;
    }

    public ArrayList<Cursor> getAllRatingsByItem(long itemId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Ratings", new String[]{"User_ID", "Rating"}, "Item_ID=?", new String[]{String.valueOf(itemId)}, null, null, null);
        ArrayList<Cursor> allRatings = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allRatings.add(cursor);
            } while (cursor.moveToNext());
        }
        return allRatings;
    }

    public ArrayList<Cursor> getAllRatings() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Ratings", new String[]{"User_ID", "Item_ID", "Rating"}, null, null, null, null, null);
        ArrayList<Cursor> allRatings = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allRatings.add(cursor);
            } while (cursor.moveToNext());
        }
        return allRatings;
    }
}
