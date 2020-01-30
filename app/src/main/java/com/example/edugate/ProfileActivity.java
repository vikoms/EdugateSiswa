package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edugate.Models.Murid;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    DatabaseReference refMurid;
    DatabaseReference refKelas;
    ProgressBar progressBar;
    Button btnEdit;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView txtNama;
    TextView txtEmail;
    TextView txtNis;
    TextView txtNis2;
    TextView txtTelp;
    TextView txtKelas;
    String nama,nis,kelasRef,telp,email,kelas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initComponent();
        btnEdit.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        refMurid = FirebaseDatabase.getInstance().getReference("Users").child("Murid").child(currentUser.getUid());

        showDataMurid();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Murid murid = new Murid();
                Intent moveToEdit = new Intent(ProfileActivity.this, EditProfile.class);
                murid.setNis(nis);
                murid.setName(nama);
                murid.setTelp(telp);
                murid.setEmail(email);
                moveToEdit.putExtra(EditProfile.EXTRA_MURID,murid);
                startActivity(moveToEdit);
            }
        });
    }

    public void initComponent() {
        btnEdit = findViewById(R.id.btnEditProfile);
        progressBar = findViewById(R.id.progressBar_profile);
        txtNama = findViewById(R.id.txtNama);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelp = findViewById(R.id.txtPhone);
        txtNis2 = findViewById(R.id.txtNis_2);
        txtKelas = findViewById(R.id.txtKelas);
        txtNis = findViewById(R.id.txtNis);
    }

    public void setUpData() {
        txtNama.setText(nama);
        txtEmail.setText(email);
        txtNis.setText(nis);
        txtKelas.setText(kelas);
        txtTelp.setText(telp);
        txtNis2.setText(nis);
    }

    public void showDataMurid() {
        refMurid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                btnEdit.setVisibility(View.VISIBLE);
                nama = dataSnapshot.child("name").getValue(String.class);
                kelasRef = dataSnapshot.child("kelas").getValue(String.class);
                nis = dataSnapshot.child("nis").getValue(String.class);
                telp = dataSnapshot.child("telp").getValue(String.class);
                email = currentUser.getEmail();

                refKelas = FirebaseDatabase.getInstance().getReference("Kelas");
                refKelas.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       kelas = dataSnapshot.child(kelasRef).getValue(String.class);
                       setUpData();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
