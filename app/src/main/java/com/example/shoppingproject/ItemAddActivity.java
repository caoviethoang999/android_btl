package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingproject.Adapter.FragmentAdminAdapter;
import com.example.shoppingproject.Adapter.ItemAdminAdapter;
import com.example.shoppingproject.Model.Item;
import com.example.shoppingproject.Service.ItemService;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAddActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdminAdapter fragmentAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);
        tabLayout = findViewById(R.id.tablayoutadmin);
        viewPager = findViewById(R.id.viewPagerAdmin);
        fragmentAdminAdapter = new FragmentAdminAdapter(getSupportFragmentManager(), FragmentAdminAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentAdminAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Iphone").setIcon(R.drawable.ic_baseline_phone_iphone_24);
        tabLayout.getTabAt(1).setText("Saumsung").setIcon(R.drawable.ic_baseline_phone_android_24);
    }
}