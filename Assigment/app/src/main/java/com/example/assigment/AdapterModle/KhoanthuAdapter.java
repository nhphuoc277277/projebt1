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

import com.example.assigment.DAO.KhoanThuDao;
import com.example.assigment.DAO.LoaiThuDao;
import com.example.assigment.Modle.KhoanThu;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;

import java.util.ArrayList;

public class KhoanthuAdapter extends RecyclerView.Adapter<KhoanthuAdapter.KhoanthuviewHolder> {
    ArrayList<LoaiThu> mdatathu;
    Spinner_LoaiThu_Adapter adapterspinerthu;
    Context context;
    ArrayList<KhoanThu> mListKhoanThu;

    public KhoanthuAdapter(Context context, ArrayList<KhoanThu> mListKhoanThu) {
        this.context = context;
        this.mListKhoanThu = mListKhoanThu;
    }

    @NonNull
    @Override
    public KhoanthuviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khoanthu,parent,false);
        return new KhoanthuviewHolder(view);
    }

    public void setDataKT(ArrayList<KhoanThu> listKhoanThu){
        mListKhoanThu.clear();
        mListKhoanThu.addAll(listKhoanThu);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanthuviewHolder holder, int position) {
        KhoanThu khoanThu = mListKhoanThu.get(position);
        holder.tv_khoanthuname.setText(khoanThu.getTenKhoanThu());
        holder.tv_nguoigiao.setText(khoanThu.getNguoiGiao());
        holder.tv_sotienthu.setText(String.valueOf(khoanThu.getSoTienThu()));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.layout_dialog_thu,null);
                EditText ed_khoanthu = view.findViewById(R.id.ed_khoanthu);
                EditText ed_nguoigiao = view.findViewById(R.id.ed_nguoigiao);
                EditText ed_sotienthu = view.findViewById(R.id.ed_sotienthu);
                Spinner spinnerkT = view.findViewById(R.id.spinnerthu);
                mdatathu = (ArrayList<LoaiThu>) new LoaiThuDao(context).get();
                adapterspinerthu = new Spinner_LoaiThu_Adapter(context,mdatathu);
                spinnerkT.setAdapter(adapterspinerthu);
                Button btnkT = view.findViewById(R.id.btn_themKT);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btnkT.setText("Cập nhật");
                btnkT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idkhoanThu = khoanThu.getIdKhoanThu();
                        String khoanthu = ed_khoanthu.getText().toString();
                        String nguoigiao = ed_nguoigiao.getText().toString();
                        int sotienthu = Integer.parseInt(String.valueOf(ed_sotienthu.getText()));
                        String idloaithu = ((LoaiThu)spinnerkT.getSelectedItem()).getIdLoaiThu();
                        KhoanThu KT = new KhoanThu(idkhoanThu,khoanthu,nguoigiao,sotienthu,idloaithu);
                        KhoanThuDao dao = new KhoanThuDao(context);
                        dao.update(KT);
                        alertDialog.dismiss();
                        mListKhoanThu.clear();
                        mListKhoanThu.addAll(new KhoanThuDao(context).get());
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
                        textView.setText("Đã cập nhật: "+KT.getTenKhoanThu());
                        imageView.setImageResource(R.drawable.cn);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListKhoanThu==null){
            return 0;
        }else {
            return mListKhoanThu.size();
        }
    }

    public KhoanThu getItem(int position) {
        if(mListKhoanThu == null || position>=mListKhoanThu.size()){
            return null;
        }else {
            return mListKhoanThu.get(position);
        }
    }

    public static class KhoanthuviewHolder extends RecyclerView.ViewHolder{
        TextView tv_khoanthuname;
        TextView tv_nguoigiao;
        TextView tv_sotienthu;
        ImageView imageView;
        public KhoanthuviewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_khoanthuname = itemView.findViewById(R.id.tv_khoanthuname);
            this.tv_nguoigiao = itemView.findViewById(R.id.tv_nguoigiao);
            this.tv_sotienthu = itemView.findViewById(R.id.tv_sotienthu);
            this.imageView = itemView.findViewById(R.id.im_btnkhoanthu);
        }
    }
}
