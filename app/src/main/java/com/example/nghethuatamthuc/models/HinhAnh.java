package com.example.nghethuatamthuc.models;

public class HinhAnh {
    private String DuongDan;
    private float ChieuDai;
    private float ChieuRong;
    private int Loai;
    private String IDLoai;

    public HinhAnh() {
    }

    public HinhAnh(String duongDan, float chieuDai, float chieuRong, int loai, String IDLoai) {
        DuongDan = duongDan;
        ChieuDai = chieuDai;
        ChieuRong = chieuRong;
        Loai = loai;
        this.IDLoai = IDLoai;
    }

    public String getDuongDan() {
        return DuongDan;
    }

    public void setDuongDan(String duongDan) {
        DuongDan = duongDan;
    }

    public float getChieuDai() {
        return ChieuDai;
    }

    public void setChieuDai(float chieuDai) {
        ChieuDai = chieuDai;
    }

    public float getChieuRong() {
        return ChieuRong;
    }

    public void setChieuRong(float chieuRong) {
        ChieuRong = chieuRong;
    }

    public int getLoai() {
        return Loai;
    }

    public void setLoai(int loai) {
        Loai = loai;
    }

    public String getIDLoai() {
        return IDLoai;
    }

    public void setIDLoai(String IDLoai) {
        this.IDLoai = IDLoai;
    }
}
