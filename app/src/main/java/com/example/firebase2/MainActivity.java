package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseFirestore firestore;
    public static String categoryNames;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storage=FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Category> categories = task.getResult().toObjects(Category.class);
                    AdapterCategory adapterCategory = new AdapterCategory(categories, getApplicationContext(), new Listener() {
                        @Override
                        public void onClick(String categoryName) {
                            Intent intent = new Intent(getApplicationContext(), ExpressionActivity.class);
                            intent.putExtra("categoryName", categoryName);
                            categoryNames = categoryName;
                            startActivity(intent);
                        }

                        @Override
                        public void onClickImage(String categoryName) {
                            Intent intent=new Intent(getApplicationContext(),ImegesActivity.class);
                            intent.putExtra("categoryNametoImage",categoryName);
                            startActivity(intent);
                        }
                    });
                    binding.rv.setAdapter(adapterCategory);
                    binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                }
            }
        });


    }


}