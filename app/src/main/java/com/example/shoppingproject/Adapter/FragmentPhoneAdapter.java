package com.example.shoppingproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shoppingproject.IphoneFragment;
import com.example.shoppingproject.SamsungFragment;

public class FragmentPhoneAdapter extends FragmentStatePagerAdapter {
    public FragmentPhoneAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new IphoneFragment();
            case 1: return new SamsungFragment();
        }
        return new IphoneFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
