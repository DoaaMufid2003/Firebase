package com.example.firebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ItemCategoryBinding;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.MyViewHolder> {
    List<Category> categories;
    Context context;
    Listener listener;



    public AdapterCategory(List<Category> categories, Context context, Listener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);


    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.category_name.setText(categories.get(pos).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.category_name.getText().toString());
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickImage(holder.category_name.getText().toString());
            }
        });

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category_name;
        ImageView imageView;


        public MyViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());

            category_name = binding.tvCategory;
            imageView = binding.imageView;


        }

    }
}
