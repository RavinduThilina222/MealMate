package com.example.mealmate.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Promotion {
    private DatabaseHelper dbHelper;

    public Promotion(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addPromotion(String name, String description, double discount, String startDate, String endDate, byte[] image) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Description", description);
        values.put("Discount", discount);
        values.put("Start_Date", startDate);
        values.put("End_Date", endDate);
        values.put("Image", image);
        long id = db.insert("Promotions", null, values);
        db.close();
        return id;
    }

    public int updatePromotion(long promotionId, String name, String description, double discount, String startDate, String endDate, byte[] image) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Description", description);
        values.put("Discount", discount);
        values.put("Start_Date", startDate);
        values.put("End_Date", endDate);
        values.put("Image", image);
        int count = db.update("Promotions", values, "Promotion_ID=?", new String[]{String.valueOf(promotionId)});
        db.close();
        return count;
    }

    public int deletePromotion(long promotionId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("Promotions", "Promotion_ID=?", new String[]{String.valueOf(promotionId)});
        db.close();
        return count;
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getPromotion(long promotionId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Promotions", null, "Promotion_ID=?", new String[]{String.valueOf(promotionId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllPromotions() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Promotions", null, null, null, null, null, null);
        ArrayList<Cursor> allPromotions = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allPromotions.add(cursor);
            } while (cursor.moveToNext());
        }
        return allPromotions;
    }
}
