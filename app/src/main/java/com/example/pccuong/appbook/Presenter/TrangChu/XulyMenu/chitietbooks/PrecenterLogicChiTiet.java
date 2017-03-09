package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.chitietbooks;

import com.example.pccuong.appbook.View.trangchitiet.ViewTrangChiTiet;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.chitietbooks.ModelChiTietBooks;

/**
 * Created by PCCuong on 3/3/2017.
 */

public class PrecenterLogicChiTiet  implements  IProcenterChiTiet{

    ViewTrangChiTiet viewTrangChiTiet;
    ModelChiTietBooks modelChiTietBooks;

    public  PrecenterLogicChiTiet(ViewTrangChiTiet viewTrangChiTiet){
        this.viewTrangChiTiet = viewTrangChiTiet;
        modelChiTietBooks = new ModelChiTietBooks();
    }
    @Override
    public void GetChiTietSanPham(int id) {
        Books books = modelChiTietBooks.ChiTietSanPhamBooks("LaySanPhamVsChitietTheoMaSP","CHITIETSANPHAM",id);
        if(books.getId() > 0){
            String [] imageNho = books.getImagenho().split(",");
            viewTrangChiTiet.HineThiSliderChitet(imageNho);
            viewTrangChiTiet.HienThiTrangChiTiet(books);
        }
    }
}
