package com.example.nghethuatamthuc.models;

public class TinNhan {
    int imgHinhThongBao;
    String txtTenNguoiDung;
    String txtNoiDung;

    public TinNhan(int imgHinhThongBao, String txtTenNguoiDung, String txtNoiDung) {
        this.imgHinhThongBao = imgHinhThongBao;
        this.txtTenNguoiDung = txtTenNguoiDung;
        this.txtNoiDung = txtNoiDung;
    }

    public int getImgHinhThongBao() {
        return imgHinhThongBao;
    }

    public void setImgHinhThongBao(int imgHinhThongBao) {
        this.imgHinhThongBao = imgHinhThongBao;
    }

    public String getTxtTenNguoiDung() {
        return txtTenNguoiDung;
    }

    public void setTxtTenNguoiDung(String txtTenNguoiDung) {
        this.txtTenNguoiDung = txtTenNguoiDung;
    }

    public String getTxtNoiDung() {
        return txtNoiDung;
    }

    public void setTxtNoiDung(String txtNoiDung) {
        this.txtNoiDung = txtNoiDung;
    }
}
