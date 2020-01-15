package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.edugate.Adapter.BeritaSekolahAdapter;
import com.example.edugate.Adapter.PanggilGuruAdapter;
import com.example.edugate.Models.BeritaSekolah;
import com.example.edugate.Models.PanggilGuru;
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
        listBerita.add(new BeritaSekolah(R.drawable.foto_1,"Murid Juara 2 dan 3 Seleksi Nasional ASC 2017","Rizky Muhammad (XII-TKJ1) dan Rakha Fauzi Muhammad,"));
        listBerita.add(new BeritaSekolah(R.drawable.foto_1,"Murid Juara 2 dan 3 Seleksi Nasional ASC 2017","Rizky Muhammad (XII-TKJ1) dan Rakha Fauzi Muhammad,"));
        listBerita.add(new BeritaSekolah(R.drawable.foto_1,"Murid Juara 2 dan 3 Seleksi Nasional ASC 2017","Rizky Muhammad (XII-TKJ1) dan Rakha Fauzi Muhammad,"));
        listBerita.add(new BeritaSekolah(R.drawable.foto_1,"Murid Juara 2 dan 3 Seleksi Nasional ASC 2017","Rizky Muhammad (XII-TKJ1) dan Rakha Fauzi Muhammad, "));

        mRecyclerView = (RecyclerView) findViewById(R.id.bsRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myAdapter = new BeritaSekolahAdapter(listBerita);
        mRecyclerView.setAdapter(myAdapter);

    }
}
