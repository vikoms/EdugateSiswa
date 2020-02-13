package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edugate.Models.BeritaSekolah;
import com.example.edugate.Models.Murid;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MURID = "extra_murid";
    public static final String EXTRA_KEY = "extra_key";
    EditText edtNama;
    EditText edtTelp;
    EditText edtEmail;
    ImageView imgProfile;
    Button btnSimpanProfile,btnGantiPassword;
    String nama,telp,email,kelas,nis,keyKelas;
    ProgressBar progressBarEdit;
    public static final int pReqCode = 1;
    public static final int REQUESCODE = 1;
    private Uri imgUri;
    ProgressDialog dialog;
    Dialog  myDialog;

    DatabaseReference databaseRef;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        initComponent();
        setUpData();
        initListener();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    public void initComponent() {
        edtNama = findViewById(R.id.edtNama);
        edtTelp = findViewById(R.id.edtTelphone);
        imgProfile = findViewById(R.id.photo_profile);
        btnSimpanProfile = findViewById(R.id.btn_simpan_profile);
        btnGantiPassword = findViewById(R.id.btn_change_password);
        dialog = new ProgressDialog(EditProfile.this);
        dialog.setMessage("Loading...");

        myDialog = new Dialog(EditProfile.this);
        myDialog.setContentView(R.layout.dialog_change_password);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    public void setUpData() {
        Murid murid = getIntent().getParcelableExtra(EXTRA_MURID);
        nama = murid.getName();
        telp = murid.getTelp();
        email = murid.getEmail();
        kelas = getIntent().getStringExtra(EXTRA_KEY);
        nis = murid.getNis();

        edtNama.setText(nama);
        edtTelp.setText(telp);
//        edtEmail.setText(email);

    }

    public void initListener() {

        imgProfile.setOnClickListener(this);
        btnSimpanProfile.setOnClickListener(this);
        btnGantiPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo_profile:
                if(Build.VERSION.SDK_INT  >= 22) {
                    checkAndRequestForPermission();
                } else {
                    openGallery();
                }
                break;
            case R.id.btn_simpan_profile:
//                progressBarEdit.setVisibility(View.VISIBLE);
                btnSimpanProfile.setVisibility(View.GONE);
                nama = edtNama.getText().toString();
//                email = edtEmail.getText().toString();
                telp = edtTelp.getText().toString();
                dialog.show();
                updateData();
                break;
            case R.id.btn_change_password:
                showDialog();
        }
    }

    private void showDialog() {

        myDialog.show();
        final EditText edtNewPassword = myDialog.findViewById(R.id.edit_new_password);
        final EditText edtOldPassword = myDialog.findViewById(R.id.edit_old_password);
        final Button btnConfirmChangePassword = myDialog.findViewById(R.id.btn_confirm_change_password);
        final ProgressBar pgChangePassword = myDialog.findViewById(R.id.progressBar_change_password);
        myDialog.show();

        btnConfirmChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgChangePassword.setVisibility(View.VISIBLE);
                btnConfirmChangePassword.setVisibility(View.INVISIBLE);
                final String newPassword = edtNewPassword.getText().toString();
                String oldPassword = edtOldPassword.getText().toString();

                if (newPassword.isEmpty() || oldPassword.isEmpty()) {
                    pgChangePassword.setVisibility(View.INVISIBLE);
                    btnConfirmChangePassword.setVisibility(View.VISIBLE);
                    Toast.makeText(EditProfile.this, "Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential credential = EmailAuthProvider
                            .getCredential(currentUser.getEmail(), oldPassword);

                    currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                currentUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditProfile.this, "Update Password Berhasil", Toast.LENGTH_SHORT).show();
                                            myDialog.dismiss();
                                        } else {
                                            pgChangePassword.setVisibility(View.INVISIBLE);
                                            btnConfirmChangePassword.setVisibility(View.VISIBLE);
                                            Toast.makeText(EditProfile.this, "Panjang Password min 8 char", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            } else {

                                pgChangePassword.setVisibility(View.INVISIBLE);
                                btnConfirmChangePassword.setVisibility(View.VISIBLE);
                                Toast.makeText(EditProfile.this, "Password Sebelumnya Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                        }
                    });
                }


            }
        });


    }

    private void updateData() {
        databaseRef  = FirebaseDatabase.getInstance().getReference("Users").child("Murid").child(currentUser.getUid());

        if(imgUri != null) {
            StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_murid_photos");
            final StorageReference imageFilePath = mStorage.child(imgUri.getLastPathSegment());
            imageFilePath.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            UserProfileChangeRequest updateProfile = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(uri)
                                    .setDisplayName(nama)
                                    .build();

                            currentUser.updateProfile(updateProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Murid murid = new Murid(email,kelas,nama,nis,telp);
                                    databaseRef.setValue(murid);
                                    Toast.makeText(EditProfile.this, "Update profile berhasil", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    startActivity(new Intent(EditProfile.this, HomeActivity.class));
                                    finish();

                                }
                            });
                        }
                    });
                }
            });
        } else {
            Murid murid = new Murid(email, kelas, nama, nis, telp);
            databaseRef.setValue(murid);

            Toast.makeText(EditProfile.this, "Update profile berhasil", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(EditProfile.this, HomeActivity.class));
            finish();
        }

    }

    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(EditProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(EditProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Pleast accept for required permission", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(EditProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, pReqCode);
            }
        } else {
            openGallery();
        }
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            imgUri = data.getData();
            Glide.with(EditProfile.this).load(imgUri).into(imgProfile);
        }
    }
}
