package com.example.edugate.Models;

public class TugasRumah  {
    private String judul_tugas, keterangan_tugas, deadline_tugas;

    public TugasRumah(String judul_tugas, String keterangan_tugas, String deadline_tugas) {
        this.judul_tugas = judul_tugas;
        this.keterangan_tugas = keterangan_tugas;
        this.deadline_tugas = deadline_tugas;
    }

    public String getJudul_tugas() {
        return judul_tugas;
    }

    public void setJudul_tugas(String judul_tugas) {
        this.judul_tugas = judul_tugas;
    }

    public String getKeterangan_tugas() {
        return keterangan_tugas;
    }

    public void setKeterangan_tugas(String keterangan_tugas) {
        this.keterangan_tugas = keterangan_tugas;
    }

    public String getDeadline_tugas() {
        return deadline_tugas;
    }

    public void setDeadline_tugas(String deadline_tugas) {
        this.deadline_tugas = deadline_tugas;
    }
}
