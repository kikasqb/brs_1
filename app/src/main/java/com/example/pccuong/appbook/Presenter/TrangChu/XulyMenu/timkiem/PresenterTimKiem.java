package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.timkiem;

import com.example.pccuong.appbook.View.timkiem.ViewTimKiem;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.timkiem.ModelTimKiem;

import java.util.List;

/**
 * Created by PCCuong on 3/5/2017.
 */

public class PresenterTimKiem implements IPrencenterTimKiem {

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterTimKiem(ViewTimKiem viewTimKiem){
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();

    }
    @Override
    public void TimKiemTheoTen(String nameBooks, int limit) {
        List<Books>  bookses = modelTimKiem.GetTimKiem(nameBooks,"TimKiemSanPhamTheoTenSP","DANHSACHSANPHAM",limit);

         if(bookses.size() > 0){
             viewTimKiem.TimKiemThanhCong(bookses);
         } else {
             viewTimKiem.TimKiemThatBai();
         }

    }
}
