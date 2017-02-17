package com.example.pccuong.appbook.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.model.ObjectClass.NhaXuatBan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PCCuong on 2/11/2017.
 */

public class AdapterFragmantTuNhien extends RecyclerView.Adapter<AdapterFragmantTuNhien.ViewHolderNhaXuatBan> {
    Context context;
    List<NhaXuatBan> nhaXuatBanList;

    public AdapterFragmantTuNhien(Context context, List<NhaXuatBan> nhaXuatBanList) {
        this.context = context;
        this.nhaXuatBanList = nhaXuatBanList;

    }

    public class ViewHolderNhaXuatBan extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ProgressBar progressBar;


        public ViewHolderNhaXuatBan(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtTieuDeNhaSach);
            imageView = (ImageView) itemView.findViewById(R.id.idLogoNhaSach);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_dowload);
        }
    }

    @Override
    public ViewHolderNhaXuatBan onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycleview_tunhien_con, parent, false);


        ViewHolderNhaXuatBan viewHolderNhaXuatBan = new ViewHolderNhaXuatBan(view);


        return viewHolderNhaXuatBan;
    }

    @Override
    public void onBindViewHolder(final ViewHolderNhaXuatBan holder, int position) {

        NhaXuatBan nhaXuatBan = nhaXuatBanList.get(position);
        holder.textView.setText(nhaXuatBan.getTenSanPham());

        Picasso.with(context).load(nhaXuatBan.getLinkHinh()).resize(150, 150).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public int getItemCount() {
        return nhaXuatBanList.size();
    }


}
