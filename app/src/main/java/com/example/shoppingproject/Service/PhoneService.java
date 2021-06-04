package com.example.shoppingproject.Service;

import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.Phone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PhoneService {
    @GET("/project/displayPhone.php")
    Call<List<Phone>> getPhone();
    @POST("/project/createPhone.php")
    @FormUrlEncoded
    Call<Phone> addPhone(@Field("Phonename") String Phonename,
                       @Field("Phoneprice") String Phoneprice,
                       @Field("Phonequantity") String Phonequantity,
                       @Field("photo") String photo);

    @POST("/project/updatePhone.php")
    @FormUrlEncoded
    Call<Phone> updatePhone(@Field("Phoneid") int Phoneid,
                          @Field("Phonename") String Phonename,
                          @Field("Phoneprice") double Phoneprice,
                          @Field("Phonequantity") int Phonequantity,
                          @Field("photo") String photo);
    @POST("/project/deletePhone.php")
    @FormUrlEncoded
    Call<Phone> deletePhone(@Field("Phoneid") int Phoneid);
    @POST("/project/searchPhone.php")
    @FormUrlEncoded
    Call<List<Phone>> searchPhone(@Field("Phonename") String Phonename);
}
