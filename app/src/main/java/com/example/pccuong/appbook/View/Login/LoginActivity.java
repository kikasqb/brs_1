package com.example.pccuong.appbook.View.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.example.pccuong.appbook.Adapter.ViewPagerLogin;
import com.example.pccuong.appbook.R;

/**
 * Created by PCCuong on 12/31/2016.
 */

public class LoginActivity extends AppCompatActivity {
    TabLayout tableLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        tableLayout = (TabLayout) findViewById(R.id.tablogin);
        viewPager = (ViewPager) findViewById(R.id.tabviewlogin);
        toolbar = (Toolbar) findViewById(R.id.tollbarLogin);
        setSupportActionBar(toolbar);


        ViewPagerLogin viewPagerLogin = new ViewPagerLogin(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerLogin);
        viewPagerLogin.notifyDataSetChanged();
        tableLayout.setupWithViewPager(viewPager);


    }
}
