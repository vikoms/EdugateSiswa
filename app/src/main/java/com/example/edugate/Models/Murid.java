package com.example.edugate.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Murid implements Parcelable {

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

    protected Murid(Parcel in) {
        email = in.readString();
        kelas = in.readString();
        name = in.readString();
        nis = in.readString();
        telp = in.readString();
    }

    public static final Creator<Murid> CREATOR = new Creator<Murid>() {
        @Override
        public Murid createFromParcel(Parcel in) {
            return new Murid(in);
        }

        @Override
        public Murid[] newArray(int size) {
            return new Murid[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(kelas);
        parcel.writeString(name);
        parcel.writeString(nis);
        parcel.writeString(telp);
    }
}
