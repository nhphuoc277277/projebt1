package com.example.assigment.ui.Chi;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.LabeledIntent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment.AdapterModle.KhoanchiAdapter;
import com.example.assigment.AdapterModle.LoaichiAdapter;
import com.example.assigment.DAO.KhoanChiDao;
import com.example.assigment.DAO.LoaiChiDao;
import com.example.assigment.DAO.LoaiThuDao;
import com.example.assigment.Modle.LoaiChi;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaichiFragment extends Fragment implements LoaichiAdapter.ClickListener {


    FloatingActionButton btnfloatingLC;
    RecyclerView recyclerView;
    ArrayList<LoaiChi> mListLoaiChi;
    LoaichiAdapter adapter;
    ArrayList mListKhoanChi;
    public static LoaichiFragment newInstance() {
        return new LoaichiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaichi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnfloatingLC = view.findViewById(R.id.btnfloatingLC);
        recyclerView = view.findViewById(R.id.recyclerViewloaiChi);

        mListLoaiChi = (ArrayList<LoaiChi>) (new LoaiChiDao(getContext())).get();
        adapter = new LoaichiAdapter(getContext(),mListLoaiChi,this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        btnfloatingLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LoaichiFragment.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_loaichi,null);
                Button btnthemLC = view.findViewById(R.id.btnthemLC);
                EditText ed_loaichi = view.findViewById(R.id.ed_loaichi);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btnthemLC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idloaichi = String.valueOf(System.currentTimeMillis());
                        String loaichi = ed_loaichi.getText().toString();
                        LoaiChi LC = new LoaiChi(idloaichi,loaichi);
                        LoaiChiDao dao = new LoaiChiDao(getContext());
                        dao.insert(LC);
                        alertDialog.dismiss();
                        mListLoaiChi = (ArrayList<LoaiChi>) (new LoaiChiDao(getContext())).get();
                        adapter.setDataLC(mListLoaiChi);
                        Toast toast = new Toast(getContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.layout_custom_toat,view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã thêm: "+LC.getLoaiChi());
                        imageView.setImageResource(R.drawable.pluss);
                    }
                });
            }
        });
//------------------------------------------------------------------------------------------------------------------------------
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                LoaiChi lc = adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Xóa: "+lc.getLoaiChi());
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiChiDao dao = new LoaiChiDao(getContext());
                        dao.delete(lc.getIdLoaiChi());
                        mListLoaiChi = (ArrayList<LoaiChi>) (new LoaiChiDao(getContext())).get();
                        adapter.setDataLC(mListLoaiChi);
                        //Toast-----------------------------------------------------------------
                        Toast toast = new Toast(getContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.layout_custom_toat,view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã xóa: "+lc.getLoaiChi());
                        imageView.setImageResource(R.drawable.pluss);
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListLoaiChi = (ArrayList<LoaiChi>) (new LoaiChiDao(getContext())).get();
                        adapter.setDataLC(mListLoaiChi);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(LoaiChi loaiChi) {
        mListKhoanChi = new ArrayList<>(new KhoanChiDao(requireContext()).getDataChi(loaiChi.getIdLoaiChi()));
        KhoanchiAdapter adapterKC = new KhoanchiAdapter(getContext(),mListKhoanChi);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LoaichiFragment.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_con_thuchi,null);
        RecyclerView rcl_thuchi = view.findViewById(R.id.rcl_thuchi);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcl_thuchi.setLayoutManager(manager);
        rcl_thuchi.setAdapter(adapterKC);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}