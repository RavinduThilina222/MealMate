// FoodItemAdapter.java
package com.example.mealmate.user_activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.database.DatabaseHelper;
import com.example.mealmate.admin_activities.FoodItem;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_card_user, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.nameTextView.setText(foodItem.getName());
        holder.descriptionTextView.setText(foodItem.getDescription());
        holder.priceTextView.setText(String.valueOf(foodItem.getPrice()));

        byte[] imageBytes = foodItem.getImage();
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imageView.setImageBitmap(bitmap);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_profile_placeholder);
        }

        holder.btnAddToOrder.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            int itemCount = Integer.parseInt(holder.tvItemCount.getText().toString());
            boolean isAdded = databaseHelper.addOrder(foodItem, itemCount);
            if (isAdded) {
                Toast.makeText(context, "Added to Order", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to add to Order", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnPlus.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.tvItemCount.getText().toString());
            holder.tvItemCount.setText(String.valueOf(++count));
        });

        holder.btnMinus.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.tvItemCount.getText().toString());
            if (count > 1) {
                holder.tvItemCount.setText(String.valueOf(--count));
            }
        });

        holder.btnFavorite.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            boolean isFavorite = databaseHelper.addFavoriteItem(foodItem.getId());
            if (isFavorite) {
                Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to add to Favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        ImageView imageView;
        ImageButton btnMinus;
        TextView tvItemCount;
        ImageButton btnPlus;
        Button btnAddToOrder;
        ImageButton btnFavorite;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.foodName);
            descriptionTextView = itemView.findViewById(R.id.foodDescription);
            priceTextView = itemView.findViewById(R.id.foodPrice);
            imageView = itemView.findViewById(R.id.foodImage);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            tvItemCount = itemView.findViewById(R.id.tvItemCount);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnAddToOrder = itemView.findViewById(R.id.btnAddToOrder);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }
}