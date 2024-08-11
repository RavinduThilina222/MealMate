package com.example.mealmate.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Payment {
    private DatabaseHelper dbHelper;

    public Payment(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addPayment(long userId, long orderId, double amount, String paymentMethod, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_ID", userId);
        values.put("Order_ID", orderId);
        values.put("Amount", amount);
        values.put("Payment_Method", paymentMethod);
        values.put("Status", status);
        long id = db.insert("Payments", null, values);
        db.close();
        return id;
    }

    public int updatePayment(long paymentId, long userId, long orderId, double amount, String paymentMethod, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_ID", userId);
        values.put("Order_ID", orderId);
        values.put("Amount", amount);
        values.put("Payment_Method", paymentMethod);
        values.put("Status", status);
        int count = db.update("Payments", values, "Payment_ID=?", new String[]{String.valueOf(paymentId)});
        db.close();
        return count;
    }

    public int deletePayment(long paymentId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("Payments", "Payment_ID=?", new String[]{String.valueOf(paymentId)});
        db.close();
        return count;
    }

    public Cursor getPayment(long paymentId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Payments", null, "Payment_ID=?", new String[]{String.valueOf(paymentId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getPaymentByOrder(long orderId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Payments", null, "Order_ID=?", new String[]{String.valueOf(orderId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getPaymentByUser(long userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Payments", null, "User_ID=?", new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllPaymentsByMethod(String paymentMethod) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Payments", null, null, null, null, null, null);
        ArrayList<Cursor> allPayments = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allPayments.add(cursor);
            } while (cursor.moveToNext());
        }
        return allPayments;
    }


}
