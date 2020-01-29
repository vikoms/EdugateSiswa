package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detail_berita extends AppCompatActivity {

    TextView title,desc,date;
    ImageView banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        title.findViewById(R.id.title_news);
        desc.findViewById(R.id.desc_news);
        banner.findViewById(R.id.news_banner);
        date.findViewById(R.id.date_news);



    }
}
