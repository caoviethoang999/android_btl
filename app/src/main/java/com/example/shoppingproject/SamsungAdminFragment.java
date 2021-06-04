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
import com.example.shoppingproject.Adapter.PhoneAdminAdapter;
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Model.Phone;
import com.example.shoppingproject.Service.ItemService;
import com.example.shoppingproject.Service.PhoneService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SamsungAdminFragment extends Fragment {
    EditText editemname,editemprice,editemquantity,editemphoto;
    Button btadd;
    Phone phone;
    PhoneAdminAdapter phoneAdminAdapter;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editemname=view.findViewById(R.id.editemname);
        editemprice=view.findViewById(R.id.editemprice);
        editemquantity=view.findViewById(R.id.editemquantity);
        editemphoto=view.findViewById(R.id.edphoto);
        btadd=view.findViewById(R.id.btadd);
        recyclerView=view.findViewById(R.id.recylerviewAdmimSamsung);
        phoneAdminAdapter=new PhoneAdminAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getPhoneList();
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhone();
                getPhoneList();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_samsung_admin, container, false);
    }
    public void addPhone(){
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
        PhoneService phoneService=APIClient.getClient().create(PhoneService.class);
        Call<Phone> call = phoneService.addPhone(phone.getPhonename(),String.valueOf(phone.getPhoneprice()),String.valueOf(phone.getPhonequantity()),phone.getPhoto());
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Item created successfully!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void getPhoneList(){
        PhoneService phoneService=APIClient.getClient().create(PhoneService.class);
        Call<List<Phone>> call = phoneService.getPhone();
        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                if(response.isSuccessful()){
                    List<Phone> list = (List<Phone>) response.body();
                    phoneAdminAdapter.getAll(list);
                    recyclerView.setAdapter(phoneAdminAdapter);
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}