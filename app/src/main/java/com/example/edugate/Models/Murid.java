package com.example.edugate.Models;

public class Murid {

    private String email,kelas,name,nis,telp;

    public Murid() {
    }

    public Murid(String email, String kelas, String name, String nis, String telp) {
        this.email = email;
        this.kelas = kelas;
        this.name = name;
        this.nis = nis;
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
}
