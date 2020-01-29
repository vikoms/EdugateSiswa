package com.example.edugate.Models;

public class HelpDesk {
    String judulSaran;
    String isiSaran;

    public HelpDesk(String judulSaran, String isiSaran) {
        this.judulSaran = judulSaran;
        this.isiSaran = isiSaran;
    }

    public String getJudulSaran() {
        return judulSaran;
    }

    public void setJudulSaran(String judulSaran) {
        this.judulSaran = judulSaran;
    }

    public String getIsiSaran() {
        return isiSaran;
    }

    public void setIsiSaran(String isiSaran) {
        this.isiSaran = isiSaran;
    }
}
