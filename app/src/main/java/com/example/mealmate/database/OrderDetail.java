package com.example.mealmate.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class OrderDetail {
    private DatabaseHelper databaseHelper;

    public OrderDetail(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void addOrderDetail(int orderID, int itemID, int quantity) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Order_ID", orderID);
        values.put("Item_ID", itemID);
        values.put("Quantity", quantity);
        db.insert("OrderDetails", null, values);
        db.close();
    }

    public void updateOrderDetail(int orderID, int itemID, int quantity) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Quantity", quantity);
        db.update("OrderDetails", values, "Order_ID = ? AND Item_ID = ?", new String[] {String.valueOf(orderID), String.valueOf(itemID)});
        db.close();
    }

    public void deleteOrderDetail(int orderID, int itemID) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("OrderDetails", "Order_ID = ? AND Item_ID = ?", new String[] {String.valueOf(orderID), String.valueOf(itemID)});
        db.close();
    }

    public void deleteOrderDetails(int orderID) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("OrderDetails", "Order_ID = ?", new String[] {String.valueOf(orderID)});
        db.close();
    }

    public Cursor getOrderDetailByOrderID(int userID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM OrderDetails WHERE Order_ID = ?", new String[] {String.valueOf(userID)});
    }

    public ArrayList<Cursor> getOrderDetails() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM OrderDetails", null);
        ArrayList<Cursor> orderDetails = new ArrayList<>();
        while (cursor.moveToNext()) {
            orderDetails.add(cursor);
        }
        return orderDetails;
    }


}
