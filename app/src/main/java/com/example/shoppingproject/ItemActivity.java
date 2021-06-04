package com.example.shoppingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shoppingproject.Adapter.FragmentPhoneAdapter;
import com.google.android.material.tabs.TabLayout;

public class ItemActivity extends AppCompatActivity {
    Button btback;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentPhoneAdapter fragmentPhoneAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        btback = findViewById(R.id.back);
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tablayout);
        fragmentPhoneAdapter=new FragmentPhoneAdapter(getSupportFragmentManager(), FragmentPhoneAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentPhoneAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Iphone").setIcon(R.drawable.ic_baseline_phone_iphone_24);
        tabLayout.getTabAt(1).setText("Saumsung").setIcon(R.drawable.ic_baseline_phone_android_24);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ItemActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}