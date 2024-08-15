// MenuManagementActivity.java
package com.example.mealmate.admin_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MenuManagementActivity extends AppCompatActivity {

    private RecyclerView foodItemsRecyclerView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_management_layout);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        DrawerLayout drawerLayout = findViewById(R.id.menuManagementDrawerLayout);
        Spinner mealSpinner = findViewById(R.id.mealSpinner);
        foodItemsRecyclerView = findViewById(R.id.foodItemsRecyclerView);
        Button addFoodItemButton = findViewById(R.id.addFoodItemButton);

        // Attach LayoutManager to RecyclerView
        foodItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Handle spinner selection
        mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMeal = parent.getItemAtPosition(position).toString();
                loadFoodItems(selectedMeal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Toast Message to select a meal in the spinner
            }
        });

        // Initialize with default selection
        loadFoodItems(mealSpinner.getSelectedItem().toString());

        // Handle Add Food Item button click
        addFoodItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuManagementActivity.this, AddFoodItemActivity.class);
                startActivity(intent);
            }
        });

        // Handle other button clicks in the navigation drawer
        findViewById(R.id.btnMenus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Menu Management button click
                Intent intent = new Intent(MenuManagementActivity.this, MenuManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Order Management button click
                Intent intent = new Intent(MenuManagementActivity.this, OrderManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnCustomers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Customer Management button click
                Intent intent = new Intent(MenuManagementActivity.this, CustomerManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Notification Management button click
                Intent intent = new Intent(MenuManagementActivity.this, NotificationAdminActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBranches).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Branch Management button click
                Intent intent = new Intent(MenuManagementActivity.this, BranchesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Analytics button click
                Intent intent = new Intent(MenuManagementActivity.this, AnalyticsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnPromotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Promotion button click
                Intent intent = new Intent(MenuManagementActivity.this, PromotionActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Dashboard button click (go back to AdminDashboardActivity)
                Intent intent = new Intent(MenuManagementActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadFoodItems(String meal) {
        List<FoodItem> foodItemList = getFoodItemsByCategoryAdmin(meal);
        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(foodItemList, this);
        foodItemsRecyclerView.setAdapter(foodItemAdapter);
    }

    private List<FoodItem> getFoodItemsByCategoryUser(String meal) {
        List<FoodItem> items = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MenuItems",
                new String[]{"Item_ID", "Name", "Description", "Price", "Availability", "Image"},
                "Category=?",
                new String[]{meal},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("Item_ID"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("Description"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("Price"));
                boolean availability = cursor.getInt(cursor.getColumnIndexOrThrow("Availability")) == 1;
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("Image"));
                items.add(new FoodItem(id, name, description, description, price, availability, image));
            } while (cursor.moveToNext());
        } else {
            Log.d("MenuManagementActivity", "No items found for category: " + meal);
        }

        cursor.close();
        db.close();
        return items;
    }

    @SuppressLint("Range")
    private List<FoodItem> getFoodItemsByCategoryAdmin(String meal) {
        List<FoodItem> items = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MenuItems",
                new String[]{"Item_ID", "Category", "Name", "Description", "Price", "Availability", "Image"},
                "Category=?",
                new String[]{meal},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Item_ID"));
                String category = cursor.getString(cursor.getColumnIndex("Category"));
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String description = cursor.getString(cursor.getColumnIndex("Description"));
                double price = cursor.getDouble(cursor.getColumnIndex("Price"));
                boolean availability = cursor.getInt(cursor.getColumnIndex("Availability")) == 1;
                byte[] image = cursor.getBlob(cursor.getColumnIndex("Image"));
                items.add(new FoodItem(id, category, name, description, price, availability, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }
}