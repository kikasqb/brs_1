package com.example.pccuong.appbook.model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by PCCuong on 3/3/2017.
 */

public class LoadMoreCroll extends RecyclerView.OnScrollListener {
    int itemFirst = 0;
    int itemLoastFirst = 6;
    int itemSum = 0;
    RecyclerView.LayoutManager layoutManager;

    public LoadMoreCroll(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        itemSum = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            itemFirst = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            itemFirst = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if (itemSum <= (itemFirst + itemLoastFirst)) {
            Log.d("kiemtraloadMore", itemSum + "-" + itemFirst);

        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
