package com.example.nghethuatamthuc.models;

public class Thich {
    private String iDThich;
    private int loai;
    private String iDLoai;
    private String thoiGian;
    private String iDNguoiDung;

    public Thich() {
    }

    public Thich(String iDThich, int loai, String iDLoai, String thoiGian, String iDNguoiDung) {
        this.iDThich = iDThich;
        this.loai = loai;
        this.iDLoai = iDLoai;
        this.thoiGian = thoiGian;
        this.iDNguoiDung = iDNguoiDung;
    }

    public String getiDThich() {
        return iDThich;
    }

    public void setiDThich(String iDThich) {
        this.iDThich = iDThich;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getiDLoai() {
        return iDLoai;
    }

    public void setiDLoai(String iDLoai) {
        this.iDLoai = iDLoai;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getiDNguoiDung() {
        return iDNguoiDung;
    }

    public void setiDNguoiDung(String iDNguoiDung) {
        this.iDNguoiDung = iDNguoiDung;
    }
}
