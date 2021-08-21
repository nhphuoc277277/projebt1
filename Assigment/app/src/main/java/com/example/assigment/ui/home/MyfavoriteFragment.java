package com.example.assigment.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assigment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyfavoriteFragment extends Fragment {
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfavorite_fragment,container,false);
        listView = view.findViewById(R.id.listview);

        String[] from = {"Name","image"};
        int[] to = {R.id.shoesname,R.id.im_shoes};

        List<HashMap<String,Object>> listitem = new ArrayList<>();
        HashMap<String ,Object> item = new HashMap<>();
        item.put("Name","sfwefwetwefs");
        item.put("image",R.drawable.giaymoi);

        HashMap<String ,Object> item1 = new HashMap<>();
        item1.put("Name","tewruiweuoilkl;");
        item1.put("image",R.drawable.giay1);
        listitem.add(item1);

        HashMap<String ,Object> item2 = new HashMap<>();
        item2.put("Name","qqolkmnsjhfuppjfs");
        item2.put("image",R.drawable.cv1);

        HashMap<String ,Object> item3 = new HashMap<>();
        item3.put("Name","gsdgsgdgerhethert");
        item3.put("image",R.drawable.cv4);

        HashMap<String ,Object> item4 = new HashMap<>();
        item4.put("Name","xcvbuhgsy8rowpekl");
        item4.put("image",R.drawable.giay10);

        HashMap<String ,Object> item5 = new HashMap<>();
        item5.put("Name","ffsdksllpawpfawfg");
        item5.put("image",R.drawable.cv3);

        HashMap<String ,Object> item6 = new HashMap<>();
        item6.put("Name","ppoiujpoeiwnjkwk");
        item6.put("image",R.drawable.giay9);

        HashMap<String ,Object> item7 = new HashMap<>();
        item7.put("Name","ogsegkallwoefgere");
        item7.put("image",R.drawable.banchay);

        HashMap<String ,Object> item8 = new HashMap<>();
        item8.put("Name","1we5gwm6rgeorkghrk");
        item8.put("image",R.drawable.cv2);

        HashMap<String ,Object> item9 = new HashMap<>();
        item9.put("Name","oualwufus");
        item9.put("image",R.drawable.bc);
        listitem.add(item);
        listitem.add(item2);
        listitem.add(item3);
        listitem.add(item4);
        listitem.add(item5);
        listitem.add(item6);
        listitem.add(item7);
        listitem.add(item8);
        listitem.add(item9);

        SimpleAdapter adapter = new SimpleAdapter(getContext(),listitem,R.layout.item_sneakers,from,to);
        listView.setAdapter(adapter);
        return view;
    }
}
