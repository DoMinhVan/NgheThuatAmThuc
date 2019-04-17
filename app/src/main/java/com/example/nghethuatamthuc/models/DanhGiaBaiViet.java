package com.example.nghethuatamthuc.models;

public class DanhGiaBaiViet {
    private String IDDanhGia;
    private int DanhGia1;
    private int DanhGia2;
    private int DanhGia3;
    private int DanhGia4;
    private int DanhGia5;

    public DanhGiaBaiViet() {
    }

    public DanhGiaBaiViet(String IDDanhGia, int danhGia1, int danhGia2, int danhGia3, int danhGia4, int danhGia5) {
        this.IDDanhGia = IDDanhGia;
        DanhGia1 = danhGia1;
        DanhGia2 = danhGia2;
        DanhGia3 = danhGia3;
        DanhGia4 = danhGia4;
        DanhGia5 = danhGia5;
    }

    public String getIDDanhGia() {
        return IDDanhGia;
    }

    public void setIDDanhGia(String IDDanhGia) {
        this.IDDanhGia = IDDanhGia;
    }

    public int getDanhGia1() {
        return DanhGia1;
    }

    public void setDanhGia1(int danhGia1) {
        DanhGia1 = danhGia1;
    }

    public int getDanhGia2() {
        return DanhGia2;
    }

    public void setDanhGia2(int danhGia2) {
        DanhGia2 = danhGia2;
    }

    public int getDanhGia3() {
        return DanhGia3;
    }

    public void setDanhGia3(int danhGia3) {
        DanhGia3 = danhGia3;
    }

    public int getDanhGia4() {
        return DanhGia4;
    }

    public void setDanhGia4(int danhGia4) {
        DanhGia4 = danhGia4;
    }

    public int getDanhGia5() {
        return DanhGia5;
    }

    public void setDanhGia5(int danhGia5) {
        DanhGia5 = danhGia5;
    }
}


