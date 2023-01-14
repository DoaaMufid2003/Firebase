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
    ListenerFavarite listenerFavarite;
    boolean isfavarite = false;


    public AdapterFevarite(List<String> expressions, Context context, ListenerFavarite listenerFavarite) {
        this.expressions = expressions;
        this.context = context;
        this.listenerFavarite=listenerFavarite;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFevariteBinding binding = ItemFevariteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.expressions.setText(expressions.get(pos).toString());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfavarite==false) {
                    listenerFavarite.unfavarite(holder.expressions.getText().toString());
                    holder.imageView.setImageResource(R.drawable.un_favorite);

                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return expressions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expressions;
        ImageView imageView;


        public MyViewHolder(@NonNull ItemFevariteBinding binding) {
            super(binding.getRoot());
            expressions = binding.tvExpressions;
            imageView = binding.img;


        }

    }
}
