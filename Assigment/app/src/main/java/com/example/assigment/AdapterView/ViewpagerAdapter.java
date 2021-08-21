package com.example.assigment.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.assigment.ui.home.RateFagment;
import com.example.assigment.ui.home.MusicFagment;
import com.example.assigment.ui.home.MyfavoriteFragment;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {


    public ViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RateFagment();
            case 1:
                return new MyfavoriteFragment();
            case 2:
                return new MusicFagment();
            default:
                return new RateFagment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
