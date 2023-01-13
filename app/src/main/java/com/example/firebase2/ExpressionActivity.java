package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebase2.Model.Category;
import com.example.firebase2.databinding.ActivityExpressionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpressionActivity extends AppCompatActivity {
    ActivityExpressionBinding binding;
    FirebaseFirestore firestore;
    public static String name;
    public FirebaseAuth auth;
    public FirebaseUser currentUser;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpressionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        firestore = FirebaseFirestore.getInstance();
        //    Log.d("categoryname",getIntent().getStringExtra("categoryName"));
        firestore.collection("Category")
                .document(MainActivity.categoryNames)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            List<String> list = (List<String>) document.get("array");
                            ExpressionAdapter adapter = new ExpressionAdapter(list, getBaseContext(), new ListenerFavarite() {
                                @Override
                                public void favarite(String expression) {
                                    if (currentUser == null) {
                                       ArrayList <String> list1=new ArrayList<>();
                                       list1.add(expression);
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        database.getReference().child(currentUser.getUid()).setValue(list1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(ExpressionActivity.this, "Added Successful", Toast.LENGTH_SHORT).show();
                                                Log.d("expression",expression);
                                            }
                                        });
//                                        firestore.collection("Fevarite").add(currentUser.getUid()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                            @Override
//                                            public void onSuccess(DocumentReference documentReference) {
//
//                                            }
//                                        });
                                    } else {
                                        return;
                                    }
                                }

                                @Override
                                public void unfavarite() {

                                }
                            });
                            binding.rv.setAdapter(adapter);
                            binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
        binding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}
