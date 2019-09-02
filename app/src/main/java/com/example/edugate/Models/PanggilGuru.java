package com.example.edugate.Models;

public class PanggilGuru {
    private String nama,pelajaran;
    private int gambar;

    public PanggilGuru(String nama, String pelajaran, int gambar) {
        this.nama = nama;
        this.pelajaran = pelajaran;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
