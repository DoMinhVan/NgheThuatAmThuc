package com.example.nghethuatamthuc.models;

public class BinhLuan {
    private String iDBinhLuan;
    private String binhLuan;
    private int loaiNguoiDung;
    private String thoiGianBL;
    private String thoiGianSua;
    private int trangThai;
    private String iDNguoiDung;
    private String iDBaiViet;

    public BinhLuan() {
    }

    public BinhLuan(String iDBinhLuan, String binhLuan, int loaiNguoiDung, String thoiGianBL, String thoiGianSua, int trangThai, String iDNguoiDung, String iDBaiViet) {
        this.iDBinhLuan = iDBinhLuan;
        this.binhLuan = binhLuan;
        this.loaiNguoiDung = loaiNguoiDung;
        this.thoiGianBL = thoiGianBL;
        this.thoiGianSua = thoiGianSua;
        this.trangThai = trangThai;
        this.iDNguoiDung = iDNguoiDung;
        this.iDBaiViet = iDBaiViet;
    }

    public String getiDBinhLuan() {
        return iDBinhLuan;
    }

    public void setiDBinhLuan(String iDBinhLuan) {
        this.iDBinhLuan = iDBinhLuan;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public int getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(int loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public String getThoiGianBL() {
        return thoiGianBL;
    }

    public void setThoiGianBL(String thoiGianBL) {
        this.thoiGianBL = thoiGianBL;
    }

    public String getThoiGianSua() {
        return thoiGianSua;
    }

    public void setThoiGianSua(String thoiGianSua) {
        this.thoiGianSua = thoiGianSua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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
