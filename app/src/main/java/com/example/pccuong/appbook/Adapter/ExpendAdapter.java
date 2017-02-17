package com.example.pccuong.appbook.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pccuong.appbook.model.ObjectClass.Categories;
import com.example.pccuong.appbook.model.DataJsonMenu;
import com.example.pccuong.appbook.R;

import java.util.List;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class ExpendAdapter extends BaseExpandableListAdapter {
    Context context;
    TextView textView;
    ImageView imageView;
    List<Categories> categoriesData;

    public ExpendAdapter(Context context, List<Categories> categoriesData) {

        this.context = context;
        this.categoriesData = categoriesData;
        DataJsonMenu dataJsonMenu = new DataJsonMenu();
        int count = categoriesData.size();
        for (int i = 0; i < count; i++) {
            int maloai = categoriesData.get(i).getId();
            categoriesData.get(i).setCategories(dataJsonMenu.getChildPositionMenu(maloai));
        }

    }

    @Override
    public int getGroupCount() {
        return categoriesData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoriesData.get(groupPosition).getCategories().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoriesData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoriesData.get(groupPosition).getCategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return categoriesData.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return categoriesData.get(groupPosition).getCategories().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_group_position, parent, false);

        TextView txtGroupPosition = (TextView) view.findViewById(R.id.textGroupPosition);
        imageView = (ImageView) view.findViewById(R.id.imMenu);

        txtGroupPosition.setText(categoriesData.get(groupPosition).getName());

        int count = categoriesData.get(groupPosition).getCategories().size();
        if (count > 0) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);

        }
        if (isExpanded) {
            imageView.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            imageView.setImageResource(R.drawable.ic_add_black_24dp);
        }


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpendAdapter secondAdapter = new ExpendAdapter(context, categoriesData.get(groupPosition).getCategories());
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
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
