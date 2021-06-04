package com.example.shoppingproject;

import com.example.shoppingproject.Model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartProcess {
    private static List<Cart> carts=new ArrayList<Cart>();
    public static void insert(Cart cart){
        carts.add(cart);
    }

    public static List<Cart>contents(){
        return carts;
    }
    public static double total(){
        double s=0;
        for(Cart cart:carts){
            s+=cart.getItem().getItemprice() * cart.getQuantity();

        }
        return s;
    }
}
