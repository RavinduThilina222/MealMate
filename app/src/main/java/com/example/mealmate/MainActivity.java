// MainActivity.java
package com.example.mealmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.database.Administrators;
import com.example.mealmate.database.User;
import com.example.mealmate.user_activities.HomeActivity;
import com.example.mealmate.admin_activities.AdminDashboardActivity;
import com.example.mealmate.user_activities.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open user registration activity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogin() {
        String usernameInput = etUsername.getText().toString().trim();
        String passwordInput = etPassword.getText().toString().trim();

        if (validateInput(usernameInput, passwordInput)) {
            User user = new User(this);
            Administrators admin = new Administrators(this);

            if (user.checkUserCredentials(usernameInput, passwordInput)) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else if (admin.checkAdminCredentials(usernameInput, passwordInput)) {
                Intent intent = new Intent(MainActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
}