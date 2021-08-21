package com.example.assigment.Modle;

public class KhoanThu {
    private String idKhoanThu;
    private String tenKhoanThu;
    private String nguoiGiao;
    private int soTienThu;
    private  String idLoaiThu;

    public KhoanThu(String idKhoanThu, String tenKhoanThu, String nguoiGiao, int soTienThu, String idLoaiThu) {
        this.idKhoanThu = idKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.nguoiGiao = nguoiGiao;
        this.soTienThu = soTienThu;
        this.idLoaiThu = idLoaiThu;
    }

    public String getIdKhoanThu() {
        return idKhoanThu;
    }

    public void setIdKhoanThu(String idKhoanThu) {
        this.idKhoanThu = idKhoanThu;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public String getNguoiGiao() {
        return nguoiGiao;
    }

    public void setNguoiGiao(String nguoiGiao) {
        this.nguoiGiao = nguoiGiao;
    }

    public int getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(int soTienThu) {
        this.soTienThu = soTienThu;
    }

    public String getIdLoaiThu() {
        return idLoaiThu;
    }

    public void setIdLoaiThu(String idLoaiThu) {
        this.idLoaiThu = idLoaiThu;
    }
}
