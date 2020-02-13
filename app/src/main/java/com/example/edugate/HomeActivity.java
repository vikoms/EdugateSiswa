package com.example.edugate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edugate.Fragment.FragmentHome;
import com.example.edugate.Models.Murid;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
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

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView panggil_guru, daftar_tugas, izin_piket, mPerpustakaan, help_desk;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference ref;
    NavigationView navigationView;
    String nama,email;
    Uri imgProfile;

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

        ImageView iconSetting = findViewById(R.id.icon_setting);

        iconSetting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
                if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.closeDrawer(GravityCompat.END);
                }
            }
        });


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 nama = dataSnapshot.child("name").getValue(String.class);
                 email = currentUser.getEmail();
                 imgProfile = currentUser.getPhotoUrl();
                 updateDrawer();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void updateDrawer() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView headerName = (TextView) header.findViewById(R.id.tv_user_name);
        TextView headerEmail = (TextView) header.findViewById(R.id.tv_user_email);
        ImageView headerPhoto = (ImageView) header.findViewById(R.id.img_photo_profile);
        headerName.setText(nama);
        headerEmail.setText(email);
        Glide.with(getApplicationContext()).load(imgProfile).into(headerPhoto);

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_logout:
                mAuth.signOut();
                Intent moveToLogin = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(moveToLogin);
                finish();
                return true;
            case R.id.tk:
                Intent about = new Intent(HomeActivity.this,AboutActivity.class);
                startActivity(about);
                return true;
            case R.id.btnProfile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
