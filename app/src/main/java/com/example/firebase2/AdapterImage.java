package com.example.firebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ItemCategoryBinding;
import com.example.firebase2.databinding.ItemImageBinding;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterImage  extends RecyclerView.Adapter<AdapterImage.MyViewHolder> {
    List<String> list;
    Context context;




    public AdapterImage(List<String> list, Context context) {
        this.list = list;
        this.context = context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageBinding binding = ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);


    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        Glide.with(context).load(list.get(pos)).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;


        public MyViewHolder(@NonNull ItemImageBinding binding) {
            super(binding.getRoot());


            imageView = binding.imageView2;


        }

    }
}
