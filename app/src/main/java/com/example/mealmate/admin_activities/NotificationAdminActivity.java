package com.example.mealmate.admin_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mealmate.R;

public class NotificationAdminActivity extends AppCompatActivity {

        private DrawerLayout drawerLayout;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.notification_admin_layout);

            drawerLayout = findViewById(R.id.notificationAdminDrawerLayout);

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

            findViewById(R.id.btnMenus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotificationAdminActivity.this, MenuManagementActivity.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.btnOrders).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotificationAdminActivity.this, OrderManagementActivity.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.btnCustomers).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotificationAdminActivity.this, CustomerManagementActivity.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotificationAdminActivity.this, NotificationAdminActivity.class);
                    startActivity(intent);
                }
            });

            findViewById(R.id.btnDashboard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotificationAdminActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                }
            });
        }
}
