package com.example.mealmate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MealMate.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users (" +
                "User_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT UNIQUE NOT NULL," +
                "Email TEXT UNIQUE NOT NULL," +
                "Phone_No TEXT UNIQUE NOT NULL," +
                "Password TEXT NOT NULL," +
                "Gender TEXT," +
                "Address TEXT NOT NULL," +
                "Birthday DATE," +
                "User_Level TEXT NOT NULL," +
                "Latitude REAL NOT NULL," +
                "Longitude REAL NOT NULL," +
                "Profile_Picture BLOB," +
                "Created_AT DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");");

        db.execSQL("CREATE TABLE Administrators (" +
                "Admin_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT UNIQUE NOT NULL," +
                "Email TEXT UNIQUE NOT NULL," +
                "Role TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "Created_AT DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");");

        db.execSQL("CREATE TABLE MenuItems (" +
                "Item_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Category TEXT NOT NULL," +
                "Name TEXT NOT NULL," +
                "Description TEXT," +
                "Price REAL NOT NULL," +
                "Availability BOOLEAN NOT NULL DEFAULT 1," +
                "Image BLOB," +
                "Created_AT DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "Updated_AT DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");");

        db.execSQL("CREATE TABLE FavoriteItems (" +
                "User_ID INTEGER NOT NULL," +
                "Item_ID INTEGER NOT NULL," +
                "PRIMARY KEY (User_ID, Item_ID)," +
                "FOREIGN KEY (User_ID) REFERENCES Users(User_ID)," +
                "FOREIGN KEY (Item_ID) REFERENCES MenuItems(Item_ID)" +
                ");");

        db.execSQL("CREATE TABLE Orders (" +
                "Order_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "User_ID INTEGER NOT NULL," +
                "Favorite_Order TEXT NOT NULL DEFAULT 'No'," +
                "Net_Amount REAL NOT NULL," +
                "Discount REAL NOT NULL DEFAULT '0'," +
                "Delivery_Charge REAL NOT NULL DEFAULT '0'," +
                "Total_Price REAL NOT NULL," +
                "Order_Date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "Latitude REAL NOT NULL," +
                "Longitude REAL NOT NULL," +
                "Status TEXT NOT NULL DEFAULT 'Pending'," +
                "FOREIGN KEY (User_ID) REFERENCES Users(User_ID)" +
                ");");

        db.execSQL("CREATE TABLE OrderDetails (" +
                "Order_Detail_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Order_ID INTEGER NOT NULL," +
                "Item_ID INTEGER NOT NULL," +
                "Quantity INTEGER NOT NULL," +
                "Customizations TEXT," +
                "FOREIGN KEY (Order_ID) REFERENCES Orders(Order_ID)," +
                "FOREIGN KEY (Item_ID) REFERENCES MenuItems(Item_ID)" +
                ");");

        db.execSQL("CREATE TABLE Promotions (" +
                "Promo_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Code TEXT UNIQUE NOT NULL," +
                "Discount_Percentage INTEGER NOT NULL," +
                "Valid_From DATETIME NOT NULL," +
                "Valid_Until DATETIME NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE Ratings (" +
                "Rating_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "User_ID INTEGER NOT NULL," +
                "Branch_ID INTEGER NOT NULL," +
                "Item_ID INTEGER NOT NULL," +
                "Rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5)," +
                "Comment TEXT," +
                "Created_AT DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (User_ID) REFERENCES Users(User_ID)," +
                "FOREIGN KEY (Branch_ID) REFERENCES Branches(Branch_ID)," +
                "FOREIGN KEY (Item_ID) REFERENCES MenuItems(Item_ID)" +
                ");");

        db.execSQL("CREATE TABLE Branches (" +
                "Branch_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Address TEXT NOT NULL," +
                "Latitude REAL NOT NULL," +
                "Longitude REAL NOT NULL," +
                "Created_AT DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "Updated_AT DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");");

        db.execSQL("CREATE TABLE PaymentMethods (" +
                "Method_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Method_Name TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE Payments (" +
                "Payment_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Order_ID INTEGER NOT NULL," +
                "Method_ID INTEGER NOT NULL," +
                "Amount REAL NOT NULL," +
                "Payment_Date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (Order_ID) REFERENCES Orders(Order_ID)," +
                "FOREIGN KEY (Method_ID) REFERENCES PaymentMethods(Method_ID)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Administrators");
        db.execSQL("DROP TABLE IF EXISTS MenuItems");
        db.execSQL("DROP TABLE IF EXISTS FavoriteItems");
        db.execSQL("DROP TABLE IF EXISTS Orders");
        db.execSQL("DROP TABLE IF EXISTS OrderDetails");
        db.execSQL("DROP TABLE IF EXISTS Promotions");
        db.execSQL("DROP TABLE IF EXISTS Ratings");
        db.execSQL("DROP TABLE IF EXISTS Branches");
        db.execSQL("DROP TABLE IF EXISTS PaymentMethods");
        db.execSQL("DROP TABLE IF EXISTS Payments");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}