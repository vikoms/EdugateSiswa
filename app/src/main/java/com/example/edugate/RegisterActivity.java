package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.edugate.Models.Kelas;
import com.example.edugate.Models.Murid;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEmail;
    private EditText txtPass;
    private EditText txtNis;
    private EditText txtName;
    private EditText txtTelp;
    ProgressBar progressBar;
    View bgViewBtn;
    Button btnRegister;
    Spinner spinnerKelas;
    FirebaseDatabase database;
    DatabaseReference refKelas;
    DatabaseReference refMurid;
    ArrayAdapter<Kelas> adapter;
    ArrayList<Kelas> listKelas;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponent();


        database = FirebaseDatabase.getInstance();
        refKelas = database.getReference("Kelas");
        refMurid= database.getReference("Users").child("Murid");
        mAuth = FirebaseAuth.getInstance();
        listKelas = new ArrayList<>();

        btnRegister.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        refKelas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item:dataSnapshot.getChildren()) {
                    Kelas kelas = new Kelas(item.getKey(),item.getValue().toString());
                    listKelas.add(kelas);
                }
                loadDataKelas();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initComponent() {
        txtEmail = findViewById(R.id.tv_email_register);
        txtPass = findViewById(R.id.tv_password_register);
        txtNis = findViewById(R.id.tv_nis_register);
        txtTelp = findViewById(R.id.tv_telp_register);
        txtName = findViewById(R.id.tv_name_register);
        progressBar = findViewById(R.id.progressBar_register);
        bgViewBtn = findViewById(R.id.bg_btn_register);
        btnRegister = findViewById(R.id.btn_register);
        spinnerKelas = findViewById(R.id.spinner_kelas);


        progressBar.setVisibility(View.INVISIBLE);
    }

    private void loadDataKelas() {
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listKelas);
        adapter.notifyDataSetChanged();
        spinnerKelas.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
            String email = txtEmail.getText().toString().trim();
            String name = txtName.getText().toString().trim();
            String telp = txtTelp.getText().toString().trim();
            String nis = txtNis.getText().toString().trim();
            String pass = txtPass.getText().toString().trim();

            if(email.isEmpty() || name.isEmpty() || telp.isEmpty() || nis.isEmpty()) {
                Kelas kelas = (Kelas) spinnerKelas.getSelectedItem();

                Toast.makeText(this, "Isi data dengan lengkap", Toast.LENGTH_SHORT).show();
            } else {
                register(email,name,telp,nis,pass);
            }

        }
    }

    private void register(final String email, final String name, final String telp, final String nis,final String pass) {

        progressBar.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.INVISIBLE);
        bgViewBtn.setVisibility(View.INVISIBLE);

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    String uid =mAuth.getCurrentUser().getUid();
                    Kelas kelas = (Kelas) spinnerKelas.getSelectedItem();
                    String kelasTugas = kelas.getKeyKelas();
                    Murid murid = new Murid(email,kelasTugas,name,nis,telp);
                    refMurid.child(uid).setValue(murid);
//
//                    FirebaseUser currentUser = mAuth.getCurrentUser();
//                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                            .setDisplayName(name).build();
//                    currentUser.updateProfile(profileUpdates);

                    Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();

                    Intent moveToHome = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(moveToHome);
                    finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    btnRegister.setVisibility(View.VISIBLE);
                    bgViewBtn.setVisibility(View.VISIBLE);

                    Toast.makeText(RegisterActivity.this, String.valueOf(task.getException()), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
