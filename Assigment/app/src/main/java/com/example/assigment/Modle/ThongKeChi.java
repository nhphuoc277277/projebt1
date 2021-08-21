package com.example.assigment.Modle;

public class ThongKeChi {
    private String ngayThang;
    private String khoanChi;
    private String loaiChi;

    public ThongKeChi(String ngayThang, String khoanChi, String loaiChi) {
        this.ngayThang = ngayThang;
        this.khoanChi = khoanChi;
        this.loaiChi = loaiChi;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getKhoanChi() {
        return khoanChi;
    }

    public void setKhoanChi(String khoanChi) {
        this.khoanChi = khoanChi;
    }

    public String getLoaiChi() {
        return loaiChi;
    }

    public void setLoaiChi(String loaiChi) {
        this.loaiChi = loaiChi;
    }
}
