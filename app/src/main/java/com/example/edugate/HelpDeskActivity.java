package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edugate.Models.HelpDesk;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpDeskActivity extends AppCompatActivity {
    private EditText judul;
    private EditText kritiksaran;
    private Button kirim;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);

        judul = findViewById(R.id.judul);
        kritiksaran = findViewById(R.id.kritiksaran);
        kirim = findViewById(R.id.btn);
        ref = FirebaseDatabase.getInstance().getReference("Help Desk");
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judulVal = judul.getText().toString();
                String kritiksaranVal = kritiksaran.getText().toString();

                String id = ref.push().getKey();
                HelpDesk helpDesk= new HelpDesk(judulVal,kritiksaranVal);
                ref.child(id).setValue(helpDesk);
                Toast.makeText(HelpDeskActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
