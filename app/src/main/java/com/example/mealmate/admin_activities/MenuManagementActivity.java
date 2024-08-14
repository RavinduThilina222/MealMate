package com.example.mealmate.admin_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;

import java.util.ArrayList;
import java.util.List;

public class MenuManagementActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView foodItemsRecyclerView;
    private FoodItemAdapter foodItemAdapter;
    private List<FoodItem> foodItemList;
    private Spinner mealSpinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_management_layout);

        drawerLayout = findViewById(R.id.menuManagementDrawerLayout);
        mealSpinner = findViewById(R.id.mealSpinner);
        foodItemsRecyclerView = findViewById(R.id.foodItemsRecyclerView);

        foodItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnMenu = findViewById(R.id.BtnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        // Handle spinner selection
        mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMeal = parent.getItemAtPosition(position).toString();
                loadFoodItems(selectedMeal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Initialize with default selection
        loadFoodItems(mealSpinner.getSelectedItem().toString());

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
        // Fetch food items based on the selected meal
        // For demonstration, we'll use dummy data
        foodItemList = getDummyFoodItems(meal);
        foodItemAdapter = new FoodItemAdapter(foodItemList, this);
        foodItemsRecyclerView.setAdapter(foodItemAdapter);
    }

    private List<FoodItem> getDummyFoodItems(String meal) {
        List<FoodItem> items = new ArrayList<>();
        // Add dummy data based on the meal
        if ("Breakfast".equals(meal)) {
            items.add(new FoodItem("Pancakes", 5.99, R.drawable.pancakes));
            items.add(new FoodItem("Omelette", 6.99, R.drawable.omelette));
        } else if ("Lunch".equals(meal)) {
            items.add(new FoodItem("Burger", 8.99, R.drawable.burger));
            items.add(new FoodItem("Salad", 7.99, R.drawable.salad));
        } else if ("Dinner".equals(meal)) {
            items.add(new FoodItem("Steak", 15.99, R.drawable.steak));
            items.add(new FoodItem("Pasta", 12.99, R.drawable.pasta));
        } else if ("Soft Drinks".equals(meal)) {
            items.add(new FoodItem("Coke", 1.99, R.drawable.coke));
            items.add(new FoodItem("Pepsi", 1.99, R.drawable.pepsi));
        } else if ("Desserts".equals(meal)) {
            items.add(new FoodItem("Ice Cream", 3.99, R.drawable.ice_cream));
            items.add(new FoodItem("Cake", 4.99, R.drawable.cake));
        }
        return items;
    }
}
