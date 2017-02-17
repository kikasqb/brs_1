package com.example.pccuong.appbook.View.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.pccuong.appbook.Adapter.ExpendAdapter;
import com.example.pccuong.appbook.Adapter.ViewBagerHomePage;
import com.example.pccuong.appbook.model.LoginBook;
import com.example.pccuong.appbook.model.ObjectClass.Categories;
import com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.PresenteLogicXuLyMenu;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.Login.LoginActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by PCCuong on 11/30/2016.
 */

public class HomePageActivity extends AppCompatActivity implements ViewXuLyMenu {

    ViewPager viewPager;
    TabLayout tabLayout;
    Menu menu;
    MenuItem itemDangNhap, itemDangXuat;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenteLogicXuLyMenu logicXuLyMenu;
    AccessToken accessToken;
    LoginBook loginBook;
    String tennguoidung = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.home_page_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        expandableListView = (ExpandableListView) findViewById(R.id.edMenu);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        viewPager = (ViewPager) findViewById(R.id.tabviewhomepage);
        tabLayout = (TabLayout) findViewById(R.id.tablayoutList);
        ViewBagerHomePage viewBagerHomePage = new ViewBagerHomePage(getSupportFragmentManager());
        viewPager.setAdapter(viewBagerHomePage);
        viewBagerHomePage.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        logicXuLyMenu = new PresenteLogicXuLyMenu(this);
        logicXuLyMenu.layDanhSachMenu();

        logicXuLyMenu.layTenNguoiDungFacebook();

        loginBook = new LoginBook();
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
        itemDangNhap = menu.findItem(R.id.itdangnhap);
        itemDangXuat = menu.findItem(R.id.itdangxuat);
        accessToken = logicXuLyMenu.layTenNguoiDungFacebook();
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        MenuItem menuItem = menu.findItem(R.id.itdangnhap);
                        menuItem.setTitle(tennguoidung);
                        Log.d("KiemtraTENNGUOIDUNG", tennguoidung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            Bundle bundle = new Bundle();
            bundle.putString("fields", "name");
            graphRequest.setParameters(bundle);
            graphRequest.executeAsync();
            Log.d("KiemtraACCESSTOCKEN", accessToken.toString());
            Log.d("KiemtraTENNGUOIDUNG", tennguoidung);
        }


        String name = loginBook.layCaheRerferences(this);
        if (!name.equals("")) {
            itemDangNhap.setTitle(name);

        }

        if (accessToken != null || !name.equals("")) {
            MenuItem menuItemDangXuat = menu.findItem(R.id.itdangxuat);
            menuItemDangXuat.setVisible(true);

        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.itdangnhap:
                if (accessToken == null) {
                    Intent intentLogin = new Intent(this, LoginActivity.class);
                    startActivity(intentLogin);
                }
                ;
                break;
            case R.id.itdangxuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (!loginBook.layCaheRerferences(this).equals("")) {
                    loginBook.upDataLogin(this, "");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;

        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<Categories> categoriesList) {
        ExpendAdapter expendAdapter = new ExpendAdapter(this, categoriesList);
        expandableListView.setAdapter(expendAdapter);
        expendAdapter.notifyDataSetChanged();

    }
}
