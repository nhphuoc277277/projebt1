package com.example.assigment.AdapterModle;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;

import java.util.ArrayList;

public class Spinner_LoaiThu_Adapter extends BaseAdapter {
     Context context;
     ArrayList<LoaiThu> dataThu;

    public Spinner_LoaiThu_Adapter(Context context, ArrayList<LoaiThu> dataThu) {
        this.context = context;
        this.dataThu = dataThu;
    }

    @Override
    public int getCount() {
        return dataThu.size();
    }

    @Override
    public Object getItem(int position) {
        return dataThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView==null){
            view = View.inflate(parent.getContext(),R.layout.item_spinner_thu,null);
        }
        LoaiThu loaiThu = (LoaiThu) getItem(position);
        TextView textView = view.findViewById(R.id.tv_spinThu);
        textView.setText(loaiThu.getLoaiThu());
        return view;
    }
}