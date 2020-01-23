package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.edugate.Adapter.PerpustakaanAdapter;
import com.example.edugate.Models.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PerpustakaanActivity extends AppCompatActivity {

    List<Book> listBook;

    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase database;
    StorageReference mStorageRef;
    DatabaseReference ref;
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perpustakaan);

        listBook = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Perpustakaan");
        mStorageRef = FirebaseStorage.getInstance().getReference();
//        listBook.add(new Book("Buku Biologi", "Kelas 10", "Elangga",R.drawable.ipa1));
//        listBook.add(new Book("Buku Fisika", "Kelas 10", "Elangga",R.drawable.ipa2));
//        listBook.add(new Book("Buku Bahasa Indonesia", "Kelas 12", "Elangga",R.drawable.indo));
//        listBook.add(new Book("Buku IPS", "Kelas 11", "Elangga",R.drawable.ips));
//        listBook.add(new Book("Buku Matematika", "Kelas 10", "Elangga",R.drawable.matematika));
//        listBook.add(new Book("Buku Bahasa Inggris", "Kelas 11", "Elangga",R.drawable.inggris));


        viewAllPdf();

        mLayoutManager = new LinearLayoutManager(this);

        rcv = (RecyclerView) findViewById(R.id.recycler_view);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(mLayoutManager);



    }

    private void viewAllPdf() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listBook.clear();

                for (DataSnapshot bookList : dataSnapshot.getChildren()) {
                    Book book = bookList.getValue(Book.class);
                    listBook.add(book);
                }
                PerpustakaanAdapter adapter = new PerpustakaanAdapter(listBook, PerpustakaanActivity.this);

                rcv.setLayoutManager(mLayoutManager);
                rcv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
