package com.example.nghethuatamthuc.models;

import java.io.Serializable;

public class DanhGiaBaiViet implements Serializable {
    private String iDDanhGia;
    private int danhGia1;
    private int danhGia2;
    private int danhGia3;
    private int danhGia4;
    private int danhGia5;

    public DanhGiaBaiViet() {
    }

    public DanhGiaBaiViet(String iDDanhGia, int danhGia1, int danhGia2, int danhGia3, int danhGia4, int danhGia5) {
        this.iDDanhGia = iDDanhGia;
        this.danhGia1 = danhGia1;
        this.danhGia2 = danhGia2;
        this.danhGia3 = danhGia3;
        this.danhGia4 = danhGia4;
        this.danhGia5 = danhGia5;
    }

    public String getiDDanhGia() {
        return iDDanhGia;
    }

    public void setiDDanhGia(String iDDanhGia) {
        this.iDDanhGia = iDDanhGia;
    }

    public int getDanhGia1() {
        return danhGia1;
    }

    public void setDanhGia1(int danhGia1) {
        this.danhGia1 = danhGia1;
    }

    public int getDanhGia2() {
        return danhGia2;
    }

    public void setDanhGia2(int danhGia2) {
        this.danhGia2 = danhGia2;
    }

    public int getDanhGia3() {
        return danhGia3;
    }

    public void setDanhGia3(int danhGia3) {
        this.danhGia3 = danhGia3;
    }

    public int getDanhGia4() {
        return danhGia4;
    }

    public void setDanhGia4(int danhGia4) {
        this.danhGia4 = danhGia4;
    }

    public int getDanhGia5() {
        return danhGia5;
    }

    public void setDanhGia5(int danhGia5) {
        this.danhGia5 = danhGia5;
    }
}


