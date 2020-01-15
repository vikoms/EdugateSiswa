package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edugate.Fragment.FragmentHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class HomeActivity extends AppCompatActivity{

    CardView panggil_guru,daftar_tugas,izin_piket,mPerpustakaan,help_desk;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadFragment(new FragmentHome());

        final TextView toolbar = (TextView) findViewById(R.id.textToolbar);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        final String userUid = currentUser.getUid();

        ref = FirebaseDatabase.getInstance().getReference("Users").child("Murid").child(userUid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.child("nama").getValue(String.class);
//                toolbar.setText(nama);
                Toast.makeText(HomeActivity.this, nama, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }
}
