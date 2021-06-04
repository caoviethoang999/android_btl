package com.example.shoppingproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("itemid")
    private int itemid;
    @SerializedName("itemname")
    private String itemname;
    @SerializedName("itemprice")
    private double itemprice;
    @SerializedName("itemquantity")
    private int itemquantity;

    public Item() {
    }

    @SerializedName("photo")
    private String photo;

    public Item(int itemid, String itemname, double itemprice, int itemquantity, String photo) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
        this.photo = photo;
    }

    public Item(String itemname, double itemprice, int itemquantity, String photo) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
        this.photo = photo;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getItemprice() {
        return itemprice;
    }

    public void setItemprice(double itemprice) {
        this.itemprice = itemprice;
    }

    public int getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(int itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
