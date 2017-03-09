package com.example.pccuong.appbook.View.trangchitiet;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pccuong.appbook.Adapter.AdapterFragmantSlider;
import com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.chitietbooks.PrecenterLogicChiTiet;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.HomePage.HomePageActivity;
import com.example.pccuong.appbook.model.ObjectClass.Books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCCuong on 3/3/2017.
 */

public class ChiTietBooks extends AppCompatActivity implements ViewTrangChiTiet, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    Toolbar toolbar;
    PrecenterLogicChiTiet precenterLogicChiTiet;
    TextView txtTenSanPham,txtThongTinChiTiet;
    List<Fragment> fragments;
    TextView[] txtDots;
    ImageView imXemThemChiTiet;
    LinearLayout layoutDots;
    boolean kiemtraxochitiet = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiet_books);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        txtTenSanPham = (TextView) findViewById(R.id.txtTenSanPham);
        viewPager = (ViewPager) findViewById(R.id.viewpagerSlider);
        txtThongTinChiTiet = (TextView) findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet = (ImageView) findViewById(R.id.imXemThemChiTiet);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int id = getIntent().getIntExtra("id", 0);
        precenterLogicChiTiet = new PrecenterLogicChiTiet(this);
        precenterLogicChiTiet.GetChiTietSanPham(id);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void HienThiTrangChiTiet(final Books books) {
        txtTenSanPham.setText(books.getTitle());
        if(books.getCover().length() < 100){
            imXemThemChiTiet.setVisibility(View.GONE);
        }else{
            imXemThemChiTiet.setVisibility(View.VISIBLE);

            imXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraxochitiet = !kiemtraxochitiet;
                    if(kiemtraxochitiet){

                        txtThongTinChiTiet.setText(books.getCover());
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));

                    }else{

                        txtThongTinChiTiet.setText(books.getCover().substring(0,100));
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));

                    }
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void HineThiSliderChitet(String[] linkimage) {
        fragments = new ArrayList<>();
        for (int i = 0; i < linkimage.length; i++) {
            FragmantSlider fragmantSlider = new FragmantSlider();
            Bundle bundle = new Bundle();
            bundle.putString("imagenho", HomePageActivity.SEVER_NAME + linkimage[i]);
            fragmantSlider.setArguments(bundle);
            fragments.add(fragmantSlider);
        }
        AdapterFragmantSlider adapterFragmantSlider = new AdapterFragmantSlider(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapterFragmantSlider);
        adapterFragmantSlider.notifyDataSetChanged();
    }


    private void ThemDotSlider(int vitrihientai) {
        txtDots = new TextView[fragments.size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < fragments.size(); i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));
            layoutDots.addView(txtDots[i]);
        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }
    private Drawable getHinhChiTiet(int idDrawable){
        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }else{
            drawable = getResources().getDrawable(idDrawable);
        }

        return drawable;
    }

    private int getIdColor(int idcolor) {

        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idcolor);
        } else {
            color = getResources().getColor(idcolor);
        }

        return color;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
