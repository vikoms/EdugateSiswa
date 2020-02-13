package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    String nama, nis, kelasRef, telp, email, kelas;
    ImageView photoProfile;
    Uri profileGambar;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initComponent();
        btnEdit.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        refMurid = FirebaseDatabase.getInstance().getReference("Users").child("Murid").child(currentUser.getUid());
        dialog = new ProgressDialog(ProfileActivity.this);

        dialog.setMessage("Loading");
        dialog.show();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Murid murid = new Murid();
                Intent moveToEdit = new Intent(ProfileActivity.this, EditProfile.class);
                murid.setNis(nis);
                murid.setName(nama);
                murid.setTelp(telp);
                murid.setEmail(email);
                murid.setKelas(kelas);
                moveToEdit.putExtra(EditProfile.EXTRA_MURID, murid);
                moveToEdit.putExtra(EditProfile.EXTRA_KEY, kelasRef);
                startActivity(moveToEdit);
            }
        });

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    public void initComponent() {
        btnEdit = findViewById(R.id.btnEditProfile);
//        progressBar = findViewById(R.id.progressBar_profile);
        txtNama = findViewById(R.id.txtNama);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelp = findViewById(R.id.txtPhone);
        txtNis2 = findViewById(R.id.txtNis_2);
        txtKelas = findViewById(R.id.txtKelas);
        txtNis = findViewById(R.id.txtNis);
        photoProfile = findViewById(R.id.photo_profile);
    }

    public void setUpData() {
        txtNama.setText(nama);
        txtEmail.setText(email);
        txtNis.setText(nis);
        txtKelas.setText(kelas);
        txtTelp.setText(telp);
        txtNis2.setText(nis);
        Glide.with(ProfileActivity.this).load(profileGambar).into(photoProfile);
    }

    public void showDataMurid() {
        refMurid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                progressBar.setVisibility(View.GONE);
//                dialog.dismiss();
                btnEdit.setVisibility(View.VISIBLE);
                nama = dataSnapshot.child("name").getValue(String.class);
                kelasRef = dataSnapshot.child("kelas").getValue(String.class);
                nis = dataSnapshot.child("nis").getValue(String.class);
                telp = dataSnapshot.child("telp").getValue(String.class);
                email = currentUser.getEmail();
                profileGambar = currentUser.getPhotoUrl();
                refKelas = FirebaseDatabase.getInstance().getReference("Kelas");
                refKelas.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        kelas = dataSnapshot.child(kelasRef).getValue(String.class);
                        setUpData();
                        dialog.dismiss();
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


    @Override
    protected void onStart() {
        super.onStart();

        showDataMurid();

    }
}
