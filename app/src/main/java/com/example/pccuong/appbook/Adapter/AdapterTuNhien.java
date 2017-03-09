package com.example.pccuong.appbook.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.model.ObjectClass.NhaXuatBan;
import com.example.pccuong.appbook.model.ObjectClass.TuNhien;

import java.util.List;

/**
 * Created by PCCuong on 2/12/2017.
 */

public class AdapterTuNhien extends RecyclerView.Adapter<AdapterTuNhien.ViewHolderTuNhien> {

    Context context;
    List<TuNhien> tuNhienList;

    public AdapterTuNhien(Context context, List<TuNhien> tuNhienList) {
        this.context = context;
        this.tuNhienList = tuNhienList;

    }

    public class ViewHolderTuNhien extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewNhaXuatBan, recyclerViewTopTuNhien;
        ImageView imageViewNhaXuatBan;

        public ViewHolderTuNhien(View itemView) {
            super(itemView);
            imageViewNhaXuatBan = (ImageView) itemView.findViewById(R.id.imgeNhaXuatBan);
            recyclerViewNhaXuatBan = (RecyclerView) itemView.findViewById(R.id.idNhaXuatBan);
            recyclerViewTopTuNhien = (RecyclerView) itemView.findViewById(R.id.idSachhayDoc);
        }
    }

    @Override
    public AdapterTuNhien.ViewHolderTuNhien onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewNhaXuatBan = inflater.inflate(R.layout.custom_recycleview_tunhien, parent, false);
        ViewHolderTuNhien viewHolderTuNhien = new ViewHolderTuNhien(viewNhaXuatBan);
        return viewHolderTuNhien;
    }

    @Override
    public void onBindViewHolder(AdapterTuNhien.ViewHolderTuNhien holder, int position) {
        TuNhien tuNhiens = tuNhienList.get(position);
        AdapterFragmantTuNhien adapterFragmantTuNhien = new AdapterFragmantTuNhien(context, tuNhiens.getNhaXuatBans(),tuNhiens.isKiemtra());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        holder.recyclerViewNhaXuatBan.setLayoutManager(layoutManager);
        holder.recyclerViewNhaXuatBan.setAdapter(adapterFragmantTuNhien);
        adapterFragmantTuNhien.notifyDataSetChanged();

        AdapterTopTuNhien adapterTopTuNhien = new AdapterTopTuNhien(context, R.layout.custom_layout_must_reading_book,tuNhiens.getBookses());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerViewTopTuNhien.setLayoutManager(layoutManager1);
        holder.recyclerViewTopTuNhien.setAdapter(adapterTopTuNhien);


    }

    @Override
    public int getItemCount() {
        return tuNhienList.size();
    }


}
