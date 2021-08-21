package com.example.assigment.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assigment.ui.Chi.KhoanchiFragment;
import com.example.assigment.ui.Chi.LoaichiFragment;
import com.example.assigment.ui.Thu.KhoanthuFragment;
import com.example.assigment.ui.Thu.LoaithuFragment;

public class ChiviewpagerAdapter extends FragmentStateAdapter {
    public ChiviewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = LoaichiFragment.newInstance();
        }else {
            fragment = KhoanchiFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
