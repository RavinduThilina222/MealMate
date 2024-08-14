package com.example.mealmate.user_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mealmate.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private ImageView profilePicture;
    private Button uploadProfilePictureButton;
    private TextInputEditText username, email, phoneNo, password, confirmPassword, address, birthday;
    private Spinner gender;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup_layout);

        // Initialize views
        profilePicture = findViewById(R.id.profile_picture);
        uploadProfilePictureButton = findViewById(R.id.upload_profile_picture_button);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phone_no);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        gender = findViewById(R.id.gender);
        address = findViewById(R.id.address);
        birthday = findViewById(R.id.birthday);
        signUpButton = findViewById(R.id.sign_up_button);

        // Set up listeners
        uploadProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle profile picture upload
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle date picker for birthday
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle sign up logic
                handleSignUp();
            }
        });
    }

    private void handleSignUp() {
        // Retrieve user input
        String usernameInput = username.getText().toString().trim();
        String emailInput = email.getText().toString().trim();
        String phoneNoInput = phoneNo.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        String confirmPasswordInput = confirmPassword.getText().toString().trim();
        String addressInput = address.getText().toString().trim();
        String birthdayInput = birthday.getText().toString().trim();
        String genderInput = gender.getSelectedItem().toString();

        // Validate input and handle registration logic
        // add user in User table
    }

    private boolean validateInput(String username, String email, String phoneNo, String password, String confirmPassword, String address, String birthday, String gender) {
        // Implement validation logic
        return true;
    }
}