package com.example.assigment.Modle;

public class LoaiThu {
    private String idLoaiThu;
    private String loaiThu;


    public LoaiThu(String idLoaiThu, String loaiThu) {
        this.idLoaiThu = idLoaiThu;
        this.loaiThu = loaiThu;
    }

    public String getLoaiThu() {
        return loaiThu;
    }

    public void setLoaiThu(String loaiThu) {
        this.loaiThu = loaiThu;
    }

    public String getIdLoaiThu() {
        return idLoaiThu;
    }

    public void setIdLoaiThu(String idLoaiThu) {
        this.idLoaiThu = idLoaiThu;
    }
}
