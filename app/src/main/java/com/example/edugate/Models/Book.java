package com.example.edugate.Models;

public class Book {

    private String judul_buku,kelas,penerbit;
    private int image;

    public Book(String judul_buku, String kelas, String penerbit, int image) {
        this.judul_buku = judul_buku;
        this.kelas = kelas;
        this.penerbit = penerbit;
        this.image = image;
    }

    public String getJudul_buku() {
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
