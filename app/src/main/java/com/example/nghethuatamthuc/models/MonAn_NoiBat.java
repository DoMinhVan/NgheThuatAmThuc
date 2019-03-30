package com.example.nghethuatamthuc.models;

public class MonAn_NoiBat {
    String tenNguoiDang;
    //String idImage;
    String tenMonAn;
    String luotThich;
    String thoiGian;
    float sosanhgia;

    public MonAn_NoiBat(String tenNguoiDang, String tenMonAn, String luotThich, String thoiGian, float sosanhgia) {
        this.tenNguoiDang = tenNguoiDang;
        //this.idImage = idImage;
        this.tenMonAn = tenMonAn;
        this.luotThich = luotThich;
        this.thoiGian = thoiGian;
        this.sosanhgia = sosanhgia;
    }

    public String getTenNguoiDang() {
        return tenNguoiDang;
    }

    public void setTenNguoiDang(String tenNguoiDang) {
        this.tenNguoiDang = tenNguoiDang;
    }

    /*public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }*/

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(String luotThich) {
        this.luotThich = luotThich;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public float getsosanhgia() {
        return sosanhgia;
    }

    public void setsosanhgia(float sosanhgia) {
        this.sosanhgia = sosanhgia;
    }
}
