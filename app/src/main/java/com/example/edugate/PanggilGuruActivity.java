package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.edugate.Adapter.PanggilGuruAdapter;
import com.example.edugate.Models.PanggilGuru;

import java.util.ArrayList;
import java.util.List;

public class PanggilGuruActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PanggilGuru> listGuru;
    private PanggilGuruAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panggil_guru);

        listGuru = new ArrayList<>();
        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));

        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));

        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));

        listGuru.add(new PanggilGuru("John Doe", "IPA", R.drawable.john));
        listGuru.add(new PanggilGuru("Selfie Amanda", "IPS", R.drawable.selfie));
        listGuru.add(new PanggilGuru("Natalie", "Matematika", R.drawable.natalie));

        mRecyclerView = (RecyclerView) findViewById(R.id.pgRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myAdapter = new PanggilGuruAdapter(listGuru);
        mRecyclerView.setAdapter(myAdapter);


    }
}
