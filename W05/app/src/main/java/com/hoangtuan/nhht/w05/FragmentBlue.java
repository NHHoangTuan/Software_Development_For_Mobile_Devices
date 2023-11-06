package com.hoangtuan.nhht.w05;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class FragmentBlue extends Fragment implements FragmentCallbacks{

    final int MAXSTUDENTNUMBER = 30;
    MainActivity main;
    CustomAdapter adapter;
    Context context = null;
    ListView listView;
    TextView txtBlue;
    String message = "";
    int currentPos = -1;
    // data to fill up the list view

    private Student[] students = new Student[MAXSTUDENTNUMBER];
    private String[] items = new String[MAXSTUDENTNUMBER];
    private int[] avatar = new int[MAXSTUDENTNUMBER];

    private int[]imageResource = {
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h
    };


    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)

    public static FragmentBlue newInstance (String strArg){
        FragmentBlue fragment = new FragmentBlue();
        Bundle args = new Bundle();
        args.putString("strArg1",strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try{
            context = getActivity();
            main = (MainActivity) getActivity();
        }
        catch (IllegalStateException ex){
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
        createData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue, null);

        txtBlue = (TextView) layout_blue.findViewById(R.id.textView1Blue);
        listView = (ListView) layout_blue.findViewById(R.id.listView1Blue);
        listView.setBackgroundColor(Color.parseColor("#f5e4da"));


        adapter = new CustomAdapter(context, items, avatar);
        listView.setAdapter(adapter);

        // show listview on the top
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>=0 ||i<items.length) {
                    String msg = "none";
                    if (i == 0) msg = "top";
                    else if (i == items.length - 1) msg = "bottom";
                    main.onMsgFromFragToMain(students[i], msg);
                    txtBlue.setText("ID: " + items[i]);

                    adapter.setSelectedPosition(i);

                    view.setSelected(true);
                    currentPos = i;
                }
            }
        });
        return layout_blue;
    }

    private void createData() {
        int totalItem = MAXSTUDENTNUMBER;
        for (int i = 0; i < totalItem; i++) {
            students[i] = Student.randomStudent();
        }

        for (int i = 0; i < totalItem; i++) {
            items[i] = students[i].getId();
        }

        Random random = new Random();

        for (int i = 0; i < totalItem; i++) {
            avatar[i] = imageResource[random.nextInt(imageResource.length)];
        }
    }


    @Override
    public void onMsgFromMainToFragment(Student student, String msg) {

    }

    @Override
    public void onCommandFromMainToFragment(String str) {
        int index = currentPos;

        if (str.equals("next")) index++;
        if (str.equals("first")) index = 0;
        if (str.equals("last")) index = items.length - 1;
        if (str.equals("previous")) index--;

        if (index >= items.length || index < 0) {
            return;
        }

        listView.setItemChecked(index, true);
        listView.smoothScrollToPosition(index);

        String msg = "none";
        if (index == 0) msg = "top";
        else if (index == items.length - 1) msg = "bottom";
        main.onMsgFromFragToMain(students[index],msg);

        txtBlue.setText("ID: " + items[index]);

        adapter.setSelectedPosition(index);

        currentPos = index;
    }
}
