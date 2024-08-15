// FoodItemAdapter.java
package com.example.mealmate.admin_activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.database.DatabaseHelper;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
    private List<FoodItem> foodItemList;
    private Context context;

    public FoodItemAdapter(List<FoodItem> foodItemList, Context context) {
        this.foodItemList = foodItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_card_admin, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.nameTextView.setText(foodItem.getName());
        holder.priceTextView.setText(String.valueOf(foodItem.getPrice()));

        byte[] imageBytes = foodItem.getImage();
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imageView.setImageBitmap(bitmap);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_profile_placeholder);
        }

        holder.updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateFoodItemActivity.class);
            intent.putExtra("foodItem", foodItem);
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Food Item")
                    .setMessage("Are you sure you want to delete this food item?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                        databaseHelper.deleteFoodItem(foodItem.getId());
                        foodItemList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, foodItemList.size());
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageView imageView;
        ImageButton updateButton;
        ImageButton deleteButton;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.foodName);
            priceTextView = itemView.findViewById(R.id.foodPrice);
            imageView = itemView.findViewById(R.id.foodImage);
            updateButton = itemView.findViewById(R.id.btnUpdate);
            deleteButton = itemView.findViewById(R.id.btnDelete);
        }
    }
}