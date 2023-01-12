package com.example.firebase2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.firebase2.databinding.ActivityImegesBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;

public class ImegesActivity extends AppCompatActivity {
    ActivityImegesBinding binding;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImegesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storage = FirebaseStorage.getInstance();
        storage.getReference()
                .child("images/" + getIntent().getStringExtra("categoryNametoImage").toString())
                .listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        Log.d(" listResult.getItems();" ,listResult.getItems().toString());
                        AdapterImage adapterImage=new AdapterImage(listResult.getItems(),getApplicationContext());
                        binding.rv.setAdapter(adapterImage);
                        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
                    }
                });
    }
}