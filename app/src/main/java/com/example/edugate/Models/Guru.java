package com.example.edugate.Models;

public class Guru {

    private String email,kota,nama,nip,pelajaran,telp,uid;

    public Guru() {}

    public Guru(String email, String kota, String nama, String nip, String pelajaran, String telp, String uid) {
        this.email = email;
        this.kota = kota;
        this.nama = nama;
        this.nip = nip;
        this.pelajaran = pelajaran;
        this.telp = telp;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
