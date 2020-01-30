package com.example.edugate;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugate.Adapter.IzinPiketAdapter;
import com.example.edugate.Models.IzinPiket;
import com.example.edugate.Models.PanggilGuru;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IzinPiketActivity extends AppCompatActivity {

    Dialog myDialog;
    Button btnPilihGuru;
    Button btnConfirm;
    EditText Edtnama;
    EditText Edtalasan;
    EditText Edtwaktu;
    TextView txtNamaGuru;
    List<PanggilGuru> guruList;
    DatabaseReference refGuru;
    DatabaseReference refIzinPiket;
    String namaGuru;
    String namaMurid;
    String waktuIzin;
    String alasanIzin;
    String uidGuru;
    ProgressBar progressBarIzin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izin_piket);
        initComponent();
        initDialog();

        refGuru = FirebaseDatabase.getInstance().getReference("Users").child("Guru");
        refIzinPiket = FirebaseDatabase.getInstance().getReference("Izin Piket");

        btnPilihGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView rvIzinPiket = myDialog.findViewById(R.id.rv_izin_piket_guru);
                IzinPiketAdapter izinPiketAdapter = new IzinPiketAdapter(guruList);

                izinPiketAdapter.setOnItemClickCallback(new IzinPiketAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(PanggilGuru data) {
                        namaGuru = data.getNama();
                        uidGuru = data.getUid();
                        myDialog.dismiss();
                        setData();
                    }
                });

                rvIzinPiket.setLayoutManager(new LinearLayoutManager(IzinPiketActivity.this));
                rvIzinPiket.setAdapter(izinPiketAdapter);
                izinPiketAdapter.notifyDataSetChanged();
                myDialog.show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        refGuru.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                guruList.clear();
                for (DataSnapshot itemGuru : dataSnapshot.getChildren()) {
                    PanggilGuru guru = itemGuru.getValue(PanggilGuru.class);
                    guruList.add(guru);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendData() {
        namaMurid = Edtnama.getText().toString();
        alasanIzin = Edtalasan.getText().toString();
        waktuIzin = Edtwaktu.getText().toString();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(namaMurid.isEmpty() || alasanIzin.isEmpty() || waktuIzin.isEmpty() || namaGuru.isEmpty()) {
            Toast.makeText(this, "Isi data dengan lengkap", Toast.LENGTH_SHORT).show();
        } else {

            progressBarIzin.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
            String id = refIzinPiket.push().getKey();
            String uidMurid = currentUser.getUid();
            IzinPiket izinPiket = new IzinPiket(namaMurid,waktuIzin,alasanIzin,uidGuru,"0",uidMurid);
            refIzinPiket.child(id).setValue(izinPiket).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(IzinPiketActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    Intent moveToHome = new Intent(IzinPiketActivity.this, HomeActivity.class);
                    startActivity(moveToHome);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBarIzin.setVisibility(View.GONE);
                    btnConfirm.setVisibility(View.VISIBLE);
                    Toast.makeText(IzinPiketActivity.this, "Izin Gagal, Cek Internet Anda", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    public void setData() {
        txtNamaGuru.setText(namaGuru);
    }


    public void initComponent() {
        btnPilihGuru = findViewById(R.id.btn_pilih_guru);
        btnConfirm = findViewById(R.id.btn_konfirmasi);
        Edtnama = findViewById(R.id.piketNama);
        Edtalasan = findViewById(R.id.alasanPiket);
        Edtwaktu = findViewById(R.id.jamPiket);
        txtNamaGuru = findViewById(R.id.txt_nama_guru);
        progressBarIzin = findViewById(R.id.progressBarIzin);
        progressBarIzin.setVisibility(View.GONE);
        guruList = new ArrayList<>();
    }

    public void initDialog() {

        myDialog = new Dialog(IzinPiketActivity.this);
        myDialog.setContentView(R.layout.dialog_guru_izin_piket);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}
