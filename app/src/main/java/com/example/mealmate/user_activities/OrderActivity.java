// OrderActivity.java
package com.example.mealmate.user_activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.admin_activities.FoodItem;
import com.example.mealmate.database.DatabaseHelper;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodItemAdapter adapter;
    private List<FoodItem> foodItemList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menus_layout);

        recyclerView = findViewById(R.id.foodItemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        foodItemList = databaseHelper.getAllFoodItems(); // Assuming a method to get all food items

        if (foodItemList != null && !foodItemList.isEmpty()) {
            adapter = new FoodItemAdapter(foodItemList, this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No food items available", Toast.LENGTH_SHORT).show();
        }


    }
}