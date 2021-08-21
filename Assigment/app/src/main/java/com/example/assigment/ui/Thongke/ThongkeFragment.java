package com.example.assigment.ui.Thongke;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assigment.DAO.KhoanChiDao;
import com.example.assigment.DAO.KhoanThuDao;
import com.example.assigment.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongkeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongkeFragment extends Fragment {


    public ThongkeFragment() {
        // Required empty public constructor
    }

    public static ThongkeFragment newInstance(String param1, String param2) {
        ThongkeFragment fragment = new ThongkeFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PieChart pieChart = view.findViewById(R.id.piechart);
        TextView tv_tongthu = view.findViewById(R.id.tv_tongthu);
        TextView tv_tongchi = view.findViewById(R.id.tv_tongchi);

        ArrayList<PieEntry> entries = new ArrayList<>();

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueLineColor(Color.BLACK);
        pieDataSet.setValueTextSize(20);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setCenterText("Thống kê");
        pieChart.setCenterTextSize(25);
        pieChart.animate();

        Legend legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setTextSize(20);
        legend.setDrawInside(false);
        legend.setEnabled(true);


        float tongThu = (new KhoanThuDao(getContext())).tongThu();
        float tongChi = (new KhoanChiDao(getContext())).tongChi();

         if(tongThu != 0.0){
             entries.add(new PieEntry(tongThu,"Thu"));
             tv_tongthu.setText("Tổng thu: "+tongThu);
         }
        if(tongChi != 0.0){
            entries.add(new PieEntry(tongChi,"Chi"));
            tv_tongchi.setText("Tổng chi: "+tongChi);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thongke, container, false);
    }
}