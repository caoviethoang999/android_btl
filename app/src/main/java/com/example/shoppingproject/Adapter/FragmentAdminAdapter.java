package com.example.shoppingproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shoppingproject.IphoneAdminFragment;
import com.example.shoppingproject.SamsungAdminFragment;

public class FragmentAdminAdapter extends FragmentStatePagerAdapter {
    public FragmentAdminAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new IphoneAdminFragment();
            case 1:return new SamsungAdminFragment();
        }
        return new IphoneAdminFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
