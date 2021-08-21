package com.example.assigment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Mydatabase extends SQLiteOpenHelper {
    public static final String Name = "QLTHUCHI";

    public Mydatabase(Context context) {
        super(context, Name, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TBLOAITHU(IDLOAITHU TEXT primary key not null, TENLOAITHU TEXT)");

        db.execSQL("CREATE TABLE TBKHOANTHU(IDKHOANTHU TEXT primary key not null,TENKHOANTHU INTEGER,NGUOIGIAO TEXT,SOTIENTHU TEXT ,IDLOAITHU TEXT not null, FOREIGN KEY (IDLOAITHU) REFERENCES TBLOAITHU(IDLOAITHU))");

        db.execSQL("CREATE TABLE TBLOAICHI(IDLOAICHI TEXT primary key not null, TENLOAICHI TEXT)");

        db.execSQL("CREATE TABLE TBKHOANCHI(IDKHOANCHI TEXT primary key not null, TENKHOANCHI INTEGER, NGUOINHAN TEXT, SOTIENCHI ,IDLOAICHI TEXT not null, FOREIGN KEY (IDLOAICHI) REFERENCES TBLOAICHI(IDLOAICHI))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TBLOAITHU");
        db.execSQL("DROP TABLE IF EXISTS TBKHOANTHU");
        db.execSQL("DROP TABLE IF EXISTS TBLOAICHI");
        db.execSQL("DROP TABLE IF EXISTS TBKHOANCHI");
        onCreate(db);
    }
}
