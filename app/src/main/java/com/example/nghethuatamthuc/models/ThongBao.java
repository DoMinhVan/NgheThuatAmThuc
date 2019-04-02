package com.example.nghethuatamthuc.models;

public class ThongBao {
    int imgHinhThongBao;
    String txtTenNguoiDung;
    String txtThoiGian;

    public ThongBao(int imgHinhThongBao, String txtTenNguoiDung, String txtThoiGian) {
        this.imgHinhThongBao = imgHinhThongBao;
        this.txtTenNguoiDung = txtTenNguoiDung;
        this.txtThoiGian = txtThoiGian;
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

    public String getTxtThoiGian() {
        return txtThoiGian;
    }

    public void setTxtThoiGian(String txtThoiGian) {
        this.txtThoiGian = txtThoiGian;
    }
}
