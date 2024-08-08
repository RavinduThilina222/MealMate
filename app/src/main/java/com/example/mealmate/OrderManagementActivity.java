package com.example.mealmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class OrderManagementActivity extends AppCompatActivity {
    // Order Management Activity
    private DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_management_layout);

        drawerLayout = findViewById(R.id.orderManagementDrawerLayout);

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

        // Handle other button clicks in the navigation drawer
        findViewById(R.id.btnMenus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Menu Management button click
                Intent intent = new Intent(OrderManagementActivity.this, MenuManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Order Management button click
                Intent intent = new Intent(OrderManagementActivity.this, OrderManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnCustomers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Customer Management button click
                Intent intent = new Intent(OrderManagementActivity.this, CustomerManagementActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Notification Management button click
                Intent intent = new Intent(OrderManagementActivity.this, NotificationAdminActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBranches).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Branch Management button click
                Intent intent = new Intent(OrderManagementActivity.this, BranchesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Analytics button click
                Intent intent = new Intent(OrderManagementActivity.this, AnalyticsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnPromotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Promotion button click
                Intent intent = new Intent(OrderManagementActivity.this, PromotionActivity.class);
                startActivity(intent);
            }
        });

        // Handle Dashboard button click
        findViewById(R.id.btnDashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderManagementActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
