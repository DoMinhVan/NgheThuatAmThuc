package com.example.nghethuatamthuc.models;

import android.widget.Spinner;

import java.util.Date;

public class NguoiDung {
    private String HoTen;
    private String TenDangNhap;
    private String MatKhau;
    private String SoDienThoai;
    private String Gmail;
    private Date NgaySinh;
    private int GioiTinh;
    private int LoaiNguoiDung;
    private String HinhNen;
    private String HinhDaiDien;
    private Date ThoiGian;
    private int TrangThai;

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public void setGioiTinh(int gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public void setLoaiNguoiDung(int loaiNguoiDung) {
        LoaiNguoiDung = loaiNguoiDung;
    }

    public void setHinhNen(String hinhNen) {
        HinhNen = hinhNen;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        HinhDaiDien = hinhDaiDien;
    }

    public void setThoiGian(Date thoiGian) {
        ThoiGian = thoiGian;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getGmail() {
        return Gmail;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public int getLoaiNguoiDung() {
        return LoaiNguoiDung;
    }

    public String getHinhNen() {
        return HinhNen;
    }

    public String getHinhDaiDien() {
        return HinhDaiDien;
    }

    public Date getThoiGian() {
        return ThoiGian;
    }

    public int getTrangThai() {
        return TrangThai;
    }
}
