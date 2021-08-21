package com.example.assigment.ui.Thu;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment.AdapterModle.KhoanthuAdapter;
import com.example.assigment.AdapterModle.Spinner_LoaiThu_Adapter;
import com.example.assigment.DAO.KhoanThuDao;
import com.example.assigment.DAO.LoaiThuDao;
import com.example.assigment.Modle.KhoanThu;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KhoanthuFragment extends Fragment {

    RecyclerView recyclerView;
    KhoanthuAdapter adapterKhoanThu;
    ArrayList<KhoanThu> mlistKhoanThu;
    FloatingActionButton floating;
    Spinner spinnerKT;
    Spinner_LoaiThu_Adapter adapterSpinnerThu;
    ArrayList<LoaiThu> mDatathu;

    public static KhoanthuFragment newInstance() {
        return new KhoanthuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoanthu_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floating = view.findViewById(R.id.btn_floatingKT);
        recyclerView = view.findViewById(R.id.recyclerViewkhoanthu);

        mlistKhoanThu = (ArrayList<KhoanThu>) (new KhoanThuDao(getContext())).get();
        adapterKhoanThu = new KhoanthuAdapter(getContext(),mlistKhoanThu );
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterKhoanThu);

        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_thu,null);
                EditText ed_khoanthu = view.findViewById(R.id.ed_khoanthu);
                EditText ed_nguoigiao = view.findViewById(R.id.ed_nguoigiao);
                EditText ed_sotienthu = view.findViewById(R.id.ed_sotienthu);
                spinnerKT = view.findViewById(R.id.spinnerthu);
                mDatathu = (ArrayList<LoaiThu>) (new LoaiThuDao(getContext())).get();
                adapterSpinnerThu = new Spinner_LoaiThu_Adapter(getContext(),mDatathu);
                spinnerKT.setAdapter(adapterSpinnerThu);
                Button btn_them = view.findViewById(R.id.btn_themKT);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idthu = String.valueOf(System.currentTimeMillis());
                        String khoanThu = ed_khoanthu.getText().toString();
                        String nguoiGiao = ed_nguoigiao.getText().toString();
                        int soTienThu = Integer.parseInt(ed_sotienthu.getText().toString());
                        String idLoaiThu = ((LoaiThu)spinnerKT.getSelectedItem()).getIdLoaiThu();
                        KhoanThu KT = new KhoanThu(idthu,khoanThu,nguoiGiao,soTienThu,idLoaiThu);
                        KhoanThuDao dao = new KhoanThuDao(getContext());
                        dao.insert(KT);
                        alertDialog.dismiss();
                        mlistKhoanThu = (ArrayList<KhoanThu>) (new KhoanThuDao(getContext())).get();
                        adapterKhoanThu.setDataKT(mlistKhoanThu);
                        //Toast------------------------------------------------------------------------
                        Toast toast = new Toast(getContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.layout_custom_toat,view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã thêm: "+KT.getTenKhoanThu());
                        imageView.setImageResource(R.drawable.pluss);
                    }
                });
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                KhoanThu KT = adapterKhoanThu.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Xóa: "+ KT.getTenKhoanThu());
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KhoanThuDao dao = new KhoanThuDao(getContext());
                        dao.delete(KT.getIdKhoanThu());
                        mlistKhoanThu = (ArrayList<KhoanThu>) (new KhoanThuDao(getContext())).get();
                        adapterKhoanThu.setDataKT(mlistKhoanThu);

                        //Toast------------------------------------------------------------------------
                        Toast toast = new Toast(getContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.layout_custom_toat,view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã xóa: "+KT.getTenKhoanThu());
                        imageView.setImageResource(R.drawable.dlt);
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mlistKhoanThu = (ArrayList<KhoanThu>) (new KhoanThuDao(getContext())).get();
                        adapterKhoanThu.setDataKT(mlistKhoanThu);
                    }
                });
              AlertDialog alertDialog = builder.create();
              alertDialog.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}