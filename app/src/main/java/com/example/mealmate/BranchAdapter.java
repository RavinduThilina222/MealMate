package com.example.mealmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmate.R;
import com.example.mealmate.database.Branch;

import java.util.List;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    private List<Branch> branchList;

    public BranchAdapter(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_recycler_view, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        Branch branch = branchList.get(position);
        holder.tvBranchName.setText(branch.getName());
        holder.tvBranchAddress.setText(branch.getAddress());
    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public static class BranchViewHolder extends RecyclerView.ViewHolder {
        TextView tvBranchName, tvBranchAddress;

        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBranchName = itemView.findViewById(R.id.branch_name);
            tvBranchAddress = itemView.findViewById(R.id.branch_address);
        }
    }
}
