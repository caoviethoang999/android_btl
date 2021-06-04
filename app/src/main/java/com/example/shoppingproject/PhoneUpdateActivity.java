package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.Phone;
import com.example.shoppingproject.Service.ItemService;
import com.example.shoppingproject.Service.PhoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneUpdateActivity extends AppCompatActivity {
    EditText editemname,editemprice,editemquantity,editemphoto;
    Button btupdate,btdelete;
    Phone phone;
    String id,name,price,quantity,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_update);
        editemname=findViewById(R.id.editemname);
        editemprice=findViewById(R.id.editemprice);
        editemquantity=findViewById(R.id.editemquantity);
        editemphoto=findViewById(R.id.edphoto);
        btupdate=findViewById(R.id.btupdate);
        btdelete=findViewById(R.id.btDelete);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        price=intent.getStringExtra("price");
        quantity=intent.getStringExtra("quantity");
        photo=intent.getStringExtra("photo");
        editemname.setText(name);
        editemprice.setText(price);
        editemquantity.setText(quantity);
        editemphoto.setText(photo);
        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePhone();
            }
        });
        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhone();
            }
        });
    }
    public void updatePhone(){
        PhoneService phoneService=APIClient.getClient().create(PhoneService.class);
        phone=new Phone();
        if(editemname.getText().toString().isEmpty()){
            editemname.setError("Not blank");
        }else{
            phone.setPhonename(editemname.getText().toString());
        }
        if(editemprice.getText().toString().isEmpty()){
            editemprice.setError("Not blank");
        }else{
            phone.setPhoneprice(Double.parseDouble(editemprice.getText().toString()));
        }
        if(editemquantity.getText().toString().isEmpty()){
            editemquantity.setError("Not blank");
        }else{
            phone.setPhonequantity(Integer.parseInt(editemquantity.getText().toString()));
        }
        if(editemphoto.getText().toString().isEmpty()){
            editemphoto.setError("Not blank");
        }else{
            phone.setPhoto(editemphoto.getText().toString());
        }
        Call<Phone> call = phoneService.updatePhone(Integer.parseInt(id),phone.getPhonename(),phone.getPhoneprice(),phone.getPhonequantity(),phone.getPhoto());
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PhoneUpdateActivity.this, "Item updated", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(PhoneUpdateActivity.this,ItemAddActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void deletePhone(){
        PhoneService phoneService=APIClient.getClient().create(PhoneService.class);
        phone=new Phone();
        Call<Phone> call = phoneService.deletePhone(Integer.parseInt(id));
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PhoneUpdateActivity.this, "Item deleted", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(PhoneUpdateActivity.this,ItemAddActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}