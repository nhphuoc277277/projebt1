package com.example.assigment.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assigment.ui.Thu.KhoanthuFragment;
import com.example.assigment.ui.Thu.LoaithuFragment;
import com.example.assigment.ui.Thu.ThuFragment;

public class ThuviewpagerAdapter extends FragmentStateAdapter {
    public ThuviewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = LoaithuFragment.newInstance();
        }else {
            fragment = KhoanthuFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
