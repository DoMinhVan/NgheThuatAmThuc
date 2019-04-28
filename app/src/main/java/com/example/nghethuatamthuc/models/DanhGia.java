package com.example.nghethuatamthuc.models;

public class DanhGia {
    private String iDDanhGia;
    private int danhGia;
    private String iDNguoiDung;
    private String iDBaiViet;

    public DanhGia() {
    }

    public DanhGia(String iDDanhGia, int danhGia, String iDNguoiDung, String iDBaiViet) {
        this.iDDanhGia = iDDanhGia;
        this.danhGia = danhGia;
        this.iDNguoiDung = iDNguoiDung;
        this.iDBaiViet = iDBaiViet;
    }

    public String getiDDanhGia() {
        return iDDanhGia;
    }

    public void setiDDanhGia(String iDDanhGia) {
        this.iDDanhGia = iDDanhGia;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public String getiDNguoiDung() {
        return iDNguoiDung;
    }

    public void setiDNguoiDung(String iDNguoiDung) {
        this.iDNguoiDung = iDNguoiDung;
    }

    public String getiDBaiViet() {
        return iDBaiViet;
    }

    public void setiDBaiViet(String iDBaiViet) {
        this.iDBaiViet = iDBaiViet;
    }
}
