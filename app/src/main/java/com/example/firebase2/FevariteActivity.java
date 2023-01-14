package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firebase2.databinding.ActivityFevariteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FevariteActivity extends AppCompatActivity {
    ActivityFevariteBinding binding;
    public FirebaseAuth auth;
    public FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    AdapterFevarite adapterFevarite;
    int i=0;
    List<String> expressions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFevariteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        expressions = new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference(currentUser.getUid());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        binding.rv.setLayoutManager(layoutManager);

        //listen to the events come
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.e("dataSnapshot", String.valueOf(dataSnapshot.getValue()));
                    String value = dataSnapshot.getValue(String.class);
                    expressions.add(value);
                }
                adapterFevarite.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error", error.getMessage());
            }
        });

        adapterFevarite = new AdapterFevarite(expressions, getBaseContext(), new ListenerFavarite() {
            @Override
            public void favarite(String expression) {
                Log.d("expression",expression);
            }
            @Override
            public void unfavarite(String expression) {

                database.getReference().child(currentUser.getUid())
                        .equalTo(expression)
                       .addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                   Log.e("dataSnapshot", String.valueOf(dataSnapshot.getValue()));
                                   String value = dataSnapshot.getValue(String.class);
                                   expressions.remove(value);
                               }
                               adapterFevarite.notifyDataSetChanged();
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });


                      //  .removeValue(new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        Toast.makeText(FevariteActivity.this, "Un Fevarite Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
        binding.rv.setAdapter(adapterFevarite);

    }
}