package com.example.pccuong.appbook.View.trangchitiet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pccuong.appbook.R;
import com.squareup.picasso.Picasso;

/**
 * Created by PCCuong on 3/3/2017.
 */

public class FragmantSlider extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmant_slider,container,false);
        Bundle bundle = getArguments();
        String linkHinh = bundle.getString("imagenho");
        ImageView imageView = (ImageView) view.findViewById(R.id.imHinhSlider);
        Picasso.with(getContext()).load(linkHinh).into(imageView);
        return view;
    }
}
