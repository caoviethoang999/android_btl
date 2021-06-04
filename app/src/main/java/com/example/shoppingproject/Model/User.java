package com.example.shoppingproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    @Expose
    private int userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("useraddress")
    @Expose
    private String useraddress;
    @SerializedName("userpayment")
    @Expose
    private String userpayment;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("useraccount")
    @Expose
    private String useraccount;
    @SerializedName("userpassword")
    @Expose
    private String userpassword;
    @SerializedName("position")
    @Expose
    private int position;

    public User(int userid, String username, String useraddress, String userpayment, String email, String useraccount, String userpassword, int position) {
        this.userid = userid;
        this.username = username;
        this.useraddress = useraddress;
        this.userpayment = userpayment;
        this.email = email;
        this.useraccount = useraccount;
        this.userpassword = userpassword;
        this.position = position;
    }

    public User(String username, String useraddress, String userpayment, String email, String useraccount, String userpassword, int position) {
        this.username = username;
        this.useraddress = useraddress;
        this.userpayment = userpayment;
        this.email = email;
        this.useraccount = useraccount;
        this.userpassword = userpassword;
        this.position = position;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserpayment() {
        return userpayment;
    }

    public void setUserpayment(String userpayment) {
        this.userpayment = userpayment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public User() {
    }
}
