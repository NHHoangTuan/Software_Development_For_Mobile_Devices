package com.hoangtuan.nhht.w08;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangtuan.nhht.w08.R;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomAdapter extends BaseAdapter {

    Context context;
    //String[] items;
    //int[] icons;
    Student[]students;
    LayoutInflater inflater;

    private int selectedPosition = -1;
    public CustomAdapter(Context context, Student[] students){
        super();
        this.context = context;
        this.students = students;
        //this.items = items;
        //this.icons = icons;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return students.length;
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

        id.setText(students[i].getStudentID());
        icon.setImageResource(students[i].getThumnails());

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