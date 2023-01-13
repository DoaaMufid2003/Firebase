package com.example.firebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase2.databinding.ItemExpressionBinding;
import com.example.firebase2.databinding.ItemFevariteBinding;

import java.util.List;

public class AdapterFevarite extends RecyclerView.Adapter<AdapterFevarite.MyViewHolder> {
    List<String> expressions;
    Context context;




    public AdapterFevarite(List<String> expressions, Context context) {
        this.expressions = expressions;
        this.context = context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFevariteBinding binding=ItemFevariteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(binding);

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.expressions.setText(expressions.get(pos).toString());

    }


    @Override
    public int getItemCount() {
        return expressions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView expressions;


            public MyViewHolder(@NonNull ItemFevariteBinding binding) {
            super(binding.getRoot());
            expressions=binding.tvExpressions;


        }

    }
}
