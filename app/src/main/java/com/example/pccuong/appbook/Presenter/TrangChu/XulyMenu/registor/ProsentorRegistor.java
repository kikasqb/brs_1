package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.registor;

import com.example.pccuong.appbook.View.Login.ViewRegistor;
import com.example.pccuong.appbook.model.ObjectClass.Users;
import com.example.pccuong.appbook.model.login_registor.ModelRegistor;

/**
 * Created by PCCuong on 2/26/2017.
 */

public class ProsentorRegistor implements IPresentorRegistor {

    ViewRegistor viewRegistor;
    ModelRegistor modelRegistor;
    public  ProsentorRegistor(ViewRegistor viewRegistor){
        this.viewRegistor = viewRegistor;
        modelRegistor = new ModelRegistor();
    }
    @Override
    public void ThucHienDangKy(Users users) {
        boolean kiemtra = modelRegistor.DangKyThanhVien(users);
        if(kiemtra){
            viewRegistor.DangKyThangCong();
        }else{
            viewRegistor.DangKyThatBai();
        }
    }
}
