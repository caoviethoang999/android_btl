package com.example.shoppingproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Phone implements Serializable {
    @SerializedName("Phoneid")
    private int Phoneid;
    @SerializedName("Phonename")
    private String Phonename;
    @SerializedName("Phoneprice")
    private double Phoneprice;
    @SerializedName("Phonequantity")
    private int Phonequantity;
    @SerializedName("photo")
    private String photo;

    public Phone() {
    }

    public Phone(int phoneid, String phonename, double phoneprice, int phonequantity, String photo) {
        Phoneid = phoneid;
        Phonename = phonename;
        Phoneprice = phoneprice;
        Phonequantity = phonequantity;
        this.photo = photo;
    }

    public Phone(String phonename, double phoneprice, int phonequantity, String photo) {
        Phonename = phonename;
        Phoneprice = phoneprice;
        Phonequantity = phonequantity;
        this.photo = photo;
    }

    public int getPhoneid() {
        return Phoneid;
    }

    public void setPhoneid(int phoneid) {
        Phoneid = phoneid;
    }

    public String getPhonename() {
        return Phonename;
    }

    public void setPhonename(String phonename) {
        Phonename = phonename;
    }

    public double getPhoneprice() {
        return Phoneprice;
    }

    public void setPhoneprice(double phoneprice) {
        Phoneprice = phoneprice;
    }

    public int getPhonequantity() {
        return Phonequantity;
    }

    public void setPhonequantity(int phonequantity) {
        Phonequantity = phonequantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
