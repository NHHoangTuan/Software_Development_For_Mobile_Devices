package com.hoangtuan.nhht.w05;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangtuan.nhht.w05.R;
import com.hoangtuan.nhht.w05.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] items;
    int[] icons;
    LayoutInflater inflater;

    private int selectedPosition = -1;
    public CustomAdapter(Context context, String[] items, int[] icons){
        super();
        this.context = context;
        this.items = items;
        this.icons = icons;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview,null);

        TextView id = (TextView) view.findViewById(R.id.txtID);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);

        id.setText(items[i]);
        icon.setImageResource(icons[i]);

        if (i == selectedPosition) {
            view.setBackgroundColor(Color.parseColor("#b0d9f5"));
        } else {
            view.setBackgroundColor(Color.parseColor("#f5e4da"));
        }

        return view;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }
}