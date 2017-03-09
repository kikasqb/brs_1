package com.example.pccuong.appbook.model.ObjectClass;

import java.util.List;

/**
 * Created by PCCuong on 2/12/2017.
 */

public class TuNhien {
    List<NhaXuatBan> nhaXuatBans;
    boolean kiemtra;

    public boolean isKiemtra() {
        return kiemtra;
    }

    public void setKiemtra(boolean kiemtra) {
        this.kiemtra = kiemtra;
    }

    public List<Books> getBookses() {
        return bookses;
    }

    public void setBookses(List<Books> bookses) {
        this.bookses = bookses;
    }

    List<Books> bookses;
    String imgeHinhSanPham;

    public List<NhaXuatBan> getNhaXuatBans() {
        return nhaXuatBans;
    }

    public void setNhaXuatBans(List<NhaXuatBan> nhaXuatBans) {
        this.nhaXuatBans = nhaXuatBans;
    }

    public String getImgeHinhSanPham() {
        return imgeHinhSanPham;
    }

    public void setImgeHinhSanPham(String imgeHinhSanPham) {
        this.imgeHinhSanPham = imgeHinhSanPham;
    }
}
