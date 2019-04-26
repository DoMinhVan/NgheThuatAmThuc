package com.example.nghethuatamthuc.models;

public class BinhLuan {
    private String IDBinhLuan;
    private String BinhLuan;
    private int LoaiNguoiDung;
    private String ThoiGianBL;
    private String ThoiGianSua;
    private int TrangThai;
    private String IDNguoiDung;
    private String IDBaiViet;

    public BinhLuan() {
    }

    public BinhLuan(String IDBinhLuan, String binhLuan, int loaiNguoiDung, String thoiGianBL, String thoiGianSua, int trangThai, String IDNguoiDung, String IDBaiViet) {
        this.IDBinhLuan = IDBinhLuan;
        BinhLuan = binhLuan;
        LoaiNguoiDung = loaiNguoiDung;
        ThoiGianBL = thoiGianBL;
        ThoiGianSua = thoiGianSua;
        TrangThai = trangThai;
        this.IDNguoiDung = IDNguoiDung;
        this.IDBaiViet = IDBaiViet;
    }

    public String getIDBinhLuan() {
        return IDBinhLuan;
    }

    public void setIDBinhLuan(String IDBinhLuan) {
        this.IDBinhLuan = IDBinhLuan;
    }

    public String getBinhLuan() {
        return BinhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        BinhLuan = binhLuan;
    }

    public int getLoaiNguoiDung() {
        return LoaiNguoiDung;
    }

    public void setLoaiNguoiDung(int loaiNguoiDung) {
        LoaiNguoiDung = loaiNguoiDung;
    }

    public String getThoiGianBL() {
        return ThoiGianBL;
    }

    public void setThoiGianBL(String thoiGianBL) {
        ThoiGianBL = thoiGianBL;
    }

    public String getThoiGianSua() {
        return ThoiGianSua;
    }

    public void setThoiGianSua(String thoiGianSua) {
        ThoiGianSua = thoiGianSua;
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

    public String getIDBaiViet() {
        return IDBaiViet;
    }

    public void setIDBaiViet(String IDBaiViet) {
        this.IDBaiViet = IDBaiViet;
    }
}
