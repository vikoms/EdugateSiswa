package com.example.edugate.Models;

public class Book {

    private String name;
    private String kelas;
    private String penerbit;
    private String url;
    private int image;

    public Book(String name, String kelas, String penerbit, String url, int image) {
        this.name = name;
        this.kelas = kelas;
        this.penerbit = penerbit;
        this.url = url;
        this.image = image;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
