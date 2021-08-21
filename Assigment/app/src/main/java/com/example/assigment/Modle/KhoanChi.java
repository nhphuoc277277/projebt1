package com.example.assigment.Modle;

public class KhoanChi {
    private String idKhoanChi;
    private String tenKhoanChi;
    private String nguoiNhan;
    private int soTienChi;
    private String idLoaiChi;

    public KhoanChi(String idKhoanChi, String tenKhoanChi, String nguoiNhan, int soTienChi, String idLoaiChi) {
        this.idKhoanChi = idKhoanChi;
        this.tenKhoanChi = tenKhoanChi;
        this.nguoiNhan = nguoiNhan;
        this.soTienChi = soTienChi;
        this.idLoaiChi = idLoaiChi;
    }

    public String getIdKhoanChi() {
        return idKhoanChi;
    }

    public void setIdKhoanChi(String idKhoanChi) {
        this.idKhoanChi = idKhoanChi;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public int getSoTienChi() {
        return soTienChi;
    }

    public void setSoTienChi(int soTienChi) {
        this.soTienChi = soTienChi;
    }

    public String getIdLoaiChi() {
        return idLoaiChi;
    }

    public void setIdLoaiChi(String idLoaiChi) {
        this.idLoaiChi = idLoaiChi;
    }
}
