package com.example.mealmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
    private List<FoodItem> foodItemList;
    private Context context;

    public FoodItemAdapter(List<FoodItem> foodItemList, Context context) {
        this.foodItemList = foodItemList;
        this.context = context;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_card_admin, parent, false);        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.nameTextView.setText(foodItem.getName());
        holder.priceTextView.setText(String.valueOf(foodItem.getPrice()));
        holder.imageView.setImageResource(foodItem.getImageResId()); // Set image using resource ID
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageView imageView;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.foodName);
            priceTextView = itemView.findViewById(R.id.foodPrice);
            imageView = itemView.findViewById(R.id.foodImage);
        }
    }
}