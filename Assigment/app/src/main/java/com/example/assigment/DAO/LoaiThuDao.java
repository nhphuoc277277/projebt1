package com.example.assigment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment.Database.Mydatabase;
import com.example.assigment.Modle.KhoanThu;
import com.example.assigment.Modle.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuDao {
    Mydatabase mydatabase;
    public LoaiThuDao(Context context){
        mydatabase = new Mydatabase(context);
    }

    public List<LoaiThu> get(){
        List<LoaiThu> listLT = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TBLOAITHU",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String idLoaiThu = cursor.getString(0);
            String LoaiThu = cursor.getString(1);
            LoaiThu lt = new LoaiThu(idLoaiThu,LoaiThu);
            cursor.moveToNext();
            listLT.add(lt);
        }
        cursor.close();
        return listLT;
    }

    public void insert(LoaiThu lt){
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("IDLOAITHU",lt.getIdLoaiThu());
        values.put("TENLOAITHU",lt.getLoaiThu());
        database.insert("TBLOAITHU",null,values);
    }

    public void update(LoaiThu lt){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] params = new String[]{lt.getIdLoaiThu()};
        values.put("TENLOAITHU",lt.getLoaiThu());
        database.update("TBLOAITHU", values, "IDLOAITHU = ?", params);
    }
    public void  delete(String idLoaiThu){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        String[] params = new String[]{idLoaiThu};
        database.delete("TBLOAITHU","IDLOAITHU = ?",params);
    }
}
