package com.example.mealmate.admin_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mealmate.MainActivity;
import com.example.mealmate.R;
import com.example.mealmate.database.Branch;
import com.example.mealmate.database.DatabaseHelper;

public class BranchesActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branches_layout);

        drawerLayout = findViewById(R.id.branchAdminLayout);

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

        // Add Branch Button Click Listener
        Button btnAddBranch = findViewById(R.id.btnAddBranch);
        btnAddBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etBranchName = findViewById(R.id.etBranchName);
                EditText etBranchAddress = findViewById(R.id.etBranchAddress);
                EditText etBranchLatitude = findViewById(R.id.etBranchLatitude);
                EditText etBranchLongitude = findViewById(R.id.etBranchLongitude);

                String branchName = etBranchName.getText().toString().trim();
                String branchAddress = etBranchAddress.getText().toString().trim();
                String branchLatitudeStr = etBranchLatitude.getText().toString().trim();
                String branchLongitudeStr = etBranchLongitude.getText().toString().trim();

                if (branchName.isEmpty() || branchAddress.isEmpty() || branchLatitudeStr.isEmpty() || branchLongitudeStr.isEmpty()) {
                    Toast.makeText(BranchesActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double branchLatitude;
                double branchLongitude;
                try {
                    branchLatitude = Double.parseDouble(branchLatitudeStr);
                    branchLongitude = Double.parseDouble(branchLongitudeStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(BranchesActivity.this, "Invalid latitude or longitude", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(BranchesActivity.this);
                Branch branch = new Branch(databaseHelper);
                try {
                    branch.addBranch(branchName, branchAddress, branchLatitude, branchLongitude);
                    Toast.makeText(BranchesActivity.this, "Branch added successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(BranchesActivity.this, "Error adding branch", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle other button clicks in the navigation drawer
        findViewById(R.id.btnMenus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Menu Management button click
                Intent intent = new Intent(BranchesActivity.this, MenuManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Order Management button click
                Intent intent = new Intent(BranchesActivity.this, OrderManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnCustomers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Customer Management button click
                Intent intent = new Intent(BranchesActivity.this, CustomerManagementActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Notification Management button click
                Intent intent = new Intent(BranchesActivity.this, NotificationAdminActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBranches).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Branch Management button click
                Intent intent = new Intent(BranchesActivity.this, BranchesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Analytics button click
                Intent intent = new Intent(BranchesActivity.this, AnalyticsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnPromotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Promotion button click
                Intent intent = new Intent(BranchesActivity.this, PromotionActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Dashboard button click
                Intent intent = new Intent(BranchesActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });

        //logout button
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Logout button click
                Intent intent = new Intent(BranchesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}