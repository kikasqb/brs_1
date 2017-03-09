package com.example.pccuong.appbook.View.timkiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pccuong.appbook.Adapter.AdapterTopTuNhien;
import com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.timkiem.PresenterTimKiem;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.ObjectClass.ILoadMore;
import com.example.pccuong.appbook.model.ObjectClass.LoadMoreCroll;

import java.util.List;

/**
 * Created by PCCuong on 3/5/2017.
 */

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, ILoadMore, SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterTimKiem presenterTimKiem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_timkiem);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerTimKiem);
         toolbar.setTitle("");
        setSupportActionBar(toolbar);

        presenterTimKiem = new PresenterTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem, menu);
        MenuItem icSearh = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(icSearh);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void TimKiemThanhCong(List<Books> bookses) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopTuNhien adapterTopDienThoaiDienTu = new AdapterTopTuNhien(this, R.layout.custom_layout_list_tunhien, bookses);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreCroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadItem(int itemLoad) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterTimKiem.TimKiemTheoTen(query, 0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
