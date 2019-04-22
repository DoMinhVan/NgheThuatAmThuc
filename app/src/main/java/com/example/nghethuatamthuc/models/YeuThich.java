package com.example.nghethuatamthuc.models;

public class YeuThich {
    String IDYeuThich;
    String IDBaiViet;
    String IDNGuoiDung;

    public YeuThich(String IDYeuThich, String IDBaiViet, String IDNGuoiDung) {
        this.IDYeuThich = IDYeuThich;
        this.IDBaiViet = IDBaiViet;
        this.IDNGuoiDung = IDNGuoiDung;
    }

    public YeuThich() {
    }

    public String getIDYeuThich() {
        return IDYeuThich;
    }

    public void setIDYeuThich(String IDYeuThich) {
        this.IDYeuThich = IDYeuThich;
    }

    public String getIDBaiViet() {
        return IDBaiViet;
    }

    public void setIDBaiViet(String IDBaiViet) {
        this.IDBaiViet = IDBaiViet;
    }

    public String getIDNGuoiDung() {
        return IDNGuoiDung;
    }

    public void setIDNGuoiDung(String IDNGuoiDung) {
        this.IDNGuoiDung = IDNGuoiDung;
    }
}
