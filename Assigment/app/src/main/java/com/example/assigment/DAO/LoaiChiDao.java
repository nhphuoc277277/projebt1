package com.example.assigment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment.Database.Mydatabase;
import com.example.assigment.Modle.LoaiChi;
import com.example.assigment.Modle.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiDao {
    Mydatabase mydatabase;
    public LoaiChiDao(Context context){
        mydatabase = new Mydatabase(context);
    }

    public List<LoaiChi> get(){
        List<LoaiChi> listLC = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TBLOAICHI",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String idLoaiChi = cursor.getString(0);
            String LoaiChi = cursor.getString(1);
            LoaiChi lc = new LoaiChi(idLoaiChi,LoaiChi);
            cursor.moveToNext();
            listLC.add(lc);
        }
        cursor.close();
        return listLC;
    }

    public void insert(LoaiChi lc){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IDLOAICHI",lc.getIdLoaiChi());
        values.put("TENLOAICHI",lc.getLoaiChi());
        database.insert("TBLOAICHI",null,values);
    }

    public void update(LoaiChi lc){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] params = new String[]{lc.getIdLoaiChi()};
        values.put("TENLOAICHI",lc.getLoaiChi());
        database.update("TBLOAICHI", values, "IDLOAICHI = ?", params);
    }
    public void  delete(String idLoaiChi){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        String[] params = new String[]{idLoaiChi};
        database.delete("TBLOAICHI","IDLOAICHI = ?",params);
    }
}
