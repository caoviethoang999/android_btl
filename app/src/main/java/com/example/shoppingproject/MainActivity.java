package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingproject.Model.User;
import com.example.shoppingproject.Service.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edusername,edpassword;
    Button btsignin,btregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edusername=findViewById(R.id.edusername);
        edpassword=findViewById(R.id.edpassword);
        btsignin=findViewById(R.id.btsignin);
        btregister=findViewById(R.id.btregister);
        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        btsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setUseraccount(edusername.getText().toString());
                user.setUserpassword(edpassword.getText().toString());
                UserService userService=APIClient.getClient().create(UserService.class);
                Call<User> call = userService.Login(user.getUseraccount(),user.getUserpassword());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            User u=response.body();
                            if(u.getPosition()==2) {
                                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                                startActivity(intent);
                            }else if(u.getPosition()==1){
                                Intent intent = new Intent(MainActivity.this, ItemAddActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });

            }
        });
    }
}