package com.hoangtuan.nhht.w08;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class FragmentBlue extends Fragment implements FragmentCallbacks {

    MainActivity main;

    CustomAdapter adapter;
    Context context = null;
    ListView listView;
    TextView txtBlue;
    String message = "";
    int currentPos = -1;
    // data to fill up the list view

    private Student[] students = new Student[5];
    private Class[] classes = new Class[1];


    public Student[] getStudents(){
        return this.students;
    }

    public Class[] getClasses(){
        return this.classes;
    }



    public void getDataFromDataBase(){
        SQLiteDatabase database = main.getDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM HOCSINH", null);
        cursor.moveToPosition(-1);
        int i = 0;
        while(cursor.moveToNext()){
            students[i] = new Student(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getFloat(3));
            i++;
        }

        students[0].setThumnails(R.drawable.a);
        students[1].setThumnails(R.drawable.b);
        students[2].setThumnails(R.drawable.c);
        students[3].setThumnails(R.drawable.a);
        students[4].setThumnails(R.drawable.b);

        cursor = database.rawQuery("SELECT * FROM LOPHOC", null);
        cursor.moveToPosition(-1);
        i = 0;
        while (cursor.moveToNext()){
            classes[i] = new Class(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
            i++;
        }

        main.setStudents(students);
        main.setClasses(classes);

    }

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
            getDataFromDataBase();
        }
        catch (IllegalStateException ex){
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
        //createData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue, null);

        txtBlue = (TextView) layout_blue.findViewById(R.id.textView1Blue);
        listView = (ListView) layout_blue.findViewById(R.id.listView1Blue);
        listView.setBackgroundColor(Color.parseColor("#f5e4da"));


        adapter = new CustomAdapter(context, students);
        listView.setAdapter(adapter);

        // show listview on the top
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>=0 ||i<students.length) {
                    String msg = "none";
                    if (i == 0) msg = "top";
                    else if (i == students.length - 1) msg = "bottom";
                    main.onMsgFromFragToMain(students[i], msg);
                    txtBlue.setText("ID: " + students[i].getStudentID());

                    adapter.setSelectedPosition(i);

                    view.setSelected(true);
                    currentPos = i;
                }
            }
        });
        return layout_blue;
    }


    @Override
    public void onMsgFromMainToFragment(Student student, String msg) {

    }

    @Override
    public void onCommandFromMainToFragment(String str) {
        int index = currentPos;

        if (str.equals("next")) index++;
        if (str.equals("first")) index = 0;
        if (str.equals("last")) index = students.length - 1;
        if (str.equals("previous")) index--;

        if (index >= students.length || index < 0) {
            return;
        }

        listView.setItemChecked(index, true);
        listView.smoothScrollToPosition(index);

        String msg = "none";
        if (index == 0) msg = "top";
        else if (index == students.length - 1) msg = "bottom";
        main.onMsgFromFragToMain(students[index],msg);

        txtBlue.setText("ID: " + students[index].getStudentID());

        adapter.setSelectedPosition(index);

        currentPos = index;
    }
}
