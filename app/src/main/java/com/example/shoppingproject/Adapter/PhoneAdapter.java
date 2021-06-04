package com.example.shoppingproject.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingproject.CartPhoneActivity;
import com.example.shoppingproject.ItemUpdateActivity;
import com.example.shoppingproject.Model.Phone;
import com.example.shoppingproject.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ItemViewHolder>{
    List<Phone> list;
    public PhoneAdapter() {
        list=new ArrayList<>();
    }
    public void getAll(List<Phone> list){
        this.list=list;
    }
    @NonNull
    @Override
    public PhoneAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.item_view,parent,false);
        return new PhoneAdapter.ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.ItemViewHolder holder, int position) {
        Phone phone=list.get(position);
        holder.txtname.setText(phone.getPhonename());
        holder.txtprice.setText(String.valueOf(phone.getPhoneprice()));
        holder.txtquantity.setText(String.valueOf(phone.getPhonequantity()));
        Bitmap photo= null;
        try {
            photo = new PhoneAdapter.ImageRequestAsk().execute(phone.getPhoto()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.imageView.setImageBitmap(photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), CartPhoneActivity.class);
                intent.putExtra("id",String.valueOf(phone.getPhoneid()));
                intent.putExtra("name",phone.getPhonename());
                intent.putExtra("price",String.valueOf(phone.getPhoneprice()));
                intent.putExtra("quantity",String.valueOf(phone.getPhonequantity()));
                intent.putExtra("photo",phone.getPhoto());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtprice,txtquantity;
        ImageView imageView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.txtname);
            txtprice=itemView.findViewById(R.id.txtprice);
            txtquantity=itemView.findViewById(R.id.txtquantity);
            imageView=itemView.findViewById(R.id.imgItem);
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
