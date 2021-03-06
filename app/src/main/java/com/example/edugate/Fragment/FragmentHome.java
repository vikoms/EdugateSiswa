package com.example.edugate.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.edugate.HelpDeskActivity;
import com.example.edugate.IzinPiketActivity;
import com.example.edugate.PanggilGuruActivity;
import com.example.edugate.PerpustakaanActivity;
import com.example.edugate.R;
import com.example.edugate.TugasRumahActivity;
import com.example.edugate.beritaSekolahActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class FragmentHome extends Fragment implements View.OnClickListener {

    CardView panggil_guru, daftar_tugas, izin_piket, mPerpustakaan, help_desk, beritaSekolah,helpDesk;
    Button more;
    ImageView imgLogo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater If = getActivity().getLayoutInflater() ;
        View view = If.inflate(R.layout.fragment_home,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        CardView cvPanggilGuru = getView().findViewById(R.id.panggilGuru);
        cvPanggilGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Contoh", Toast.LENGTH_SHORT).show();
            }
        });

        imgLogo = getView().findViewById(R.id.image_smkn4);
        Glide.with(getActivity()).load(R.drawable.smkn4).into(imgLogo);

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

        beritaSekolah = getView().findViewById(R.id.beritaSekolah);
        beritaSekolah.setOnClickListener(this);

        more = getView().findViewById(R.id.btn_more_beritaSekolah);
        more.setOnClickListener(this);

        helpDesk = getView().findViewById(R.id.helpDesk);
        helpDesk.setOnClickListener(this);




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
                Intent perpustakaan = new Intent(getActivity().getApplication(), PerpustakaanActivity.class);
                startActivity(perpustakaan);
                break;
            case R.id.daftarTugas:
                Intent tugas_rumah = new Intent(getActivity().getApplication(), TugasRumahActivity.class);
                startActivity(tugas_rumah);
                break;
            case R.id.beritaSekolah:
                startActivity(new Intent(getActivity().getApplication(), beritaSekolahActivity.class));
                break;
            case R.id.btn_more_beritaSekolah:
                startActivity(new Intent(getActivity().getApplication(), beritaSekolahActivity.class));
                break;
            case R.id.izinPiket:
                startActivity(new Intent(getActivity().getApplication(), IzinPiketActivity.class));
                break;
            case R.id.helpDesk:
                startActivity(new Intent(getActivity().getApplication(), HelpDeskActivity.class));
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


