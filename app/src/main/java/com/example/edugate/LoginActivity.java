package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtNis, txtPass;
    Button loginBtn;
    TextView tvRegister;
    FirebaseAuth mAuth;
    String pass, nis;
    ProgressBar progressBar;
    View bgViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtNis = findViewById(R.id.txtNisLogin);
        txtPass = findViewById(R.id.txtPassLogin);
        loginBtn = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar_login);
        bgViewBtn = findViewById(R.id.bg_btn_login);
        tvRegister = findViewById(R.id.tv_to_register);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            Intent moveToHome = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(moveToHome);
            finish();
        }
    }

    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.INVISIBLE);
        bgViewBtn.setVisibility(View.INVISIBLE);
        mAuth.signInWithEmailAndPassword(nis, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent moveToHome = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(moveToHome);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Password dan email tidak cocok", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                    bgViewBtn.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                nis = txtNis.getText().toString().trim();
                pass = txtPass.getText().toString().trim();

                if (nis.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Masukkan data dengan lengkap", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
                break;
            case R.id.tv_to_register:
                Intent moveToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(moveToRegister);
        }
    }
}
