package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.edugate.Adapter.TugasRumahAdapter;
import com.example.edugate.Models.Murid;
import com.example.edugate.Models.TugasRumah;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TugasRumahActivity extends AppCompatActivity {

    private List<TugasRumah> mList;
    RecyclerView recyclerView;
    DatabaseReference ref;
    DatabaseReference refKelas;
    FirebaseUser currentUser;
    ArrayList<Murid> listMurid;
    String kelas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_rumah);
        ref = FirebaseDatabase.getInstance().getReference("Tugas");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_tugas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mList = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        refKelas = FirebaseDatabase.getInstance().getReference("Users").child("Murid").child(currentUser.getUid());


    }

    @Override
    protected void onStart() {
        super.onStart();

        refKelas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kelas = dataSnapshot.child("kelas").getValue(String.class);
                ref.orderByChild("kelas").equalTo(kelas).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mList.clear();
                        for (DataSnapshot  tugasSnap: dataSnapshot.getChildren()) {
                            TugasRumah tugas = tugasSnap.getValue(TugasRumah.class);
                            mList.add(tugas);
                        }
                        TugasRumahAdapter adapter = new TugasRumahAdapter(mList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
