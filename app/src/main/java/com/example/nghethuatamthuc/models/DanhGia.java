package com.example.nghethuatamthuc.models;

public class DanhGia {
    private String IDDanhGia;
    private int DanhGia;
    private String IDNguoiDung;
    private String IDBaiViet;

    public DanhGia() {
    }

    public DanhGia(String IDDanhGia, int danhGia, String IDNguoiDung, String IDBaiViet) {
        this.IDDanhGia = IDDanhGia;
        DanhGia = danhGia;
        this.IDNguoiDung = IDNguoiDung;
        this.IDBaiViet = IDBaiViet;
    }

    public String getIDDanhGia() {
        return IDDanhGia;
    }

    public void setIDDanhGia(String IDDanhGia) {
        this.IDDanhGia = IDDanhGia;
    }

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
        DanhGia = danhGia;
    }

    public String getIDNguoiDung() {
        return IDNguoiDung;
    }

    public void setIDNguoiDung(String IDNguoiDung) {
        this.IDNguoiDung = IDNguoiDung;
    }

    public String getIDBaiViet() {
        return IDBaiViet;
    }

    public void setIDBaiViet(String IDBaiViet) {
        this.IDBaiViet = IDBaiViet;
    }
}
