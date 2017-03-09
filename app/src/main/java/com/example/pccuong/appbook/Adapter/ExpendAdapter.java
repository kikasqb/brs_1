package com.example.pccuong.appbook.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pccuong.appbook.View.listbooks.ListProductBooks;
import com.example.pccuong.appbook.model.ObjectClass.Categories;
import com.example.pccuong.appbook.model.DataJsonMenu;
import com.example.pccuong.appbook.R;

import java.util.List;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class ExpendAdapter extends BaseExpandableListAdapter {


    Context context;
    List<Categories> categories;
    ViewHolderMenu viewHolderMenu;
    ListProductBooks listProductBooks;

    public ExpendAdapter(Context context, List<Categories> categories) {
        this.context = context;
        this.categories = categories;

        DataJsonMenu xuLyJSONMenu = new DataJsonMenu();

        int count = categories.size();
        for (int i = 0; i < count; i++) {
            int id = categories.get(i).getId();
            categories.get(i).setCategories(xuLyJSONMenu.getChildPositionMenu(id));
        }


    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if (categories.get(vitriGroupCha).getCategories().size() != 0) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return categories.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return categories.get(vitriGroupCha).getCategories().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return categories.get(vitriGroupCha).getId();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return categories.get(vitriGroupCha).getCategories().get(vitriGroupCon).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu {
        TextView txtTenLoaiSP;
        ImageView hinhMenu;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewGroupCha = view;
        if (viewGroupCha == null) {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_position, viewGroup, false);

            viewHolderMenu.txtTenLoaiSP = (TextView) viewGroupCha.findViewById(R.id.textGroupPosition);
            viewHolderMenu.hinhMenu = (ImageView) viewGroupCha.findViewById(R.id.imMenu);

            viewGroupCha.setTag(viewHolderMenu);
        } else {
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(categories.get(vitriGroupCha).getName());

        int demsanphamcon = categories.get(vitriGroupCha).getCategories().size();

        if (demsanphamcon > 0) {
            viewHolderMenu.hinhMenu.setVisibility(View.VISIBLE);
        } else {
            viewHolderMenu.hinhMenu.setVisibility(View.INVISIBLE);
        }

        if (isExpanded) {
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_remove_black_24dp);
            //    viewGroupCha.setBackgroundResource(R.color.colorGray);
        } else {
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_add_black_24dp);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("idbooks", categories.get(vitriGroupCha).getName() + " - " + categories.get(vitriGroupCha).getId());
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                listProductBooks = new ListProductBooks();

                Bundle bundle = new Bundle();

                bundle.putInt("id", categories.get(vitriGroupCha).getId());

                bundle.putString("tennhaxuatban", categories.get(vitriGroupCha).getName());
                bundle.putBoolean("kiemtra", false);

                listProductBooks.setArguments(bundle);
                fragmentTransaction.addToBackStack("HomePageActivity");
                fragmentTransaction.replace(R.id.themFragmant, listProductBooks);
                fragmentTransaction.commit();


                return false;
            }
        });

        return viewGroupCha;
    }


    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewGroupCon = layoutInflater.inflate(R.layout.custom_layout_group_con,viewGroup,false);
//        ExpandableListView expandableListView = (ExpandableListView) viewGroupCon.findViewById(R.id.epMenuCon);

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpendAdapter secondAdapter = new ExpendAdapter(context, categories.get(vitriGroupCha).getCategories());
        secondExpanable.setAdapter(secondAdapter);

        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpanable;
    }

    public class SecondExpanable extends ExpandableListView {

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;
            Log.d("size", width + " - " + height);

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

//    Context context;
//    TextView textView;
//    ImageView imageView;
//    List<Categories> categoriesData;
//
//    public ExpendAdapter(Context context, List<Categories> categoriesData) {
//        this.context = context;
//        this.categoriesData = categoriesData;
//        DataJsonMenu dataJsonMenu = new DataJsonMenu();
//        int count = categoriesData.size();
//        for (int i = 0; i < count; i++) {
//            int id = categoriesData.get(i).getId();
//            categoriesData.get(i).setCategories(dataJsonMenu.getChildPositionMenu(id));
//        }
//
//    }
//
//    @Override
//    public int getGroupCount() {
//        return categoriesData.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return categoriesData.get(groupPosition).getCategories().size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return categoriesData.get(groupPosition);
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return categoriesData.get(groupPosition).getCategories().get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return categoriesData.get(groupPosition).getId();
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return categoriesData.get(groupPosition).getCategories().get(childPosition).getId();
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//
//
//
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.custom_layout_group_position, parent, false);
//
//        TextView txtGroupPosition = (TextView) view.findViewById(R.id.textGroupPosition);
//        imageView = (ImageView) view.findViewById(R.id.imMenu);
//
//        txtGroupPosition.setText(categoriesData.get(groupPosition).getName());
//
//        int count = categoriesData.get(groupPosition).getCategories().size();
//        if (count > 0) {
//            imageView.setVisibility(View.VISIBLE);
//        } else {
//            imageView.setVisibility(View.INVISIBLE);
//
//        }
//        if (isExpanded) {
//            imageView.setImageResource(R.drawable.ic_remove_black_24dp);
//        } else {
//            imageView.setImageResource(R.drawable.ic_add_black_24dp);
//        }
//
//
//        return view;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        SecondExpanable secondExpanable = new SecondExpanable(context);
//        ExpendAdapter secondAdapter = new ExpendAdapter(context, categoriesData.get(groupPosition).getCategories());
//        secondExpanable.setAdapter(secondAdapter);
//
//        secondExpanable.setGroupIndicator(null);
//        notifyDataSetChanged();
//
//        return secondExpanable;
//    }
//
//    public class SecondExpanable extends ExpandableListView {
//
//        public SecondExpanable(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            Display display = windowManager.getDefaultDisplay();
//            Point size = new Point();
//            display.getSize(size);
//            int width = size.x;
//            int height = size.y;
//            Log.d("size", width + " - " + height);
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return false;
//    }
}
