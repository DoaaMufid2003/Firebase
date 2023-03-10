package com.example.firebase2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebase2.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    public FirebaseAuth auth;
    public FirebaseUser currentUser;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        binding.btnRegistar.setVisibility(View.GONE);

      //  binding.img.setImageURI(currentUser.getPhotoUrl());


        if(currentUser !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        binding.tvRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnLogin.setVisibility(View.GONE);
                binding.btnRegistar.setVisibility(View.VISIBLE);
            }
        });
        binding.btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();


            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });
    }

    public boolean login() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (email==null || password==null) {
            Toast.makeText(this, "please,Enter The Email and Password", Toast.LENGTH_SHORT).show();
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("LoginActivity", task.getResult().getUser().toString());
                            startActivity(new Intent(getApplicationContext(), ExpressionActivity.class));
                            finish();
                        } else {
                            Log.d("LoginActivity", task.getException().getMessage());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    // }
                });
        return true;
    }

    public void register() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // try {
                        if (task.isSuccessful()) {
                            Log.d("LoginActivity", task.getResult().getUser().toString());
                           binding.btnRegistar.setVisibility(View.GONE);
                           binding.btnLogin.setVisibility(View.VISIBLE);

                        } else {
                            Log.d("LoginActivity", task.getException().getMessage());
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