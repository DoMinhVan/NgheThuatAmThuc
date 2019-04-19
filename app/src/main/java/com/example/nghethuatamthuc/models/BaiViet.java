package com.example.nghethuatamthuc.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaiViet {
    private String ID;
    private String TenBaiViet;
    private String DuongDan;
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
    private String IDNguoiDung;
    private String IDDanhGia;

    private float DanhGiaTrungBinh;
    public BaiViet() {
    }

    public BaiViet(String ID, String tenBaiViet, String duongDan, String nguyenLieu, String buocLam, String dinhDuong, String diaChi, String thongTinChiTiet, int doiTuong, int loaiMon, String ngayViet, String ngaySua, int luotThich, int luotBinhLuan, int trangThai, String IDNguoiDung, String IDDanhGia) {
        this.ID = ID;
        TenBaiViet = tenBaiViet;
        DuongDan = duongDan;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public String getDuongDan() {
        return DuongDan;
    }

    public void setDuongDan(String duongDan) {
        DuongDan = duongDan;
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

    public String getNgayViet() {
        return NgayViet;
    }

    public void setNgayViet(String ngayViet) {
        NgayViet = ngayViet;
    }

    public String getNgaySua() {
        return NgaySua;
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

    public String getIDNguoiDung() {
        return IDNguoiDung;
    }

    public void setIDNguoiDung(String IDNguoiDung) {
        this.IDNguoiDung = IDNguoiDung;
    }

    public String getIDDanhGia() {
        return IDDanhGia;
    }

    public void setIDDanhGia(String IDDanhGia) {
        this.IDDanhGia = IDDanhGia;
    }

    public Date LayNgayVietTheoNgayThang() throws ParseException {
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(NgayViet);
        return dateObj;
    }


    public Date LayNgaySuaTheoNgayThang() throws ParseException {
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(NgaySua);
        return dateObj;
    }

    public void DanhGiaTrungBinh(float danhGiaTrungBinh)
    {
        DanhGiaTrungBinh = danhGiaTrungBinh;
    }

    public float LayDanhGiaTrungBinh()
    {
        return DanhGiaTrungBinh;
    }
}
