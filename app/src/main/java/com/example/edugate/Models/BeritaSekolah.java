package com.example.edugate.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BeritaSekolah implements Parcelable {

    private String judulBerita,isiBerita,gambarBerita,tanggalBerita;

    public BeritaSekolah(String judulBerita, String isiBerita, String gambarBerita, String tanggalBerita) {
        this.judulBerita = judulBerita;
        this.isiBerita = isiBerita;
        this.gambarBerita = gambarBerita;
        this.tanggalBerita = tanggalBerita;
    }

    public BeritaSekolah() {
    }

    protected BeritaSekolah(Parcel in) {
        judulBerita = in.readString();
        isiBerita = in.readString();
        gambarBerita = in.readString();
        tanggalBerita = in.readString();
    }

    public static final Creator<BeritaSekolah> CREATOR = new Creator<BeritaSekolah>() {
        @Override
        public BeritaSekolah createFromParcel(Parcel in) {
            return new BeritaSekolah(in);
        }

        @Override
        public BeritaSekolah[] newArray(int size) {
            return new BeritaSekolah[size];
        }
    };

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getGambarBerita() {
        return gambarBerita;
    }

    public void setGambarBerita(String gambarBerita) {
        this.gambarBerita = gambarBerita;
    }

    public String getTanggalBerita() {
        return tanggalBerita;
    }

    public void setTanggalBerita(String tanggalBerita) {
        this.tanggalBerita = tanggalBerita;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(judulBerita);
        parcel.writeString(isiBerita);
        parcel.writeString(gambarBerita);
        parcel.writeString(tanggalBerita);
    }
}
