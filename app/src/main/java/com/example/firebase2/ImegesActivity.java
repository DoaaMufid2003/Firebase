package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.firebase2.databinding.ActivityImegesBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;

import java.util.List;

public class ImegesActivity extends AppCompatActivity {
    ActivityImegesBinding binding;
    FirebaseStorage storage;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImegesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storage = FirebaseStorage.getInstance();
        firestore=FirebaseFirestore.getInstance();
        firestore.collection("Category")
                .document(getIntent().getStringExtra("categoryNametoImage"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            List<String> list = (List<String>) document.get("arrayImage");
                            AdapterImage adapter = new AdapterImage(list, getBaseContext());
                            binding.rv.setAdapter(adapter);
                            binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
//        Log.d("categoryNametoImage",getIntent().getStringExtra("categoryNametoImage").toString());
//        storage.getReference()
//                .child("images/" +getIntent().getStringExtra("categoryNametoImage").toString())
//                .listAll()
//                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
//                    @Override
//                    public void onSuccess(ListResult listResult) {
//                        Log.d(" listResult.getItems();" ,listResult.getItems().toString());
//                        AdapterImage adapterImage=new AdapterImage(listResult.getItems(),getApplicationContext());
//                        binding.rv.setAdapter(adapterImage);
//                        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
//                    }
//                });
    }
}