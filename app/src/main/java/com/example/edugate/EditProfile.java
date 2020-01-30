package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.edugate.Models.BeritaSekolah;
import com.example.edugate.Models.Murid;

public class EditProfile extends AppCompatActivity {

    public static final String EXTRA_MURID = "extra_murid";
    EditText edtNama;
    EditText edtTelp;
    EditText edtEmail;
    String nama,telp,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initComponent();
        setUpData();
    }

    public void initComponent() {
        edtNama = findViewById(R.id.edtNama);
        edtTelp = findViewById(R.id.edtTelphone);
        edtEmail =findViewById(R.id.edtEmail);
    }

    public void setUpData() {
        Murid murid = getIntent().getParcelableExtra(EXTRA_MURID);
        nama = murid.getName();
        telp = murid.getTelp();
        email = murid.getEmail();

        edtNama.setText(nama);
        edtTelp.setText(telp);
        edtEmail.setText(email);
    }
}
