package com.example.mealmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Administrators {
    private DatabaseHelper dbHelper;

    public Administrators(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addAdministrator(String username, String email, String role, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Email", email);
        values.put("Role", role);
        values.put("Password", password);
        long id = db.insert("Administrators", null, values);
        db.close();
        return id;
    }

    public int updateAdministrator(long adminId, String username, String email, String role, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Email", email);
        values.put("Role", role);
        values.put("Password", password);
        int rowsAffected = db.update("Administrators", values, "Admin_ID=?", new String[]{String.valueOf(adminId)});
        db.close();
        return rowsAffected;
    }

    public int deleteAdministrator(long adminId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("Administrators", "Admin_ID=?", new String[]{String.valueOf(adminId)});
        db.close();
        return rowsAffected;
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Administrators", null, null);
        db.close();
    }

    public Cursor getAdministrator(long adminId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Administrators", null, "Admin_ID=?", new String[]{String.valueOf(adminId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Cursor> getAllAdministrators() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Administrators", null, null, null, null, null, null);
        ArrayList<Cursor> allAdministrators = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                allAdministrators.add(cursor);
            } while (cursor.moveToNext());
        }
        return allAdministrators;
    }

}
