package com.example.pccuong.appbook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pccuong.appbook.View.Login.Fragment.FragmantLogin;
import com.example.pccuong.appbook.View.Login.Fragment.FragmantRegister;

/**
 * Created by PCCuong on 12/31/2016.
 */

public class ViewPagerLogin  extends FragmentPagerAdapter{
    public ViewPagerLogin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
         switch (position){
             case 0 :
                 FragmantLogin fragmantLogin = new FragmantLogin();
                 return  fragmantLogin;
             case 1:
                 FragmantRegister fragmantRegister = new FragmantRegister();
                 return  fragmantRegister;
               default: return  null;
         }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :

                return  "Đăng Nhập";
            case 1:

                return  "Đăng Ký";
            default: return  null;
        }
    }
}
