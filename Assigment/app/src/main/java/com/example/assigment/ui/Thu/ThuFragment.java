package com.example.assigment.ui.Thu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assigment.AdapterView.ThuviewpagerAdapter;
import com.example.assigment.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThuFragment extends Fragment {
    private ViewPager2 mViewPagerThu;
    private TabLayout mTabLayoutThu;

    public ThuFragment() {
        // Required empty public constructor
    }


    public static ThuFragment newInstance(String param1, String param2) {
        ThuFragment fragment = new ThuFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPagerThu = view.findViewById(R.id.viewpagerThu);
        mTabLayoutThu = view.findViewById(R.id.tabLayout2);

        ThuviewpagerAdapter adapter = new ThuviewpagerAdapter(getActivity());
        mViewPagerThu.setAdapter(adapter);

        new TabLayoutMediator(mTabLayoutThu, mViewPagerThu, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setIcon(R.drawable.money_32);
                    tab.setText("Loại thu");
                }else {
                    tab.setIcon(R.drawable.money_32);
                    tab.setText("Khoản thu");
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
        return inflater.inflate(R.layout.fragment_thu, container, false);
    }
}