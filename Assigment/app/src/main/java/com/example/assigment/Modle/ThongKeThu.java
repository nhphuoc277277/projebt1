package com.example.assigment.Modle;

public class ThongKeThu {
    private String ngayThang;
    private String khoanThu;
    private String loaiThu;

    public ThongKeThu(String ngayThang, String khoanThu, String loaiThu) {
        this.ngayThang = ngayThang;
        this.khoanThu = khoanThu;
        this.loaiThu = loaiThu;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getKhoanThu() {
        return khoanThu;
    }

    public void setKhoanThu(String khoanThu) {
        this.khoanThu = khoanThu;
    }

    public String getLoaiThu() {
        return loaiThu;
    }

    public void setLoaiThu(String loaiThu) {
        this.loaiThu = loaiThu;
    }
}
