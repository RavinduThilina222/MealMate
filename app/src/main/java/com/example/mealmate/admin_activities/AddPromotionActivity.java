package com.example.mealmate.admin_activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.R;
import com.example.mealmate.database.DatabaseHelper;

public class AddPromotionActivity extends AppCompatActivity {

    private EditText etPromotionName, etPromotionDescription, etPromoCode, etDiscountPercentage;
    private DatePicker dpStartDate, dpEndDate;
    private TimePicker tpStartTime, tpEndTime;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_promotion);

        etPromotionName = findViewById(R.id.etPromotionName);
        etPromotionDescription = findViewById(R.id.etPromotionDescription);
        etPromoCode = findViewById(R.id.etPromoCode);
        etDiscountPercentage = findViewById(R.id.etDiscountPercentage);
        dpStartDate = findViewById(R.id.dpStartDate);
        tpStartTime = findViewById(R.id.tpStartTime);
        dpEndDate = findViewById(R.id.dpEndDate);
        tpEndTime = findViewById(R.id.tpEndTime);
        Button btnAddPromotion = findViewById(R.id.btnAddPromotion);
        databaseHelper = new DatabaseHelper(this);

        btnAddPromotion.setOnClickListener(v -> handleAddPromotion());
    }

    private void handleAddPromotion() {
        String name = etPromotionName.getText().toString().trim();
        String description = etPromotionDescription.getText().toString().trim();
        String PromoCode = etPromoCode.getText().toString().trim();
        String discountPercentage = etDiscountPercentage.getText().toString().trim();
        String startDate = dpStartDate.getYear() + "-" + (dpStartDate.getMonth() + 1) + "-" + dpStartDate.getDayOfMonth();
        String startTime = tpStartTime.getHour() + ":" + tpStartTime.getMinute();
        String endDate = dpEndDate.getYear() + "-" + (dpEndDate.getMonth() + 1) + "-" + dpEndDate.getDayOfMonth();
        String endTime = tpEndTime.getHour() + ":" + tpEndTime.getMinute();

        if (validateInput(name, description)) {
            databaseHelper.addPromotion(name, description, PromoCode, Integer.parseInt(discountPercentage), startDate, startTime, endDate, endTime);
            Toast.makeText(this, "Promotion added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String name, String description) {
        return !name.isEmpty() && !description.isEmpty();
    }
}