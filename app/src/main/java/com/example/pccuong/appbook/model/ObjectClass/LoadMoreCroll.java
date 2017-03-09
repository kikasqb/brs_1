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
    int itemSum = 0;
    int itemLoastFirst = 10;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreCroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;

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
            iLoadMore.LoadItem(itemSum);
          //  Log.d("kiemtraloadMore", itemSum + "-" + itemFirst);

        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
