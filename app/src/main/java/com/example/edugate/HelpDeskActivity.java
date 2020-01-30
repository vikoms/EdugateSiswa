package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edugate.Models.HelpDesk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpDeskActivity extends AppCompatActivity {
    private EditText judul;
    private EditText kritiksaran;
    private Button kirim;
    DatabaseReference ref;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);

        judul = findViewById(R.id.judul);
        kritiksaran = findViewById(R.id.kritiksaran);
        kirim = findViewById(R.id.btn);
        setupDialog();
        ref = FirebaseDatabase.getInstance().getReference("Help Desk");
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judulVal = judul.getText().toString();
                String kritiksaranVal = kritiksaran.getText().toString();

                String id = ref.push().getKey();
                HelpDesk helpDesk= new HelpDesk(judulVal,kritiksaranVal);
                ref.child(id).setValue(helpDesk).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            myDialog.show();
                            Button btnOk = myDialog.findViewById(R.id.btn_ok);
                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    myDialog.dismiss();
                                    Intent moveToHome = new Intent(HelpDeskActivity.this, HomeActivity.class);
                                    startActivity(moveToHome);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void setupDialog() {
        myDialog = new Dialog(HelpDeskActivity.this);
        myDialog.setContentView(R.layout.dialog_help_desk);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
