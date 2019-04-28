package com.example.nghethuatamthuc.models;

public class HinhAnh {
    private String duongDan;
    private float chieuDai;
    private float chieuRong;
    private int loai;
    private String iDLoai;

    public HinhAnh() {
    }

    public HinhAnh(String duongDan, float chieuDai, float chieuRong, int loai, String iDLoai) {
        this.duongDan = duongDan;
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
        this.loai = loai;
        this.iDLoai = iDLoai;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public float getChieuDai() {
        return chieuDai;
    }

    public void setChieuDai(float chieuDai) {
        this.chieuDai = chieuDai;
    }

    public float getChieuRong() {
        return chieuRong;
    }

    public void setChieuRong(float chieuRong) {
        this.chieuRong = chieuRong;
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
}
