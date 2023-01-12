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

import com.example.firebase2.databinding.ActivityAddBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
ActivityAddBinding binding;
FirebaseFirestore firestore;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firestore=FirebaseFirestore.getInstance();
        Map<String, Object> docData = new HashMap<>();
        docData.put("Expressions",binding.etExpressions.getText().toString());
        firestore.collection("Category")
                .document(MainActivity.categoryNames)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        value.getData().put("ExpressionsName",binding.etExpressions.getText().toString());
                    }
                });




        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                list.add(binding.etExpressions.getText().toString());
                Intent intent=new Intent(getBaseContext(),ExpressionActivity.class);
                startActivity(intent);
            }
        });


    }
}