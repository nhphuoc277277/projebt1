package com.example.assigment.ui.Chi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assigment.AdapterView.ChiviewpagerAdapter;
import com.example.assigment.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiFragment extends Fragment {
    private ViewPager2 mViewpagerChi;
    private TabLayout mTabLayoutChi;


    public ChiFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChiFragment newInstance(String param1, String param2) {
        ChiFragment fragment = new ChiFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewpagerChi = view.findViewById(R.id.viewpagerChi);
        mTabLayoutChi = view.findViewById(R.id.tabLayout);

        ChiviewpagerAdapter adapter = new ChiviewpagerAdapter(getActivity());
        mViewpagerChi.setAdapter(adapter);

        new TabLayoutMediator(mTabLayoutChi, mViewpagerChi, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0){
                    tab.setIcon(R.drawable.mnico_32);
                    tab.setText("Loại chi");
                }else {
                    tab.setIcon(R.drawable.mnico_32);
                    tab.setText("Khoản chi");
                }
            }
        }).attach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }
}