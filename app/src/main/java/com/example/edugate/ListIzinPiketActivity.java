package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.edugate.Adapter.IzinPiketAdapter;
import com.example.edugate.Adapter.ListIzinPiketAdapter;
import com.example.edugate.Models.IzinPiket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListIzinPiketActivity extends AppCompatActivity {
    RecyclerView rv_container;
    List<IzinPiket> izinPiketList;
    DatabaseReference refIzinPiket;
    FirebaseUser currentUser;
    String uidMurid;
    ProgressBar pgIzinPiket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_izin_piket);
        rv_container = findViewById(R.id.rv_list_izin_piket);
        pgIzinPiket = findViewById(R.id.pg_list_izin_piket);
        izinPiketList = new ArrayList<>();
        refIzinPiket  = FirebaseDatabase.getInstance().getReference("Izin Piket");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uidMurid = currentUser.getUid();
        getDataIzinPiket();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("List Izin Piket");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }

    private void getDataIzinPiket() {
        refIzinPiket.orderByChild("keyMurid").equalTo(uidMurid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pgIzinPiket.setVisibility(View.INVISIBLE);
                for (DataSnapshot itemIzinPiket : dataSnapshot.getChildren()) {
                    IzinPiket izinPiket = itemIzinPiket.getValue(IzinPiket.class);
                    izinPiketList.add(izinPiket);
                }
                setupRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setupRecyclerView() {
        ListIzinPiketAdapter adapter = new ListIzinPiketAdapter(izinPiketList,this);
        rv_container.setLayoutManager(new LinearLayoutManager(this));
        rv_container.setHasFixedSize(true);
        rv_container.setAdapter(adapter);
    }

}
