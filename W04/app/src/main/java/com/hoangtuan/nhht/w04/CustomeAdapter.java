package com.hoangtuan.nhht.w04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomeAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contact>data;
    LayoutInflater inflater;
    public CustomeAdapter(Context context, ArrayList<Contact> data){
        super();
        this.context=context;
        this.data=data;
        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return data.size();
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
        view=inflater.inflate(R.layout.activity_listview,null);
        TextView fullName=(TextView) view.findViewById(R.id.fullname);
        TextView phoneNumber=(TextView) view.findViewById(R.id.phoneNumber);
        ImageView icon=(ImageView) view.findViewById(R.id.icon);

        fullName.setText(data.get(i).getFullname());
        phoneNumber.setText(data.get(i).getPhoneNumber());
        icon.setImageResource(data.get(i).getImgResource());
        return view;
    }
}