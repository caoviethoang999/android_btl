package com.example.shoppingproject.Service;

import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ItemService {
    @GET("/project/displayItem.php")
    Call<List<Item>> getItem();
    @POST("/project/createItem.php")
    @FormUrlEncoded
    Call<Item> addItem(@Field("itemname") String itemname,
                       @Field("itemprice") String itemprice,
                       @Field("itemquantity") String itemquantity,
                       @Field("photo") String photo);

    @POST("/project/updateItem.php")
    @FormUrlEncoded
    Call<Item> updateItem(@Field("itemid") int itemid,
                          @Field("itemname") String itemname,
                          @Field("itemprice") double itemprice,
                          @Field("itemquantity") int itemquantity,
                          @Field("photo") String photo);
    @POST("/project/deleteItem.php")
    @FormUrlEncoded
    Call<Item> deleteItem(@Field("itemid") int itemid);
    @POST("/project/searchItem.php")
    @FormUrlEncoded
    Call<List<Item>> searchItem(@Field("itemname") String itemname);
}
