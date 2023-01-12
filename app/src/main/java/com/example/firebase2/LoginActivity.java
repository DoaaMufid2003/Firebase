package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebase2.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
    public FirebaseAuth auth;
    public FirebaseUser currentUser;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();

        if(currentUser !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void register() {
        String email = binding.editTextTextEmailAddress.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // try {
                        if(task.isSuccessful()){
                            Log.d("LoginActivity",task.getResult().getUser().toString());
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            Log.d("LoginActivity",task.getException().getMessage());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // }catch (Exception ex){
                        // ex.printStackTrace();
                        // }
                        //else{
                    }
                    // }
                });
    }
    private void login() {
        String email = binding.editTextTextEmailAddress.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // try {
                        if(task.isSuccessful()){
                            Log.d("LoginActivity",task.getResult().getUser().toString());
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Log.d("LoginActivity",task.getException().getMessage());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // }catch (Exception ex){
                        // ex.printStackTrace();
                        // }
                        //else{
                    }
                    // }
                });
    }
}