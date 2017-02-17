package com.example.pccuong.appbook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pccuong.appbook.View.HomePage.Fragment.BookTuNhieuFragmant;
import com.example.pccuong.appbook.View.HomePage.Fragment.BookXaHoiFragmant;

/**
 * Created by PCCuong on 1/10/2017.
 */

public class ViewBagerHomePage extends FragmentPagerAdapter{

    public ViewBagerHomePage(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BookTuNhieuFragmant bookTuNhieuFragmant = new BookTuNhieuFragmant();
                return  bookTuNhieuFragmant;
//            case 1:
//                BookXaHoiFragmant bookXaHoiFragmant = new BookXaHoiFragmant();
//                return bookXaHoiFragmant;
            default: return  null;
        }
    }




    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Tự Nhiên";
//            case 1:
//                return "Xã Hội";
            default: return null;
        }


    }
}
