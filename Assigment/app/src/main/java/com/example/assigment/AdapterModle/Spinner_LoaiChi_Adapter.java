package com.example.assigment.AdapterModle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.Modle.LoaiChi;
import com.example.assigment.R;

import java.util.ArrayList;

public class Spinner_LoaiChi_Adapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiChi> datachi;

    public Spinner_LoaiChi_Adapter(Context context, ArrayList<LoaiChi> datachi) {
        this.context = context;
        this.datachi = datachi;
    }

    @Override
    public int getCount() {
        return datachi.size();
    }

    @Override
    public Object getItem(int position) {
        return datachi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            view = View.inflate(parent.getContext(),R.layout.item_spinner_chi,null);
        }
        LoaiChi loaiChi = (LoaiChi) getItem(position);
        TextView textView = view.findViewById(R.id.tv_spinChi);
        textView.setText(loaiChi.getLoaiChi());
        return view;
    }
}
