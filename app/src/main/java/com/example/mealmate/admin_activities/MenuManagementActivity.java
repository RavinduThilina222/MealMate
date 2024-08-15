package com.example.mealmate.admin_activities;

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

    // MenuManagementActivity.java
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

    // MenuManagementActivity.java
    private void loadFoodItems(String meal) {
        List<FoodItem> foodItemList = getFoodItemsByCategory(meal);
        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(foodItemList, this);
        foodItemsRecyclerView.setAdapter(foodItemAdapter);
    }

    // MenuManagementActivity.java
    private List<FoodItem> getFoodItemsByCategory(String meal) {
        List<FoodItem> items = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MenuItems",
                new String[]{"Name", "Price", "Image"},
                "Category=?",
                new String[]{meal},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("Price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("Image"));
                items.add(new FoodItem(name, price, image));
            } while (cursor.moveToNext());
        } else {
            // Log if no items are found
            Log.d("MenuManagementActivity", "No items found for category: " + meal);
        }

        cursor.close();
        db.close();
        return items;
    }
}