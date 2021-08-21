package com.example.assigment.Modle;

public class LoaiChi {
    private String idLoaiChi;
    private String loaiChi;


    public LoaiChi(String idLoaiChi, String loaiChi) {
        this.idLoaiChi = idLoaiChi;
        this.loaiChi = loaiChi;
    }

    public String getLoaiChi() {
        return loaiChi;
    }

    public void setLoaiChi(String loaiChi) {
        this.loaiChi = loaiChi;
    }

    public String getIdLoaiChi() {
        return idLoaiChi;
    }

    public void setIdLoaiChi(String idLoaiChi) {
        this.idLoaiChi = idLoaiChi;
    }
}
