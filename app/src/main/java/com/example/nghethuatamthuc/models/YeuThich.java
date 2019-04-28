package com.example.nghethuatamthuc.models;

public class YeuThich {
    String iDYeuThich;
    String iDBaiVietYeuThich;
    String iDNGuoiDungYeuThich;

    public YeuThich() {
    }

    public YeuThich(String iDYeuThich, String iDBaiVietYeuThich, String iDNGuoiDungYeuThich) {
        this.iDYeuThich = iDYeuThich;
        this.iDBaiVietYeuThich = iDBaiVietYeuThich;
        this.iDNGuoiDungYeuThich = iDNGuoiDungYeuThich;
    }

    public String getiDYeuThich() {
        return iDYeuThich;
    }

    public void setiDYeuThich(String iDYeuThich) {
        this.iDYeuThich = iDYeuThich;
    }

    public String getiDBaiVietYeuThich() {
        return iDBaiVietYeuThich;
    }

    public void setiDBaiVietYeuThich(String iDBaiVietYeuThich) {
        this.iDBaiVietYeuThich = iDBaiVietYeuThich;
    }

    public String getiDNGuoiDungYeuThich() {
        return iDNGuoiDungYeuThich;
    }

    public void setiDNGuoiDungYeuThich(String iDNGuoiDungYeuThich) {
        this.iDNGuoiDungYeuThich = iDNGuoiDungYeuThich;
    }
}
