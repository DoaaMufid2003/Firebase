package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ActivityFevariteBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FevariteActivity extends AppCompatActivity {
    ActivityFevariteBinding binding;
    public FirebaseAuth auth;
    public FirebaseUser currentUser;
    FirebaseDatabase databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFevariteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();


        databaseReference.getReference(currentUser.getUid());

        //listen to the events come
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
//                     Category category = dataSnapshot.getValue(Category.class);
//                     ArrayList<String> list=new ArrayList<>() ;
//              //      list.add(category)
//                }
//           //     adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//              //  Toast.makeText(getActivity(),"no data",Toast.LENGTH_LONG).show();
//                Log.e("error","error");
//            }
//        });

    }
}