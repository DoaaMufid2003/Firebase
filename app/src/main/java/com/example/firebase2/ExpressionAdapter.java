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
import com.example.firebase2.databinding.ItemExpressionBinding;

import java.util.List;

public class ExpressionAdapter  extends RecyclerView.Adapter<ExpressionAdapter.MyViewHolder> {
    List<String> expressions;
    Context context;
    boolean isfavarite;
    ListenerFavarite listenerFavarite;



    public ExpressionAdapter(List<String> expressions, Context context,ListenerFavarite listenerFavarite) {
        this.expressions = expressions;
        this.context = context;
       this.listenerFavarite=listenerFavarite;

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
        holder.img_favarite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfavarite){
                    holder.img_favarite.setImageResource(R.drawable.un_favorite);
                 listenerFavarite.unfavarite();

                }else{
                    holder.img_favarite.setImageResource(R.drawable.ic_baseline_favorite_24);
                   listenerFavarite.favarite(holder.expressions.getText().toString());
                }
                isfavarite=!isfavarite;
            }
        });

    }


    @Override
    public int getItemCount() {
        return expressions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView expressions;
        ImageView img_favarite;

        public MyViewHolder(@NonNull ItemExpressionBinding binding) {
            super(binding.getRoot());
            expressions=binding.tvExpressions;
            img_favarite=binding.imgFavarite;

        }

    }
}
