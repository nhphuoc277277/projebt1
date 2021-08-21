package com.example.assigment.AdapterModle;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.DAO.LoaiChiDao;
import com.example.assigment.Modle.LoaiChi;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;

import java.util.ArrayList;

public class LoaichiAdapter extends RecyclerView.Adapter<LoaichiAdapter.LoaichiviewHolder> {
    Context context;
    ArrayList<LoaiChi> mListLoaiChi;
    ClickListener clickListener;
    public LoaichiAdapter(Context context, ArrayList<LoaiChi> mListLoaiChi, ClickListener clickListener) {
        this.context = context;
        this.mListLoaiChi = mListLoaiChi;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public LoaichiviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaichi,parent,false);
        return new LoaichiviewHolder(view);
    }

    public void setDataLC(ArrayList<LoaiChi> ListLoaiChi){
        mListLoaiChi.clear();
        mListLoaiChi.addAll(ListLoaiChi);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull LoaichiviewHolder holder, int position) {
        LoaiChi loaiChi = mListLoaiChi.get(position);
        holder.tv_loaichiname.setText(loaiChi.getLoaiChi());
        holder.relativeLayout.setOnClickListener(v -> {
            clickListener.onItemClick(loaiChi);
        });
        holder.im_btnloaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.layout_dialog_loaichi,null);
                Button btn_themlc = view.findViewById(R.id.btnthemLC);
                EditText ed_loaichi = view.findViewById(R.id.ed_loaichi);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btn_themlc.setText("Cập nhật");
                btn_themlc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idloaichi = loaiChi.getIdLoaiChi();
                        String loaichi = ed_loaichi.getText().toString();
                        LoaiChi LC = new LoaiChi(idloaichi,loaichi);
                        LoaiChiDao dao = new LoaiChiDao(context);
                        dao.update(LC);
                        alertDialog.dismiss();
                        mListLoaiChi.clear();
                        mListLoaiChi.addAll(new LoaiChiDao(context).get());
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
                        textView.setText("Đã cập nhật: "+LC.getLoaiChi());
                        imageView.setImageResource(R.drawable.cn);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListLoaiChi.size();
    }

    public LoaiChi getItem(int position) {
        if(mListLoaiChi==null||position>=mListLoaiChi.size()){
            return null;
        }else {
            return mListLoaiChi.get(position);
        }
    }

    public static class LoaichiviewHolder extends RecyclerView.ViewHolder{
        ImageButton im_btnloaichi;
        TextView tv_loaichiname;
        RelativeLayout relativeLayout;
        public LoaichiviewHolder(@NonNull View itemView) {
            super(itemView);
            this.im_btnloaichi = itemView.findViewById(R.id.im_btnloaichi);
            this.tv_loaichiname = itemView.findViewById(R.id.tv_loaichiname);
            this.relativeLayout = itemView.findViewById(R.id.layout_item_LC);
        }
    }
    public interface ClickListener{
        void onItemClick(LoaiChi loaiChi);
    }
}
