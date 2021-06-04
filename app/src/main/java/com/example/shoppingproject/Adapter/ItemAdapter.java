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
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    List<Item> list;
    public ItemAdapter() {
        list=new ArrayList<>();
    }
    public void getAll(List<Item> list){
        this.list=list;
    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.item_view,parent,false);
        return new ItemAdapter.ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Item item=list.get(position);
        holder.txtname.setText(item.getItemname());
        holder.txtprice.setText(String.valueOf(item.getItemprice()));
        holder.txtquantity.setText(String.valueOf(item.getItemquantity()));
        Bitmap photo= null;
        try {
            photo = new ItemAdapter.ImageRequestAsk().execute(item.getPhoto()).get();
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
                intent.putExtra("id",String.valueOf(item.getItemid()));
                intent.putExtra("name",item.getItemname());
                intent.putExtra("price",String.valueOf(item.getItemprice()));
                intent.putExtra("quantity",String.valueOf(item.getItemquantity()));
                intent.putExtra("photo",item.getPhoto());
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
