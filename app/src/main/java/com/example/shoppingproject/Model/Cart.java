package com.example.shoppingproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
    private Item item;
    private Phone phone;
    private int quantity;

    public Cart(Item item, Phone phone, int quantity) {
        this.item = item;
        this.phone = phone;
        this.quantity = quantity;
    }

    public Cart(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Cart() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
