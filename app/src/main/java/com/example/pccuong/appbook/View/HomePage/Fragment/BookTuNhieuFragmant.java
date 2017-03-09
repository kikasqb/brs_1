package com.example.pccuong.appbook.View.HomePage.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccuong.appbook.Adapter.AdapterFragmantTuNhien;
import com.example.pccuong.appbook.Adapter.AdapterTopTuNhien;
import com.example.pccuong.appbook.Adapter.AdapterTuNhien;
import com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.tunhien.PresenterLogicTuNhien;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.HomePage.ViewXuLyTuNhien;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.ObjectClass.NhaXuatBan;
import com.example.pccuong.appbook.model.ObjectClass.TuNhien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCCuong on 1/10/2017.
 */

public class BookTuNhieuFragmant  extends Fragment implements ViewXuLyTuNhien {
    RecyclerView recyclerViews;
    List<TuNhien> tuNhienList;
    PresenterLogicTuNhien presenterLogicTuNhien;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tunhien, container, false);
        recyclerViews = (RecyclerView) view.findViewById(R.id.listNhaXuatBan);
        tuNhienList = new ArrayList<>();
        presenterLogicTuNhien = new PresenterLogicTuNhien(this);
        presenterLogicTuNhien.LayListTuNhien();
        return view;
    }

    @Override
    public void HienThiTuNhien(List<TuNhien> tuNhiens) {
          tuNhienList = tuNhiens;
        AdapterTuNhien adapterTuNhien = new AdapterTuNhien(getContext(),tuNhienList);
        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(getContext());
        recyclerViews.setLayoutManager(layoutManager);
        recyclerViews.setAdapter(adapterTuNhien);
        adapterTuNhien.notifyDataSetChanged();

    }


}
