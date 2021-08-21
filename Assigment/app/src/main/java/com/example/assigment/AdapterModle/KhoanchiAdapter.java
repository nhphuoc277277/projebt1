package com.example.assigment.AdapterModle;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.DAO.KhoanChiDao;
import com.example.assigment.DAO.LoaiChiDao;
import com.example.assigment.Modle.KhoanChi;
import com.example.assigment.Modle.LoaiChi;
import com.example.assigment.R;

import java.util.ArrayList;

public class KhoanchiAdapter extends RecyclerView.Adapter<KhoanchiAdapter.KhoanchiviewHolder> {
    ArrayList<LoaiChi> datachi;
    Spinner_LoaiChi_Adapter adapterspinerchi;
    Context context;
    ArrayList<KhoanChi> mListKhoanChi;

    public KhoanchiAdapter(Context context, ArrayList<KhoanChi> mListKhoanChi) {
        this.context = context;
        this.mListKhoanChi = mListKhoanChi;
    }

    @NonNull
    @Override
    public KhoanchiviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khoanchi,parent,false);
        return new KhoanchiviewHolder(view);
    }

    public void setDataKC(ArrayList<KhoanChi> listKhoanChi){
        mListKhoanChi.clear();
        mListKhoanChi.addAll(listKhoanChi);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanchiviewHolder holder, int position) {
        KhoanChi khoanChi = mListKhoanChi.get(position);
        holder.tv_khoanchiname.setText(khoanChi.getTenKhoanChi());
        holder.tv_nguoinhan.setText(khoanChi.getNguoiNhan());
        holder.tv_sotienchi.setText(String.valueOf(khoanChi.getSoTienChi()));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.layout_dialog_chi,null);
                EditText ed_khoanchi = view.findViewById(R.id.ed_khoanchi);
                EditText ed_nguoinhan = view.findViewById(R.id.ed_nguoinhan);
                EditText ed_sotienchi = view.findViewById(R.id.ed_sotienchi);
                Spinner spinner = view.findViewById(R.id.spinnerchi);
                datachi = (ArrayList<LoaiChi>) new LoaiChiDao(context).get();
                adapterspinerchi = new Spinner_LoaiChi_Adapter(context,datachi);
                spinner.setAdapter(adapterspinerchi);
                Button btncapnhat = view.findViewById(R.id.btn_themKC);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btncapnhat.setText("Cập nhật");
                btncapnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idkhoanchi = khoanChi.getIdKhoanChi();
                        String khoanchi = ed_khoanchi.getText().toString();
                        String nguoinhan = ed_nguoinhan.getText().toString();
                        int sotienchi = Integer.parseInt(ed_sotienchi.getText().toString());
                        String idloaichi = ((LoaiChi)spinner.getSelectedItem()).getIdLoaiChi();
                        KhoanChi KC = new KhoanChi(idkhoanchi,khoanchi,nguoinhan,sotienchi,idloaichi);
                        KhoanChiDao dao = new KhoanChiDao(context);
                        dao.update(KC);
                        alertDialog.dismiss();
                        mListKhoanChi.clear();
                        mListKhoanChi.addAll(new KhoanChiDao(context).get());
                        notifyDataSetChanged();
                        //Toast------------------------------------------------------------------------
                        Toast toast = new Toast(context);
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View view1 = inflater.inflate(R.layout.layout_custom_toat,view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã cập nhật: "+KC.getTenKhoanChi());
                        imageView.setImageResource(R.drawable.cn);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListKhoanChi.size();
    }

    public KhoanChi getItem(int position) {
        if (mListKhoanChi==null||position>=mListKhoanChi.size()){
            return null;
        }else {
            return mListKhoanChi.get(position);
        }
    }

    public static class KhoanchiviewHolder extends RecyclerView.ViewHolder {
        TextView tv_khoanchiname;
        TextView tv_nguoinhan;
        TextView tv_sotienchi;
        ImageView imageView;
        public KhoanchiviewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_khoanchiname = itemView.findViewById(R.id.tv_khoanchiname);
            this.tv_nguoinhan = itemView.findViewById(R.id.tv_nguoinhan);
            this.tv_sotienchi = itemView.findViewById(R.id.tv_sotienchi);
            this.imageView = itemView.findViewById(R.id.im_btnkhoanchi);
        }
    }
}
