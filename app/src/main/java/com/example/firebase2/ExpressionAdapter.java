package com.example.firebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ItemCategoryBinding;
import com.example.firebase2.databinding.ItemExpressionBinding;

import java.util.List;

public class ExpressionAdapter  extends RecyclerView.Adapter<ExpressionAdapter.MyViewHolder> {
    List<String> expressions;
    Context context;



    public ExpressionAdapter(List<String> expressions, Context context) {
        this.expressions = expressions;
        this.context = context;

    }

    @NonNull
    @Override
    public ExpressionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExpressionBinding binding=ItemExpressionBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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

        public MyViewHolder(@NonNull ItemExpressionBinding binding) {
            super(binding.getRoot());
            expressions=binding.tvExpressions;
        }

    }
}
