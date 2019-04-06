package com.example.nghethuatamthuc.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaiViet {
    private String TenBaiViet;
    private String NguyenLieu;
    private String BuocLam;
    private String DinhDuong;
    private String DiaChi;
    private String ThongTinChiTiet;
    private int DoiTuong;
    private int LoaiMon;
    private String NgayViet;
    private String NgaySua;
    private int LuotThich;
    private int LuotBinhLuan;
    private int TrangThai;
    private int IDNguoiDung;
    private int IDDanhGia;

    public BaiViet() {
    }

    public BaiViet(String tenBaiViet, String nguyenLieu, String buocLam, String dinhDuong, String diaChi, String thongTinChiTiet, int doiTuong, int loaiMon, String ngayViet, String ngaySua, int luotThich, int luotBinhLuan, int trangThai, int IDNguoiDung, int IDDanhGia) throws ParseException {
        TenBaiViet = tenBaiViet;
        NguyenLieu = nguyenLieu;
        BuocLam = buocLam;
        DinhDuong = dinhDuong;
        DiaChi = diaChi;
        ThongTinChiTiet = thongTinChiTiet;
        DoiTuong = doiTuong;
        LoaiMon = loaiMon;
        NgayViet = ngayViet;
        NgaySua = ngaySua;
        LuotThich = luotThich;
        LuotBinhLuan = luotBinhLuan;
        TrangThai = trangThai;
        this.IDNguoiDung = IDNguoiDung;
        this.IDDanhGia = IDDanhGia;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public String getNguyenLieu() {
        return NguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        NguyenLieu = nguyenLieu;
    }

    public String getBuocLam() {
        return BuocLam;
    }

    public void setBuocLam(String buocLam) {
        BuocLam = buocLam;
    }

    public String getDinhDuong() {
        return DinhDuong;
    }

    public void setDinhDuong(String dinhDuong) {
        DinhDuong = dinhDuong;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getThongTinChiTiet() {
        return ThongTinChiTiet;
    }

    public void setThongTinChiTiet(String thongTinChiTiet) {
        ThongTinChiTiet = thongTinChiTiet;
    }

    public int getDoiTuong() {
        return DoiTuong;
    }

    public void setDoiTuong(int doiTuong) {
        DoiTuong = doiTuong;
    }

    public int getLoaiMon() {
        return LoaiMon;
    }

    public void setLoaiMon(int loaiMon) {
        LoaiMon = loaiMon;
    }

    public Date getNgayViet() throws ParseException {
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(NgayViet);
        return dateObj;
    }

    public void setNgayViet(String ngayViet){
        NgayViet = ngayViet;
    }

    public Date getNgaySua() throws ParseException {
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(NgaySua);
        return dateObj;
    }

    public void setNgaySua(String ngaySua) {
        NgaySua = ngaySua;
    }

    public int getLuotThich() {
        return LuotThich;
    }

    public void setLuotThich(int luotThich) {
        LuotThich = luotThich;
    }

    public int getLuotBinhLuan() {
        return LuotBinhLuan;
    }

    public void setLuotBinhLuan(int luotBinhLuan) {
        LuotBinhLuan = luotBinhLuan;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getIDNguoiDung() {
        return IDNguoiDung;
    }

    public void setIDNguoiDung(int IDNguoiDung) {
        this.IDNguoiDung = IDNguoiDung;
    }

    public int getIDDanhGia() {
        return IDDanhGia;
    }

    public void setIDDanhGia(int IDDanhGia) {
        this.IDDanhGia = IDDanhGia;
    }
}
