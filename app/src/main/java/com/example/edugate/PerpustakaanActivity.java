package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    PerpustakaanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perpustakaan);

        listBook = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Perpustakaan");
        mStorageRef = FirebaseStorage.getInstance().getReference();


        viewAllPdf();

        mLayoutManager = new LinearLayoutManager(this);

        rcv = (RecyclerView) findViewById(R.id.recycler_view);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(mLayoutManager);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Perpustakaan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint("Search...");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    adapter.getFilter().filter(s);
                    return true;
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
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
                 adapter = new PerpustakaanAdapter(listBook, PerpustakaanActivity.this);

                rcv.setLayoutManager(mLayoutManager);
                rcv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
