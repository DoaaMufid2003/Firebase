package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ActivityExpressionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Collections;
import java.util.List;

public class ExpressionActivity extends AppCompatActivity {
    ActivityExpressionBinding binding;
    FirebaseFirestore firestore;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpressionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        name=getIntent().getStringExtra("categoryName");
        firestore = FirebaseFirestore.getInstance();
    //    Log.d("categoryname",getIntent().getStringExtra("categoryName"));
        firestore.collection("Category")
                .document(getIntent().getStringExtra("categoryName"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            List<String> list = (List<String>) document.get("array");
                            ExpressionAdapter adapter = new ExpressionAdapter(list, getBaseContext());
                            binding.rv.setAdapter(adapter);
                            binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AddActivity.class));
            }
        });
    }
}
