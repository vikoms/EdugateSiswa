package com.example.edugate.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.edugate.Models.TugasRumah;
import com.example.edugate.PanggilGuruActivity;
import com.example.edugate.Perpustakaan;
import com.example.edugate.R;
import com.example.edugate.TugasRumahActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class FragmentHome extends Fragment implements View.OnClickListener {

    private int[] mImage = new int[]{
            R.drawable.foto_1, R.drawable.foto_2
    };

    private String[] mImageTitle = new String[]{
            "Contoh 1", "Contoh 2"
    };

    CardView panggil_guru, daftar_tugas, izin_piket, mPerpustakaan, help_desk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater If = getActivity().getLayoutInflater() ;
        View view = If.inflate(R.layout.fragment_home,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

//        UNTUK CAROUSEL
        CarouselView carouselView = (CarouselView) getView().findViewById(R.id.carousel);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImage[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), mImageTitle[position], Toast.LENGTH_SHORT).show();
            }
        });
        carouselView.setPageCount(mImage.length);

        CardView cvPanggilGuru = getView().findViewById(R.id.panggilGuru);
        cvPanggilGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Contoh", Toast.LENGTH_SHORT).show();
            }
        });


        panggil_guru = getView().findViewById(R.id.panggilGuru);
        panggil_guru.setOnClickListener(this);

        daftar_tugas = getView().findViewById(R.id.daftarTugas);
        daftar_tugas.setOnClickListener(this);

        izin_piket = getView().findViewById(R.id.izinPiket);
        izin_piket.setOnClickListener(this);

        mPerpustakaan = getView().findViewById(R.id.perpustakaan);
        mPerpustakaan.setOnClickListener(this);

        help_desk = getView().findViewById(R.id.helpDesk);
        help_desk.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

//        ISI UNTUK PINDAH FRAGMENT NYA DISINI
        switch (view.getId()) {
            case R.id.panggilGuru:
                Intent panggilguru = new Intent(getActivity().getApplication(), PanggilGuruActivity.class);
                startActivity(panggilguru);
                break;
            case R.id.perpustakaan:
                Intent perpustakaan = new Intent(getActivity().getApplication(), Perpustakaan.class);
                startActivity(perpustakaan);
            case R.id.daftarTugas:
                Intent tugas_rumah = new Intent(getActivity().getApplication(), TugasRumahActivity.class);
                startActivity(tugas_rumah);
                break;
        }
    }


    //    YANG INI JANGAN DI OTAK ATIK LAGI
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

    }
}


