package com.example.shoppingproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shoppingproject.Adapter.ItemAdapter;
import com.example.shoppingproject.Adapter.ItemAdminAdapter;
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Service.ItemService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IphoneFragment extends Fragment {
    ItemAdapter itemAdapter;
    SearchView searchView;
    RecyclerView recyclerView;
    Button btrefresh;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView=view.findViewById(R.id.searchviewIphone);
        recyclerView=view.findViewById(R.id.recylerviewIphone);
        btrefresh=view.findViewById(R.id.refresh);
        itemAdapter=new ItemAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getItemList();
        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemList();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ItemService itemService=APIClient.getClient().create(ItemService.class);
                Call<List<Item>> call = itemService.searchItem(newText);
                call.enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        if(response.isSuccessful()){
                            List<Item> list = (List<Item>) response.body();
                            itemAdapter.getAll(list);
                            recyclerView.setAdapter(itemAdapter);
                        }
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
                return true;
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iphone, container, false);
    }
    public void getItemList() {
        ItemService itemService = APIClient.getClient().create(ItemService.class);
        Call<List<Item>> call = itemService.getItem();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    List<Item> list = (List<Item>) response.body();
                    itemAdapter.getAll(list);
                    recyclerView.setAdapter(itemAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}