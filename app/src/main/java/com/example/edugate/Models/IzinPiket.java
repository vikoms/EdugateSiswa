package com.example.edugate.Models;

public class IzinPiket {
    private String namaMurid,waktuIzin,alasanIzin,keyGuru,status,keyMurid;

    public IzinPiket(String namaMurid, String waktuIzin, String alasanIzin, String keyGuru, String status, String keyMurid) {
        this.namaMurid = namaMurid;
        this.waktuIzin = waktuIzin;
        this.alasanIzin = alasanIzin;
        this.keyGuru = keyGuru;
        this.status = status;
        this.keyMurid = keyMurid;
    }

    public String getNamaMurid() {
        return namaMurid;
    }

    public void setNamaMurid(String namaMurid) {
        this.namaMurid = namaMurid;
    }

    public String getWaktuIzin() {
        return waktuIzin;
    }

    public void setWaktuIzin(String waktuIzin) {
        this.waktuIzin = waktuIzin;
    }

    public String getAlasanIzin() {
        return alasanIzin;
    }

    public void setAlasanIzin(String alasanIzin) {
        this.alasanIzin = alasanIzin;
    }

    public String getKeyGuru() {
        return keyGuru;
    }

    public void setKeyGuru(String keyGuru) {
        this.keyGuru = keyGuru;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyMurid() {
        return keyMurid;
    }

    public void setKeyMurid(String keyMurid) {
        this.keyMurid = keyMurid;
    }
}
