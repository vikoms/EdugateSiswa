package com.example.edugate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.edugate.Adapter.PerpustakaanAdapter;
import com.example.edugate.Models.Book;

import java.util.ArrayList;
import java.util.List;

public class PerpustakaanActivity extends AppCompatActivity {

    List<Book> listBook;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perpustakaan);

        listBook = new ArrayList<>();

        listBook.add(new Book("Buku Biologi", "Kelas 10", "Elangga",R.drawable.ipa1));
        listBook.add(new Book("Buku Fisika", "Kelas 10", "Elangga",R.drawable.ipa2));
        listBook.add(new Book("Buku Bahasa Indonesia", "Kelas 12", "Elangga",R.drawable.indo));
        listBook.add(new Book("Buku IPS", "Kelas 11", "Elangga",R.drawable.ips));
        listBook.add(new Book("Buku Matematika", "Kelas 10", "Elangga",R.drawable.matematika));
        listBook.add(new Book("Buku Bahasa Inggris", "Kelas 11", "Elangga",R.drawable.inggris));

        mLayoutManager = new LinearLayoutManager(this);

        RecyclerView rcv = (RecyclerView) findViewById(R.id.recycler_view);
        PerpustakaanAdapter adapter = new PerpustakaanAdapter(listBook, this);

        rcv.setLayoutManager(mLayoutManager);
        rcv.setAdapter(adapter);


    }
}
