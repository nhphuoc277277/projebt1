package com.example.assigment.ui.Thu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.AdapterModle.KhoanthuAdapter;
import com.example.assigment.AdapterModle.LoaithuAdapter;
import com.example.assigment.DAO.KhoanThuDao;
import com.example.assigment.DAO.LoaiThuDao;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaithuFragment extends Fragment implements LoaithuAdapter.ClickListener {

    FloatingActionButton floating;
    RecyclerView recyclerView;
    ArrayList<LoaiThu> mListLoaiThu;
    ArrayList mListKhoanThu;
    LoaithuAdapter adapter;
    Button btnthemLT;
    EditText ed_loaithu;

    public static LoaithuFragment newInstance() {
        return new LoaithuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaithu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floating = view.findViewById(R.id.btnfloatingThu);
        recyclerView = view.findViewById(R.id.recyclerViewloaithu);

        mListLoaiThu = (ArrayList<LoaiThu>) (new LoaiThuDao(getContext())).get();
        adapter = new LoaithuAdapter(getContext(), mListLoaiThu,this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

//------------------------------------------------------------------------------
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LoaithuFragment.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_loaithu, null);
                btnthemLT = view.findViewById(R.id.btnthemLT);
                ed_loaithu = view.findViewById(R.id.ed_loaithu);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btnthemLT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idloaiThu = String.valueOf(System.currentTimeMillis());
                        String loaiThu = ed_loaithu.getText().toString();
                        LoaiThu LT = new LoaiThu(idloaiThu, loaiThu);
                        LoaiThuDao dao = new LoaiThuDao(getContext());
                        dao.insert(LT);
                        alertDialog.dismiss();
                        mListLoaiThu = (ArrayList<LoaiThu>) (new LoaiThuDao(getContext())).get();
                        adapter.setDataLT(mListLoaiThu);
                        //Toast------------------------------------------------------------------------
                        Toast toast = new Toast(getContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.layout_custom_toat, view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã thêm: " + LT.getLoaiThu());
                        imageView.setImageResource(R.drawable.pluss);
                    }
                });
            }
        });
        //-------------------------------------------------------------------------------------------------------------------

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                LoaiThu LT = adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Xóa: " + LT.getLoaiThu());
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiThuDao dao = new LoaiThuDao(getContext());
                        dao.delete(LT.getIdLoaiThu());
                        mListLoaiThu = (ArrayList<LoaiThu>) (new LoaiThuDao(getContext())).get();
                        adapter.setDataLT(mListLoaiThu);

                        //Toast------------------------------------------------------------------------
                        Toast toast = new Toast(getContext());
                        LayoutInflater inflater = getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.layout_custom_toat, view.findViewById(R.id.layout_custom_toast));
                        TextView textView = view1.findViewById(R.id.tv_toat);
                        ImageView imageView = view1.findViewById(R.id.im_toast);
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                        textView.setText("Đã xóa: " + LT.getLoaiThu());
                        imageView.setImageResource(R.drawable.dlt);
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListLoaiThu = (ArrayList<LoaiThu>) (new LoaiThuDao(getContext())).get();
                        adapter.setDataLT(mListLoaiThu);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(LoaiThu loaiThu) {
            mListKhoanThu = new ArrayList<>(new KhoanThuDao(requireContext()).getDataThu(loaiThu.getIdLoaiThu()));
            KhoanthuAdapter adapterKT = new KhoanthuAdapter(getContext(),mListKhoanThu);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = LoaithuFragment.this.getLayoutInflater();
            View view = inflater.inflate(R.layout.list_con_thuchi,null);
            RecyclerView rcl_thuchi = view.findViewById(R.id.rcl_thuchi);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            rcl_thuchi.setLayoutManager(manager);
            rcl_thuchi.setAdapter(adapterKT);
            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
    }
}