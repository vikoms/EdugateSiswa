package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.edugate.Adapter.BeritaSekolahAdapter;
import com.example.edugate.Adapter.PanggilGuruAdapter;
import com.example.edugate.Models.BeritaSekolah;
import com.example.edugate.Models.PanggilGuru;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class beritaSekolahActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BeritaSekolah> listBerita;
    private BeritaSekolahAdapter myAdapter;

    DatabaseReference refBerita;
    StorageReference storageRef;
    private int[] mImage = new int[]{
            R.drawable.foto_1, R.drawable.foto_2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_sekolah);

        CarouselView carouselView = (CarouselView)findViewById(R.id.carouselBeritaSekolah);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImage[position]);
            }
        });

        listBerita = new ArrayList<>();

        refBerita = FirebaseDatabase.getInstance().getReference("Berita");

        mRecyclerView = (RecyclerView) findViewById(R.id.bsRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        refBerita.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listBerita.clear();
                for (DataSnapshot dataBerita : dataSnapshot.getChildren()) {
                    BeritaSekolah berita = dataBerita.getValue(BeritaSekolah.class);
                    listBerita.add(berita);
                }
                myAdapter = new BeritaSekolahAdapter(listBerita);
                mRecyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
