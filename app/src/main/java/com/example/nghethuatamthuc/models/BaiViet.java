package com.example.nghethuatamthuc.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaiViet implements Serializable {
    private String iD;
    private String tenBaiViet;
    private String duongDan;
    private String nguyenLieu;
    private String buocLam;
    private String dinhDuong;
    private String diaChi;
    private String thongTinChiTiet;
    private int doiTuong;
    private int loaiMon;
    private String ngayViet;
    private String ngaySua;
    private int luotThich;
    private int luotBinhLuan;
    private int trangThai;
    private String iDNguoiDung;
    private String iDDanhGia;

    private float danhGiaTrungBinh;

    public BaiViet() {
    }

    public BaiViet(String iD, String tenBaiViet, String duongDan, String nguyenLieu, String buocLam, String dinhDuong, String diaChi, String thongTinChiTiet, int doiTuong, int loaiMon, String ngayViet, String ngaySua, int luotThich, int luotBinhLuan, int trangThai, String iDNguoiDung, String iDDanhGia) {
        this.iD = iD;
        this.tenBaiViet = tenBaiViet;
        this.duongDan = duongDan;
        this.nguyenLieu = nguyenLieu;
        this.buocLam = buocLam;
        this.dinhDuong = dinhDuong;
        this.diaChi = diaChi;
        this.thongTinChiTiet = thongTinChiTiet;
        this.doiTuong = doiTuong;
        this.loaiMon = loaiMon;
        this.ngayViet = ngayViet;
        this.ngaySua = ngaySua;
        this.luotThich = luotThich;
        this.luotBinhLuan = luotBinhLuan;
        this.trangThai = trangThai;
        this.iDNguoiDung = iDNguoiDung;
        this.iDDanhGia = iDDanhGia;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getTenBaiViet() {
        return tenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        this.tenBaiViet = tenBaiViet;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public String getBuocLam() {
        return buocLam;
    }

    public void setBuocLam(String buocLam) {
        this.buocLam = buocLam;
    }

    public String getDinhDuong() {
        return dinhDuong;
    }

    public void setDinhDuong(String dinhDuong) {
        this.dinhDuong = dinhDuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThongTinChiTiet() {
        return thongTinChiTiet;
    }

    public void setThongTinChiTiet(String thongTinChiTiet) {
        this.thongTinChiTiet = thongTinChiTiet;
    }

    public int getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(int doiTuong) {
        this.doiTuong = doiTuong;
    }

    public int getLoaiMon() {
        return loaiMon;
    }

    public void setLoaiMon(int loaiMon) {
        this.loaiMon = loaiMon;
    }

    public String getNgayViet() {
        return ngayViet;
    }

    public void setNgayViet(String ngayViet) {
        this.ngayViet = ngayViet;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(int luotThich) {
        this.luotThich = luotThich;
    }

    public int getLuotBinhLuan() {
        return luotBinhLuan;
    }

    public void setLuotBinhLuan(int luotBinhLuan) {
        this.luotBinhLuan = luotBinhLuan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getiDNguoiDung() {
        return iDNguoiDung;
    }

    public void setiDNguoiDung(String iDNguoiDung) {
        this.iDNguoiDung = iDNguoiDung;
    }

    public String getiDDanhGia() {
        return iDDanhGia;
    }

    public void setiDDanhGia(String iDDanhGia) {
        this.iDDanhGia = iDDanhGia;
    }

    public Date LayNgayVietTheoNgayThang() throws ParseException {
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(ngayViet);
        return dateObj;
    }


    public Date LayNgaySuaTheoNgayThang() throws ParseException {
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(ngaySua);
        return dateObj;
    }

    public void DanhGiaTrungBinh(float danhGiaTrungBinh)
    {
        danhGiaTrungBinh = danhGiaTrungBinh;
    }

    public float LayDanhGiaTrungBinh()
    {
        return danhGiaTrungBinh;
    }
}
