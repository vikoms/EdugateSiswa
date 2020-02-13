package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AboutActivity extends AppCompatActivity {

    private ImageView viko,salman,fajar,dea,firman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initComponent();
        initPicture();

    }

    private void initPicture() {

        Glide.with(this).load(R.drawable.profile).into(viko);
        Glide.with(this).load(R.drawable.salman_profile).into(salman);
        Glide.with(this).load(R.drawable.fajar_profile).into(fajar);
        Glide.with(this).load(R.drawable.dea_profile).into(dea);
        Glide.with(this).load(R.drawable.firman_profile).into(firman);

    }

    private void initComponent() {

        viko = findViewById(R.id.viko_profile);
        salman = findViewById(R.id.salman_profile);
        fajar = findViewById(R.id.fajar_profile);
        dea = findViewById(R.id.dea_profile);
        firman = findViewById(R.id.firman_profile);

    }

}
