package com.example.edugate;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugate.Adapter.IzinPiketAdapter;
import com.example.edugate.Models.Guru;
import com.example.edugate.Models.IzinPiket;
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
import java.util.Calendar;
import java.util.List;

public class IzinPiketActivity extends AppCompatActivity {

    Dialog myDialog;
    Button btnPilihGuru, btnConfirm;
    ImageButton btnChooseTime;
    EditText Edtnama, Edtalasan;
    TextView txtNamaGuru, tvWaktu;
    List<Guru> guruList;
    DatabaseReference refGuru;
    DatabaseReference refIzinPiket;
    String namaGuru, namaMurid, waktuIzin, alasanIzin, uidGuru, keyGuru_status;
    ProgressBar progressBarIzin, pgDialogGuru;

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
                    public void onItemClicked(Guru data) {
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

        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Izin Piket");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    private void showTimeDialog() {
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int min = Calendar.getInstance().get(Calendar.MINUTE);

        boolean is24HoursFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String time = hour + ":" + min;
                tvWaktu.setText(time);
            }
        }, hour, min, is24HoursFormat);
        timePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.izin_piket_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.list_izin_piket) {
            Intent moveToList = new Intent(IzinPiketActivity.this, ListIzinPiketActivity.class);
            startActivity(moveToList);
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        refGuru.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                guruList.clear();
                pgDialogGuru.setVisibility(View.INVISIBLE);
                for (DataSnapshot itemGuru : dataSnapshot.getChildren()) {
                    Guru guru = itemGuru.getValue(Guru.class);
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
        waktuIzin = tvWaktu.getText().toString();
        keyGuru_status = uidGuru + "_0";
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (namaMurid.isEmpty() || alasanIzin.isEmpty() || waktuIzin.isEmpty() || namaGuru.isEmpty()) {
            Toast.makeText(this, "Isi data dengan lengkap", Toast.LENGTH_SHORT).show();
        } else {

            progressBarIzin.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
            String id = refIzinPiket.push().getKey();
            String uidMurid = currentUser.getUid();
            IzinPiket izinPiket = new IzinPiket(id, namaMurid, waktuIzin, alasanIzin, uidGuru, "0", uidMurid, keyGuru_status);
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
        tvWaktu = findViewById(R.id.tv_waktu_izin_piket);
        txtNamaGuru = findViewById(R.id.txt_nama_guru);
        progressBarIzin = findViewById(R.id.progressBarIzin);
        progressBarIzin.setVisibility(View.GONE);
        btnChooseTime = findViewById(R.id.btn_choose_time_izin_piket);
        guruList = new ArrayList<>();
    }

    public void initDialog() {

        myDialog = new Dialog(IzinPiketActivity.this);
        myDialog.setContentView(R.layout.dialog_guru_izin_piket);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pgDialogGuru = myDialog.findViewById(R.id.pg_guru_izin_piket);
    }


}
