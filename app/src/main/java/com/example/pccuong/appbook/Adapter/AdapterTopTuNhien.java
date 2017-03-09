package com.example.pccuong.appbook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.trangchitiet.ChiTietBooks;
import com.example.pccuong.appbook.model.ObjectClass.Books;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PCCuong on 3/1/2017.
 */

public class AdapterTopTuNhien extends RecyclerView.Adapter<AdapterTopTuNhien.ViewHolderTopTuNhien> {
    Context context;
    List<Books> bookses;
    int layoutList;

    public AdapterTopTuNhien(Context context, int layoutList, List<Books> bookses) {
        this.context = context;
        this.bookses = bookses;
        this.layoutList = layoutList;
    }


    public class ViewHolderTopTuNhien extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;


        public ViewHolderTopTuNhien(View itemView) {


            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imTopTuNhien);
            textView = (TextView) itemView.findViewById(R.id.txtTieuDeTopTuNhien);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }

    @Override
    public ViewHolderTopTuNhien onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutList, parent, false);
        ViewHolderTopTuNhien viewHolderTopTuNhien = new ViewHolderTopTuNhien(view);
        return viewHolderTopTuNhien;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopTuNhien holder, int position) {
        Books books = bookses.get(position);
        Picasso.with(context).load(books.getImagebig()).resize(300, 400).centerInside().into(holder.imageView);
        holder.textView.setText(books.getTitle());

        holder.cardView.setTag(books.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentChiTiet = new Intent(context, ChiTietBooks.class);
                intentChiTiet.putExtra("id",(int)v.getTag());
                context.startActivity(intentChiTiet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookses.size();
    }


}
