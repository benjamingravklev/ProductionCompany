package com.dev.productioncompany.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.productioncompany.CreateNewProjectActivity;
import com.dev.productioncompany.R;
import com.dev.productioncompany.model.ProjectModel;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder>{
    List<ProjectModel> list;
    Context context;

    public ProjectsAdapter(List<ProjectModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProjectModel model = list.get(position);
        holder.tvName.setText(model.getName());
        holder.tvContract.setText(model.getContract());
        holder.tvAccessories.setText(model.getList());
        holder.tvDateTime.setText(model.getDatetime());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateNewProjectActivity.class);
                intent.putExtra("data", model);
                intent.putExtra("position", String.valueOf(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName, tvContract, tvAccessories, tvDateTime;
        AppCompatImageView ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvContract = itemView.findViewById(R.id.tvContract);
            tvAccessories = itemView.findViewById(R.id.tvAccessories);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
        }
    }
}
