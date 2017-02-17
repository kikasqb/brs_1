package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu;

import android.util.Log;

import com.example.pccuong.appbook.model.LoginBook;
import com.example.pccuong.appbook.model.ObjectClass.Categories;
import com.example.pccuong.appbook.model.DataJsonMenu;
import com.example.pccuong.appbook.View.HomePage.ViewXuLyMenu;
import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class PresenteLogicXuLyMenu implements IPresenteXulyMenu {
    ViewXuLyMenu viewXuLyMenu;


    public PresenteLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void layDanhSachMenu() {
        List<Categories> categoriesList;
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String duongdan = "http://192.168.17.2/Appbook/loaisanpham.php";

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "layListMenu");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id_product","0");

        attrs.add(hashMap);
        attrs.add(hsHam);
        DowloadJSON dowloadJSON = new DowloadJSON(duongdan, attrs);
        dowloadJSON.execute();

        try {
            dataJSON = dowloadJSON.get();
            DataJsonMenu dataJsonMenu = new DataJsonMenu();
            categoriesList = dataJsonMenu.paraseJson(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(categoriesList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("kiemtrachuoimotmach", attrs.toString());
    }

    @Override
    public AccessToken layTenNguoiDungFacebook() {
        LoginBook loginBook = new LoginBook();
        AccessToken accessToken = loginBook.layTockenFacebookRuntime();
        DataJsonMenu dataJsonMenu = new DataJsonMenu();
        return accessToken;
    }

}
