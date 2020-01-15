package com.example.edugate.Models;

public class PanggilGuru {
    private String nama,Pelajaran;
    private int gambar;

    public PanggilGuru(String nama, String Pelajaran, int gambar) {
        this.nama = nama;
        this.Pelajaran = Pelajaran;
        this.gambar = gambar;
    }


    public PanggilGuru() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPelajaran() {
        return Pelajaran;
    }

    public void setPelajaran(String Pelajaran) {
        this.Pelajaran = Pelajaran;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
