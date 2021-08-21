package com.example.assigment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment.Database.Mydatabase;
import com.example.assigment.Modle.KhoanChi;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiDao {
    Mydatabase mydatabase;
    public KhoanChiDao(Context context){
        mydatabase = new Mydatabase(context);
    }

   public List<KhoanChi> get() {
        List<KhoanChi> listkc = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getReadableDatabase();
       Cursor cursor = database.rawQuery("SELECT * FROM TBKHOANCHI",null);
       cursor.moveToFirst();
       while (!cursor.isAfterLast()){
           String idKhoanChi = cursor.getString(0);
           String tenkhoanChi = cursor.getString(1);
           String nguoiNhan = cursor.getString(2);
           int soTienChi = cursor.getInt(3);
           String idLoaiChi = cursor.getString(4);
           KhoanChi kc = new KhoanChi(idKhoanChi,tenkhoanChi,nguoiNhan,soTienChi,idLoaiChi);
           listkc.add(kc);
           cursor.moveToNext();
       }
       cursor.close();
       return listkc;
    }

    public List getDataChi(String loaichiId){
        List<KhoanChi> listkc = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TBKHOANCHI WHERE IDLOAICHI = ?",new String[]{loaichiId});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String idKhoanChi = cursor.getString(0);
            String tenkhoanChi = cursor.getString(1);
            String nguoiNhan = cursor.getString(2);
            int soTienChi = cursor.getInt(3);
            String idLoaiChi = cursor.getString(4);
            KhoanChi kc = new KhoanChi(idKhoanChi,tenkhoanChi,nguoiNhan,soTienChi,idLoaiChi);
            listkc.add(kc);
            cursor.moveToNext();
        }
        cursor.close();
        return listkc;
    }
    public void insert(KhoanChi kc){
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("IDKHOANCHI",kc.getIdKhoanChi());
        values.put("TENKHOANCHI",kc.getTenKhoanChi());
        values.put("NGUOINHAN",kc.getNguoiNhan());
        values.put("SOTIENCHI",kc.getSoTienChi());
        values.put("IDLOAICHI",kc.getIdLoaiChi());
        database.insert("TBKHOANCHI",null,values);
    }

    public void update(KhoanChi kc){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] params = new String[]{kc.getIdKhoanChi()};
        values.put("TENKHOANCHI",kc.getTenKhoanChi());
        values.put("NGUOINHAN",kc.getNguoiNhan());
        values.put("SOTIENCHI",kc.getSoTienChi());
        values.put("IDLOAICHI",kc.getIdLoaiChi());
        database.update("TBKHOANCHI", values, "IDKHOANCHI = ?", params);
    }
    public void  delete(String idKhoanThu){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        String[] params = new String[]{idKhoanThu};
        database.delete("TBKHOANCHI","IDKHOANCHI = ?",params);
    }

    public long tongChi(){
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        return DatabaseUtils.longForQuery(database,"SELECT SUM(SOTIENCHI) FROM TBKHOANCHI",null);
    }
}
