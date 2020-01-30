package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edugate.Models.BeritaSekolah;

public class detail_berita extends AppCompatActivity {

    TextView title,desc,date;
    ImageView banner;
    public static final String EXTRA_BERITA = "extra_berita";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        title = findViewById(R.id.title_news);
        desc = findViewById(R.id.desc_news);
        banner = findViewById(R.id.news_banner);
        date = findViewById(R.id.date_news);


        BeritaSekolah berita = getIntent().getParcelableExtra(EXTRA_BERITA);
        title.setText(berita.getJudulBerita());
        desc.setText(berita.getIsiBerita());
        Glide.with(this).load(berita.getGambarBerita()).into(banner);
        date.setText(berita.getTanggalBerita());

    }
}
