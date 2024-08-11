package com.example.mealmate.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PaymentMethod {
    private DatabaseHelper dbHelper;

    public PaymentMethod(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addPaymentMethod(String name, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Description", description);
        long id = db.insert("PaymentMethods", null, values);
        db.close();
        return id;
    }

    public int updatePaymentMethod(long paymentMethodId, String name, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Description", description);
        int count = db.update("PaymentMethods", values, "PaymentMethod_ID=?", new String[]{String.valueOf(paymentMethodId)});
        db.close();
        return count;
    }

    public int deletePaymentMethod(long paymentMethodId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("PaymentMethods", "PaymentMethod_ID=?", new String[]{String.valueOf(paymentMethodId)});
        db.close();
        return count;
    }

    public Cursor getPaymentMethod(long paymentMethodId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("PaymentMethods", null, "PaymentMethod_ID=?", new String[]{String.valueOf(paymentMethodId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllPaymentMethods() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("PaymentMethods", null, null, null, null, null, null);
        ArrayList<Cursor> allPaymentMethods = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allPaymentMethods.add(cursor);
            } while (cursor.moveToNext());
        }
        return allPaymentMethods;
    }
}
