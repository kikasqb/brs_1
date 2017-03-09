package com.example.pccuong.appbook.View.listbooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.pccuong.appbook.Adapter.AdapterTopTuNhien;
import com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.listbooksprenter.PresenteListBooks;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.example.pccuong.appbook.model.ObjectClass.ILoadMore;
import com.example.pccuong.appbook.model.ObjectClass.LoadMoreCroll;

import java.util.List;

/**
 * Created by PCCuong on 3/2/2017.
 */

public class ListProductBooks extends Fragment implements ViewListBooks, View.OnClickListener, ILoadMore {
    RecyclerView recyclerView;
    Button btnThayDoiTrangThai;
    boolean kiemtraGrid = true;
    RecyclerView.LayoutManager layoutManager;
    int id;
    ProgressBar progressBar;
    PresenteListBooks presenteListBooks;
    boolean kiemtra;
    AdapterTopTuNhien adapterTopTuNhien;
    Toolbar toolbar;
    List<Books> bookses1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_books, container, false);
        setHasOptionsMenu(false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThai = (Button) view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        Bundle bundle = getArguments();
        id = bundle.getInt("id", 0);
        String nhaxuatban = bundle.getString("tennhaxuatban");
        kiemtra = bundle.getBoolean("kiemtra", false);

        presenteListBooks = new PresenteListBooks(this);
        presenteListBooks.GetListBooks(id, kiemtra);
        recyclerView.addOnScrollListener(new LoadMoreCroll(layoutManager, this));
        btnThayDoiTrangThai.setOnClickListener(this);


        toolbar.setTitle(nhaxuatban);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("HomePageActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menutrangchu, menu);
    }



    @Override
    public void ViewDisplayBooks(List<Books> bookses) {
        bookses1 = bookses;
        if (kiemtraGrid) {
            layoutManager = new GridLayoutManager(getContext(), 2);
            adapterTopTuNhien = new AdapterTopTuNhien(getContext(), R.layout.custom_layout_must_reading_book, bookses1);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
            adapterTopTuNhien = new AdapterTopTuNhien(getContext(), R.layout.custom_layout_list_tunhien, bookses1);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopTuNhien);
        adapterTopTuNhien.notifyDataSetChanged();

    }


    @Override
    public void ErroListBooks() {

    }

    @Override
    public void onClick(View v) {
        int ma = v.getId();
        switch (ma) {
            case R.id.btnThayDoiTrangThaiRecycler:
                kiemtraGrid = !kiemtraGrid;
                presenteListBooks.GetListBooks(id, kiemtra);
                Log.d("kocoketqua", "ketquamorui")
                ;
                break;
        }

    }

    @Override
    public void LoadItem(int itemLoad) {
        List<Books> ListBooksLoadMore = presenteListBooks.GetLoadMore(id, kiemtra, itemLoad, progressBar);
        bookses1.addAll(ListBooksLoadMore);
        adapterTopTuNhien.notifyDataSetChanged();
    }
}
