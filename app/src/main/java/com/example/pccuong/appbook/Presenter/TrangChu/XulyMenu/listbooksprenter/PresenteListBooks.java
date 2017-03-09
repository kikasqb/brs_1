package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.listbooksprenter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pccuong.appbook.View.listbooks.ViewListBooks;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.displaylistbooks.GetListBooks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCCuong on 3/2/2017.
 */

public class PresenteListBooks implements IPresenteListBook {
    ViewListBooks viewListBooks;
    GetListBooks getListBooks;

    public PresenteListBooks(ViewListBooks viewListBooks) {

        this.viewListBooks = viewListBooks;
        getListBooks = new GetListBooks();
    }


    @Override
    public void GetListBooks(int id, boolean kiemtra) {
        List<Books> bookses = new ArrayList<>();
        if (kiemtra) {
            bookses = getListBooks.GetProducModel(id, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM", 0);
        } else {
            bookses = getListBooks.GetProducModel(id, "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", 0);
        }

        if (bookses.size() > 0) {
            viewListBooks.ViewDisplayBooks(bookses);
        } else {
            viewListBooks.ErroListBooks();
        }


    }
    public  List<Books>  GetLoadMore(int id, boolean kiemtra, int limit, ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
        List<Books> bookses = new ArrayList<>();
        if (kiemtra) {
            bookses = getListBooks.GetProducModel(id, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM", limit);
        } else {
            bookses = getListBooks.GetProducModel(id, "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", limit);
        }
        if (bookses.size() != 0){
            progressBar.setVisibility(View.GONE);
        }
        return bookses;


    }
}
