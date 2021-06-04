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
import com.example.shoppingproject.Service.ItemService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemUpdateActivity extends AppCompatActivity {
    EditText editemname,editemprice,editemquantity,editemphoto;
    Button btupdate,btdelete;
    Item item;
    String id,name,price,quantity,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);
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
                updateItem();
            }
        });
        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });
    }
    public void updateItem(){
        ItemService itemService=APIClient.getClient().create(ItemService.class);
        item=new Item();
        if(editemname.getText().toString().isEmpty()){
            editemname.setError("Not blank");
        }else{
            item.setItemname(editemname.getText().toString());
        }
        if(editemprice.getText().toString().isEmpty()){
            editemprice.setError("Not blank");
        }else{
            item.setItemprice(Double.parseDouble(editemprice.getText().toString()));
        }
        if(editemquantity.getText().toString().isEmpty()){
            editemquantity.setError("Not blank");
        }else{
            item.setItemquantity(Integer.parseInt(editemquantity.getText().toString()));
        }
        if(editemphoto.getText().toString().isEmpty()){
            editemphoto.setError("Not blank");
        }else{
            item.setPhoto(editemphoto.getText().toString());
        }
        Call<Item> call = itemService.updateItem(Integer.parseInt(id),item.getItemname(),item.getItemprice(),item.getItemquantity(),item.getPhoto());
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ItemUpdateActivity.this, "Item updated", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ItemUpdateActivity.this,ItemAddActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void deleteItem(){
        ItemService itemService=APIClient.getClient().create(ItemService.class);
        item=new Item();
        Call<Item> call = itemService.deleteItem(Integer.parseInt(id));
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ItemUpdateActivity.this, "Item deleted", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ItemUpdateActivity.this,ItemAddActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}