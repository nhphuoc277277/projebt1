package com.example.assigment.ui.Chi;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment.AdapterModle.KhoanchiAdapter;
import com.example.assigment.AdapterModle.Spinner_LoaiChi_Adapter;
import com.example.assigment.DAO.KhoanChiDao;
import com.example.assigment.DAO.KhoanThuDao;
import com.example.assigment.DAO.LoaiChiDao;
import com.example.assigment.Modle.KhoanChi;
import com.example.assigment.Modle.KhoanThu;
import com.example.assigment.Modle.LoaiChi;
import com.example.assigment.Modle.LoaiThu;
import com.example.assigment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KhoanchiFragment extends Fragment {

    FloatingActionButton btn_floatingchi;
    RecyclerView recyclerView;
    ArrayList<KhoanChi> mListkhoanchi;
    KhoanchiAdapter adapterKC;
    ArrayList<LoaiChi> mdatachi;
    Spinner_LoaiChi_Adapter adapterspnerchi;

    public static KhoanchiFragment newInstance() {
        return new KhoanchiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoanchi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_floatingchi = view.findViewById(R.id.btn_floatingKC);
        recyclerView = view.findViewById(R.id.recyclerViewkhoanchi);

        mListkhoanchi = (ArrayList<KhoanChi>) (new KhoanChiDao(getContext())).get();
        adapterKC = new KhoanchiAdapter(getContext(),mListkhoanchi);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterKC);

        btn_floatingchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_chi,null);
                EditText ed_khoanchi = view.findViewById(R.id.ed_khoanchi);
                EditText ed_nguoinhan = view.findViewById(R.id.ed_nguoinhan);
                EditText ed_sotienchi = view.findViewById(R.id.ed_sotienchi);
                Spinner spinner = view.findViewById(R.id.spinnerchi);
                mdatachi = (ArrayList<LoaiChi>) new LoaiChiDao(getContext()).get();
                adapterspnerchi = new Spinner_LoaiChi_Adapter(getContext(),mdatachi);
                spinner.setAdapter(adapterspnerchi);
                Button btnthemKC = view.findViewById(R.id.btn_themKC);
                builder.setView(view);
                AlertDialog alertDialog = builder.show();
                btnthemKC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idkhoanchi = String.valueOf(System.currentTimeMillis());
                        String khoanchi = ed_khoanchi.getText().toString();
                        String nguoinhan = ed_nguoinhan.getText().toString();
                        int sotienchi = Integer.parseInt(ed_sotienchi.getText().toString());
                        String idloaichi = ((LoaiChi)spinner.getSelectedItem()).getIdLoaiChi();
                        KhoanChi KC = new KhoanChi(idkhoanchi,khoanchi,nguoinhan,sotienchi,idloaichi);
                        KhoanChiDao dao = new KhoanChiDao(getContext());
                        dao.insert(KC);
                        alertDialog.dismiss();
                        mListkhoanchi = (ArrayList<KhoanChi>) (new KhoanChiDao(getContext())).get();
                        adapterKC.setDataKC(mListkhoanchi);
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
                        textView.setText("Đã thêm: "+KC.getTenKhoanChi());
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
                KhoanChi KC = adapterKC.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Xóa: "+ KC.getTenKhoanChi());
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KhoanChiDao dao = new KhoanChiDao(getContext());
                        dao.delete(KC.getIdKhoanChi());
                        mListkhoanchi = (ArrayList<KhoanChi>) (new KhoanChiDao(getContext())).get();
                        adapterKC.setDataKC(mListkhoanchi);
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
                        textView.setText("Đã xóa: "+KC.getTenKhoanChi());
                        imageView.setImageResource(R.drawable.dlt);
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListkhoanchi = (ArrayList<KhoanChi>) (new KhoanChiDao(getContext())).get();
                        adapterKC.setDataKC(mListkhoanchi);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}