package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingproject.Model.User;
import com.example.shoppingproject.Service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    EditText edusername,eduseradd,eduserpayment,eduseremail,edaccount,edpass;
    Button btregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        edusername=findViewById(R.id.edusername);
        eduseradd=findViewById(R.id.eduseradd);
        eduserpayment=findViewById(R.id.eduserpayment);
        eduseremail=findViewById(R.id.eduseremail);
        edaccount=findViewById(R.id.edaccount);
        edpass=findViewById(R.id.edpassword);
        btregister=findViewById(R.id.btregister);
        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }
    public void addUser(){
        User u=new User();
        if(eduserpayment.getText().toString().equalsIgnoreCase("paypal")) {
            u.setUserpayment(eduserpayment.getText().toString());
        }else {
            Toast.makeText(UserActivity.this, "We only accept paypal", Toast.LENGTH_LONG).show();
        }
        u.setPosition(2);
        if(edusername.getText().toString().isEmpty()){
            edusername.setError("Not blank");
        }else{
            u.setUsername(edusername.getText().toString());
        }
        if(eduseradd.getText().toString().isEmpty()){
            eduseradd.setError("Not blank");
        }else{
            u.setUseraddress(eduseradd.getText().toString());
        }
        if(eduseremail.getText().toString().isEmpty()){
            eduseremail.setError("Not blank");
        }else{
            u.setEmail(eduseremail.getText().toString());
        }
        if(edaccount.getText().toString().isEmpty()){
            edaccount.setError("Not blank");
        }else{
            u.setUseraccount(edaccount.getText().toString());
        }
        if(edpass.getText().toString().isEmpty()){
            edpass.setError("Not blank");
        }else{
            u.setUserpassword(edpass.getText().toString());
        }
        UserService userService=APIClient.getClient().create(UserService.class);
        Call<User> call = userService.addUser(u.getUsername(),u.getUseraddress(),u.getUserpayment(),u.getEmail(),u.getUseraccount(),u.getUserpassword(),u.getPosition());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "User created successfully!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}