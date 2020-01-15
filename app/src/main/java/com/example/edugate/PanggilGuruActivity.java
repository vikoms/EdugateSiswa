package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.example.edugate.Adapter.PanggilGuruAdapter;
import com.example.edugate.Models.PanggilGuru;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PanggilGuruActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PanggilGuru> listGuru;
    private PanggilGuruAdapter myAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panggil_guru);

//        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
//        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
//        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));
//
//        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
//        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
//        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));
//
//        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
//        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
//        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));
//
//        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
//        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
//        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

//        Toast.makeText(this, currentUser.getUid(), Toast.LENGTH_SHORT).show();

        mRecyclerView = (RecyclerView) findViewById(R.id.pgRecyclerView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("Users").child("Guru");


    }

    @Override
    protected void onStart() {
        super.onStart();
        listGuru = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listGuru.clear();
                for (DataSnapshot guru : dataSnapshot.getChildren()) {
                    PanggilGuru panggilGuru = guru.getValue(PanggilGuru.class);
                    listGuru.add(panggilGuru);
                }
                myAdapter = new PanggilGuruAdapter(listGuru);
                mRecyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
