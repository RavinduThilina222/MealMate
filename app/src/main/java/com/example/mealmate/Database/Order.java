package com.example.mealmate.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Recycle")
public class Order {
    private DatabaseHelper dbHelper;

    public Order(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addOrder(long userId, long itemId, int quantity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO Orders (User_ID, Item_ID, Quantity) VALUES (" + userId + ", " + itemId + ", " + quantity + ");");
        db.close();
    }

    public void deleteOrder(long userId, long itemId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Orders WHERE User_ID=" + userId + " AND Item_ID=" + itemId + ";");
        db.close();
    }

    public  void updateOrder(long userId, long itemId, int quantity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Orders SET Quantity=" + quantity + " WHERE User_ID=" + userId + " AND Item_ID=" + itemId + ";");
        db.close();
    }

    public ArrayList<Cursor> getOrdersByUserID(long userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Orders WHERE User_ID=" + userId + ";", null);
        ArrayList<Cursor> orders = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                orders.add(cursor);
            } while (cursor.moveToNext());
        }
        return orders;
    }
}
