package com.example.edugate.Models;

public class TugasRumah  {
    private String nama, desc, date, kelas,time;

    public TugasRumah(String nama, String desc, String date, String kelas, String time) {
        this.nama = nama;
        this.desc = desc;
        this.date = date;
        this.kelas = kelas;
        this.time = time;
    }

    public TugasRumah() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
