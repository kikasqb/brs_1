package com.example.pccuong.appbook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by PCCuong on 3/3/2017.
 */

public class AdapterFragmantSlider extends FragmentStatePagerAdapter {

    List<Fragment> fragments;
    public  AdapterFragmantSlider(FragmentManager fragmentManager, List<Fragment> fragments){
        super(fragmentManager);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
