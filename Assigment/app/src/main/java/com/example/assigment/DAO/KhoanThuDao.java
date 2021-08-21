package com.example.assigment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment.Database.Mydatabase;
import com.example.assigment.Modle.KhoanChi;
import com.example.assigment.Modle.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuDao {
    Mydatabase mydatabase;

    public KhoanThuDao(Context context){
        mydatabase = new Mydatabase(context);

    }

    public List<KhoanThu> get(){
        List<KhoanThu> listKT = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TBKHOANTHU",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String idKhoanThu = cursor.getString(0);
            String khoanThu = cursor.getString(1);
            String nguoiGiao = cursor.getString(2);
            int soTienThu = cursor.getInt(3);
            String idLoaiThu = cursor.getString(4);
            KhoanThu kt = new KhoanThu(idKhoanThu,khoanThu,nguoiGiao,soTienThu,idLoaiThu);
            cursor.moveToNext();
            listKT.add(kt);
        }
        cursor.close();
        return listKT;
    }

    public List getDataThu(String loaiThuId){
        List<KhoanThu> listKT = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TBKHOANTHU WHERE IDLOAITHU =?",new String[]{loaiThuId});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String idKhoanThu = cursor.getString(0);
            String khoanThu = cursor.getString(1);
            String nguoiGiao = cursor.getString(2);
            int soTienThu = cursor.getInt(3);
            String idLoaiThu = cursor.getString(4);
            KhoanThu kt = new KhoanThu(idKhoanThu,khoanThu,nguoiGiao,soTienThu,idLoaiThu);
            cursor.moveToNext();
            listKT.add(kt);
        }
        cursor.close();
        return listKT;
    }

    public void insert(KhoanThu kt){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IDKHOANTHU",kt.getIdKhoanThu());
        values.put("TENKHOANTHU",kt.getTenKhoanThu());
        values.put("NGUOIGIAO",kt.getNguoiGiao());
        values.put("SOTIENTHU",kt.getSoTienThu());
        values.put("IDLOAITHU",kt.getIdLoaiThu());
        database.insert("TBKHOANTHU",null,values);
    }

    public void update(KhoanThu kt){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] params = new String[]{kt.getIdKhoanThu()};
        values.put("TENKHOANTHU",kt.getTenKhoanThu());
        values.put("NGUOIGIAO",kt.getNguoiGiao());
        values.put("SOTIENTHU",kt.getSoTienThu());
        values.put("IDLOAITHU",kt.getIdLoaiThu());
        database.update("TBKHOANTHU", values, "IDKHOANTHU = ?", params);
    }

    public void  delete(String idKhoanThu){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        String[] params = new String[]{idKhoanThu};
        database.delete("TBKHOANTHU","IDKHOANTHU = ?",params);
    }

    public long tongThu(){
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        return DatabaseUtils.longForQuery(database,"SELECT SUM(SOTIENTHU) FROM TBKHOANTHU",null);
    }
}
