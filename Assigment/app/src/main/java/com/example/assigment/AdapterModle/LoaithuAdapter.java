package com.example.assigment.AdapterModle;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.DAO.KhoanThuDao;
import com.example.assigment.DAO.LoaiThuDao;
import com.example.assigment.Modle.KhoanThu;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;
import com.example.assigment.ui.Thu.LoaithuFragment;

import java.util.ArrayList;

import static com.example.assigment.ui.Thu.LoaithuFragment.*;

public class LoaithuAdapter extends RecyclerView.Adapter<LoaithuAdapter.LoaithuviewHolder> {

    Context context;
    ArrayList<LoaiThu> mListLoaiThu;
    ClickListener clickListener;


    public LoaithuAdapter(Context context, ArrayList<LoaiThu> mListLoaiThu,ClickListener clickListener) {
        this.context = context;
        this.mListLoaiThu = mListLoaiThu;
        this.clickListener = clickListener;
    }

    public void setDataLT(ArrayList<LoaiThu> listLoaiThu){
        mListLoaiThu.clear();
        mListLoaiThu.addAll(listLoaiThu);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaithuviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaithu,parent,false);
        return new LoaithuviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaithuviewHolder holder, int position) {
        LoaiThu loaiThu = mListLoaiThu.get(position);
        holder.tv_loaithuname.setText(loaiThu.getLoaiThu());
        holder.relativeLayout.setOnClickListener(v->{
            clickListener.onItemClick(loaiThu);
        });
        holder.im_btnloaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.layout_dialog_loaithu,null);
                Button btnthemLT = view.findViewById(R.id.btnthemLT);
                EditText ed_loaithu = view.findViewById(R.id.ed_loaithu);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btnthemLT.setText("Cập nhật");
                btnthemLT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idloaiThu = loaiThu.getIdLoaiThu();
                        String loaiThu = ed_loaithu.getText().toString();
                        LoaiThu LT = new LoaiThu(idloaiThu,loaiThu);
                        LoaiThuDao dao = new LoaiThuDao(context);
                        dao.update(LT);
                        alertDialog.dismiss();
                        mListLoaiThu.clear();
                        mListLoaiThu.addAll(new LoaiThuDao(context).get());
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
                        textView.setText("Đã cập nhật: "+LT.getLoaiThu());
                        imageView.setImageResource(R.drawable.cn);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListLoaiThu == null){
            return 0;
        }else {
            return mListLoaiThu.size();
        }
    }

    public LoaiThu getItem(int position) {
        if(mListLoaiThu==null||position>=mListLoaiThu.size()){
            return null;
        }else {
            return mListLoaiThu.get(position);
        }
    }


    public static class LoaithuviewHolder extends RecyclerView.ViewHolder{
        TextView tv_loaithuname;
        ImageButton im_btnloaithu;
        RelativeLayout relativeLayout;
        public LoaithuviewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_loaithuname = itemView.findViewById(R.id.tv_loaithuname);
            this.im_btnloaithu = itemView.findViewById(R.id.im_btnloaithu);
            this.relativeLayout = itemView.findViewById(R.id.layout_item_LT);
        }
    }


    public interface ClickListener{
        void onItemClick(LoaiThu loaiThu);
    }
}
