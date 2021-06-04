package com.example.shoppingproject.Service;

import com.example.shoppingproject.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("/project/createUser.php")
    @FormUrlEncoded
    Call<User> addUser(@Field("username") String username,
                       @Field("useraddress") String useraddress,
                       @Field("userpayment") String userpayment,
                       @Field("email") String email,
                       @Field("useraccount") String useraccount,
                       @Field("userpassword") String userpassword,
                       @Field("position") int position);
    @POST("/project/login.php")
    @FormUrlEncoded
    Call<User> Login(@Field("useraccount") String useraccount,
                     @Field("userpassword") String userpassword);
}
