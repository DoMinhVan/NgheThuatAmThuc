package com.example.nghethuatamthuc.models;

public class MonAn_NoiBat {
    String tenNguoiDang;
    //String idImage;
    String tenMonAn;
    String luotThich;
    String thoiGian;
    float sodanhgia;
    int like;
    int love;

    public MonAn_NoiBat(String tenNguoiDang, String tenMonAn, String luotThich, String thoiGian, float sodanhgia, int like, int love) {
        this.tenNguoiDang = tenNguoiDang;
        this.tenMonAn = tenMonAn;
        this.luotThich = luotThich;
        this.thoiGian = thoiGian;
        this.sodanhgia = sodanhgia;
        this.like = like;
        this.love = love;
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

    public float getSodanhgia() {
        return sodanhgia;
    }

    public int isLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int isLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }
}
