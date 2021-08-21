package com.example.assigment.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.assigment.R;

import java.util.ArrayList;
import java.util.Random;

public class RateFagment extends Fragment {
    ConstraintLayout constraintLayout;
    ArrayList<Integer> arrayimg;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment,container,false);
        constraintLayout = view.findViewById(R.id.constraintlayou);
        arrayimg = new ArrayList<>();
        arrayimg.add(R.drawable.banner1);
        arrayimg.add(R.drawable.banner2);
        arrayimg.add(R.drawable.banner3);
        arrayimg.add(R.drawable.br4);
        Random random = new Random();
        int vitri = random.nextInt(arrayimg.size());
        constraintLayout.setBackgroundResource(arrayimg.get(vitri));

        return view;
    }
}
