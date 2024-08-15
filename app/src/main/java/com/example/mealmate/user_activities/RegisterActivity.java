package com.example.mealmate.user_activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.mealmate.R;
import com.example.mealmate.database.User;
import com.google.android.material.textfield.TextInputEditText;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    private static final int REQUEST_PERMISSION = 100;

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
                if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                } else {
                    selectImage();
                }
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

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap bitmap = (Bitmap) bundle.get("data");
                profilePicture.setImageBitmap(bitmap);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    profilePicture.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
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
        // Byte[] profilePictureInput = profilePicture.ToByteArray(); // Handle profile picture conversion

        // Validate input
        if (validateInput(usernameInput, emailInput, phoneNoInput, passwordInput, confirmPasswordInput, addressInput, birthdayInput, genderInput)) {
            // Add user to database
            addUser(usernameInput, emailInput, phoneNoInput, passwordInput, genderInput, addressInput, birthdayInput, "user", 0.0, 0.0, null);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String username, String email, String phoneNo, String password, String confirmPassword, String address, String birthday, String gender) {
        // Implement validation logic
        return true;
    }

    // Method to add user to database
    private void addUser(String username, String email, String phoneNo, String password, String gender, String address, String birthday, String userLevel, double latitude, double longitude, byte[] profilePicture) {
        User user = new User(this);
        user.addUser(username, email, phoneNo, password, gender, address, birthday, userLevel, latitude, longitude, profilePicture);
    }
}