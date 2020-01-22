package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText txtNis, txtPass;
    Button loginBtn;
    FirebaseAuth mAuth;
    String pass,nis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtNis = findViewById(R.id.txtNisLogin);
        txtPass = findViewById(R.id.txtPassLogin);
        loginBtn = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user;
        user = mAuth.getCurrentUser();
        if(user != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nis = txtNis.getText().toString().trim();
                pass = txtPass.getText().toString().trim();

                if(nis.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Masukkan data dengan lengkap", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });
    }

    private void login() {
        mAuth.signInWithEmailAndPassword(nis,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });
    }

}
