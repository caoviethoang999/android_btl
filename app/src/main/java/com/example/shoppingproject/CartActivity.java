package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingproject.Model.Cart;
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.Phone;

import java.io.InputStream;

public class CartActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private Button btnshopping;
    int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnshopping=findViewById(R.id.buttonContinueShopping);

        tableLayout=findViewById(R.id.tableLayoutProduct);
        btnshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,ItemActivity.class);
                startActivity(intent);
            }
        });
        addCart();
        createColums();
        fillData();
    }
    private void addCart(){
        try {
            Intent intent=getIntent();
            Item item= (Item) intent.getSerializableExtra("item");
            Phone phone= (Phone) intent.getSerializableExtra("phone");
            quantity = Integer.parseInt(intent.getStringExtra("quantityed"));
            CartProcess.insert(new Cart(item,phone,quantity));
        }catch (Exception e){
        }
    }
    private void createColums(){
        TableRow tableRow=new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView textViewName=new TextView(this);
        textViewName.setText("Name");
        textViewName.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewName.setPadding(5,5,5,0);
        tableRow.addView(textViewName);

        TextView textViewPhoto=new TextView(this);
        textViewPhoto.setText("Photo");
        textViewPhoto.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewPhoto.setPadding(5,5,5,0);
        tableRow.addView(textViewPhoto);

        TextView textViewPrice=new TextView(this);
        textViewPrice.setText("Price");
        textViewPrice.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewPrice.setPadding(5,5,5,0);
        tableRow.addView(textViewPrice);

        TextView textViewQuantity=new TextView(this);
        textViewQuantity.setText("Quantity");
        textViewQuantity.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewQuantity.setPadding(5,5,5,0);
        tableRow.addView(textViewQuantity);

        TextView textViewSubTotal=new TextView(this);
        textViewSubTotal.setText("SubTotal");
        textViewSubTotal.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewSubTotal.setPadding(5,5,5,0);
        tableRow.addView(textViewSubTotal);

        TextView textViewTotal=new TextView(this);
        textViewTotal.setText("Total");
        textViewTotal.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewTotal.setPadding(5,5,5,0);
        tableRow.addView(textViewTotal);

        tableLayout.addView(tableRow,new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        tableRow=new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        textViewName=new TextView(this);
        textViewName.setText("--------");
        textViewName.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewName.setPadding(5,5,5,0);
        tableRow.addView(textViewName);


        textViewPrice=new TextView(this);
        textViewPrice.setText("--------");
        textViewPrice.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewPrice.setPadding(5,5,5,0);
        tableRow.addView(textViewPrice);

        textViewPhoto=new TextView(this);
        textViewPhoto.setText("--------");
        textViewPhoto.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewPhoto.setPadding(5,5,5,0);
        tableRow.addView(textViewPhoto);

        textViewPhoto=new TextView(this);
        textViewPhoto.setText("--------");
        textViewPhoto.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        textViewPhoto.setPadding(5,5,5,0);
        tableRow.addView(textViewPhoto);

        tableLayout.addView(tableRow,new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }
    private void fillData(){
        try{
            for(Cart cart:CartProcess.contents()){
                TableRow tableRow=new TableRow(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));


                TextView textViewName=new TextView(this);
                textViewName.setText(cart.getItem().getItemname());
                textViewName.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
                textViewName.setPadding(5,5,5,0);
                tableRow.addView(textViewName);

                ImageView imageViewPhoto= new ImageView(this);
                Bitmap bitmap =new ImageRequestAsk().execute(cart.getItem().getPhoto()).get();
                imageViewPhoto.setImageBitmap(bitmap);
                tableRow.addView(imageViewPhoto);

                TextView textViewPrice=new TextView(this);
                textViewPrice.setText(String.valueOf((cart.getItem().getItemprice())));
                textViewPrice.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
                textViewPrice.setPadding(5,5,5,0);
                tableRow.addView(textViewPrice);

                TextView textViewQuantity=new TextView(this);
                textViewQuantity.setText(String.valueOf((cart.getQuantity())));
                textViewQuantity.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
                textViewQuantity.setPadding(5,5,5,0);
                tableRow.addView(textViewQuantity);

                TextView textViewSubTotal=new TextView(this);
                textViewSubTotal.setText(String.valueOf((cart.getItem().getItemprice())*cart.getQuantity()));
                textViewSubTotal.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
                textViewSubTotal.setPadding(5,5,5,0);
                tableRow.addView(textViewSubTotal);

                tableLayout.addView(tableRow,new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TableRow tableRowTotal=new TableRow(this);
                tableRowTotal.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView textViewTotalvalue=new TextView(this);
                textViewTotalvalue.setText(String.valueOf(CartProcess.total()));
                textViewTotalvalue.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
                textViewTotalvalue.setPadding(5,5,5,0);
                tableRow.addView(textViewTotalvalue);

                tableLayout.addView(tableRowTotal,new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
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