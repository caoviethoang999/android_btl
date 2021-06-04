package com.example.shoppingproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingproject.Adapter.ItemAdminAdapter;
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Service.ItemService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IphoneAdminFragment extends Fragment {
    EditText editemname,editemprice,editemquantity,editemphoto;
    Button btadd;
    Item item;
    ItemAdminAdapter itemAdminAdapter;
    RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editemname=view.findViewById(R.id.editemname);
        editemprice=view.findViewById(R.id.editemprice);
        editemquantity=view.findViewById(R.id.editemquantity);
        editemphoto=view.findViewById(R.id.edphoto);
        itemAdminAdapter=new ItemAdminAdapter();
        recyclerView=view.findViewById(R.id.recylerviewAdminIPhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        btadd=view.findViewById(R.id.btadd);
        getItemList();
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
                getItemList();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iphone_admin, container, false);
    }
    public void addItem(){
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
        ItemService itemService=APIClient.getClient().create(ItemService.class);
        Call<Item> call = itemService.addItem(item.getItemname(),String.valueOf(item.getItemprice()),String.valueOf(item.getItemquantity()),item.getPhoto());
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Item created successfully!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void getItemList(){
        ItemService itemService=APIClient.getClient().create(ItemService.class);
        Call<List<Item>> call = itemService.getItem();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()){
                    List<Item> list = (List<Item>) response.body();
                    itemAdminAdapter.getAll(list);
                    recyclerView.setAdapter(itemAdminAdapter);
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}