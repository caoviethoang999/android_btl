package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.Phone;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class CartPhoneActivity extends AppCompatActivity {
    TextView textPrice,textName;
    ImageButton btnaddcart;
    EditText edtquantity;
    String name,quantity,price,photo1;
    ImageView imgItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_phone);
        textPrice=findViewById(R.id.textPrice);
        textName=findViewById(R.id.textName);
        edtquantity=findViewById(R.id.edtquantity);
        btnaddcart=findViewById(R.id.imageButtonBuy);
        imgItem=findViewById(R.id.imagePhoto);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        price = i.getStringExtra("price");
        quantity=i.getStringExtra("quantity");
        photo1 = getIntent().getStringExtra("photo");
        Bitmap photo= null;
        try {
            photo = new ImageRequestAsk().execute(photo1).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imgItem.setImageBitmap(photo);
        Item item=new Item();
        Phone phone=new Phone();
        textPrice.setText(price);
        textName.setText(name);
        item.setItemname(name);
        item.setItemprice(Double.parseDouble(price));
        item.setItemquantity(Integer.parseInt(quantity));
        item.setPhoto(photo1);
        item.getItemname();
        item.getItemprice();
        item.getItemquantity();
        item.getPhoto();
        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityed=edtquantity.getText().toString();
                Intent intent=new Intent(CartPhoneActivity.this,CartActivity.class);
                intent.putExtra("item",item);
                intent.putExtra("phone",phone);
                intent.putExtra("quantityed",String.valueOf(quantityed));
                startActivity(intent);
            }
        });
    }
    public class ImageRequestAsk extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                InputStream inputStream =new java.net.URL(params[0]).openStream();
                return BitmapFactory.decodeStream(inputStream);
            }catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
}