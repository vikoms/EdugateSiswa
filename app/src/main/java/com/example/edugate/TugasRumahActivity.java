package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.edugate.Adapter.TugasRumahAdapter;
import com.example.edugate.Models.TugasRumah;

import java.util.ArrayList;
import java.util.List;

public class TugasRumahActivity extends AppCompatActivity {

    private List<TugasRumah> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_rumah);


        mList = new ArrayList<>();
        mList.add(new TugasRumah("Matematika", "Halaman 200 No 2 dan 3 ", "12 Desember 2018"));
        mList.add(new TugasRumah("Bahasa indonesia", "Halaman 121 bagian 3", "8 Desember 2018"));
        mList.add(new TugasRumah("Bahasa Inggris", "Halaman 12 PG nya saja", "4 Desember 2018"));
        mList.add(new TugasRumah("IPA", "Halaman 51 Essaynya Saja", "5 Desember 2018"));


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_tugas);
        TugasRumahAdapter adapter = new TugasRumahAdapter(mList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
