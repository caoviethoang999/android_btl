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
import com.example.shoppingproject.Adapter.PhoneAdapter;
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.Phone;
import com.example.shoppingproject.Service.ItemService;
import com.example.shoppingproject.Service.PhoneService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SamsungFragment extends Fragment {
    PhoneAdapter phoneAdapter;
    SearchView searchView;
    RecyclerView recyclerView;
    Button btrefresh;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView=view.findViewById(R.id.searchviewSamsung);
        recyclerView=view.findViewById(R.id.recylerviewSamsung);
        btrefresh=view.findViewById(R.id.refresh);
        phoneAdapter=new PhoneAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getPhoneList();
        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoneList();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PhoneService phoneService = APIClient.getClient().create(PhoneService.class);
                Call<List<Phone>> call = phoneService.searchPhone(newText);
                call.enqueue(new Callback<List<Phone>>() {
                    @Override
                    public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                        if(response.isSuccessful()){
                            List<Phone> list = (List<Phone>) response.body();
                            phoneAdapter.getAll(list);
                            recyclerView.setAdapter(phoneAdapter);
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_samsung, container, false);
    }
    public void getPhoneList() {
        PhoneService phoneService = APIClient.getClient().create(PhoneService.class);
        Call<List<Phone>> call = phoneService.getPhone();
        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                if (response.isSuccessful()) {
                    List<Phone> list = (List<Phone>) response.body();
                    phoneAdapter.getAll(list);
                    recyclerView.setAdapter(phoneAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}