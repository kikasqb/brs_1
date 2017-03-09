package com.example.pccuong.appbook.model.ObjectClass;

/**
 * Created by PCCuong on 2/11/2017.
 */

public class NhaXuatBan {
    private  int id;
    private   String tenSanPham,linkHinh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }
}
