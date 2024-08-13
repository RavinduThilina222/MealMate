package com.example.mealmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FavoriteItem {
    private DatabaseHelper dbHelper;

    public FavoriteItem(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addFavoriteItem(long userId, long itemId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_ID", userId);
        values.put("Item_ID", itemId);
        long id = db.insert("FavoriteItems", null, values);
        db.close();
        return id;

    }

    public int deleteFavoriteItem(long userId, long itemId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("FavoriteItems", "User_ID=? AND Item_ID=?", new String[]{String.valueOf(userId), String.valueOf(itemId)});
        db.close();
        return rowsAffected;
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("FavoriteItems", null, null);
        db.close();
    }

    public void updateFavoriteItem(long userId, long itemId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_ID", userId);
        values.put("Item_ID", itemId);
        db.update("FavoriteItems", values, "User_ID=? AND Item_ID=?", new String[]{String.valueOf(userId), String.valueOf(itemId)});
        db.close();
    }

    public ArrayList<Cursor> getAllFavoriteItems() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("FavoriteItems", null, null, null, null, null, null);
        ArrayList<Cursor> allFavoriteItems = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allFavoriteItems.add(cursor);
            } while (cursor.moveToNext());
        }
        return allFavoriteItems;
    }

}
