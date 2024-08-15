// UpdateFoodItemActivity.java
package com.example.mealmate.admin_activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.R;
import com.example.mealmate.database.DatabaseHelper;

public class UpdateFoodItemActivity extends AppCompatActivity {
    private EditText etName, etDescription, etPrice;
    private ImageView ivFoodImage;
    private Button btnUpdate;
    private FoodItem foodItem;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_food_item);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        ivFoodImage = findViewById(R.id.ivFoodImage);
        btnUpdate = findViewById(R.id.btnUpdate);

        databaseHelper = new DatabaseHelper(this);

        foodItem = (FoodItem) getIntent().getSerializableExtra("foodItem");

        if (foodItem != null) {
            etName.setText(foodItem.getName());
            etDescription.setText(foodItem.getDescription());
            etPrice.setText(String.valueOf(foodItem.getPrice()));
            byte[] imageBytes = foodItem.getImage();
            if (imageBytes != null && imageBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                ivFoodImage.setImageBitmap(bitmap);
            }
        }

        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);

            foodItem.setName(name);
            foodItem.setDescription(description);
            foodItem.setPrice(price);

            boolean isUpdated = databaseHelper.updateFoodItem(foodItem);

            if (isUpdated) {
                Toast.makeText(this, "Food item updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update food item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}