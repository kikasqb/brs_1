package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.tunhien;

import android.util.Log;

import com.example.pccuong.appbook.View.HomePage.ViewXuLyTuNhien;
import com.example.pccuong.appbook.model.ObjectClass.NhaXuatBan;
import com.example.pccuong.appbook.model.tunhien.ModelTuNhien;

import java.util.List;

/**
 * Created by PCCuong on 2/12/2017.
 */

public class PresenterLogicTuNhien implements  IPresenterTuNhien {

    ViewXuLyTuNhien viewXuLyTuNhiens;
    ModelTuNhien modelTuNhien;

    public  PresenterLogicTuNhien(ViewXuLyTuNhien viewXuLyTuNhiens){
        this.viewXuLyTuNhiens = viewXuLyTuNhiens;
        modelTuNhien = new ModelTuNhien();

    }
    @Override
    public void LayListTuNhien() {


        List<NhaXuatBan> nhaXuatBans = modelTuNhien.layDanhSachNhaXuatBan();

        int count = nhaXuatBans.size();
        if(count > 0){
            viewXuLyTuNhiens.HienThiTuNhien(nhaXuatBans);
        }
    }
}
