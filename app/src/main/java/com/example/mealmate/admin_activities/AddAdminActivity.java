package com.example.mealmate.admin_activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.R;
import com.example.mealmate.database.Administrators;
import com.example.mealmate.database.User;

public class AddAdminActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etRole, etPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_admin);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        Spinner spRole = findViewById(R.id.spRole);
        etPassword = findViewById(R.id.etPassword);
        Button btnAddAdmin = findViewById(R.id.btnAddAdmin);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.admin_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRole.setAdapter(adapter);

        btnAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddAdmin();
            }
        });
    }

    private void handleAddAdmin() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String role = etRole.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (validateInput(username, email, role, password)) {
            Administrators admin = new Administrators(this);
            admin.addAdministrator(username, email, role, password);
            Toast.makeText(this, "Admin added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String username, String email, String role, String password) {
        return !username.isEmpty() && !email.isEmpty() && !role.isEmpty() && !password.isEmpty();
    }
}
