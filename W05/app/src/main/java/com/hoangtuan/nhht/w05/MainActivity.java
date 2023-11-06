package com.hoangtuan.nhht.w05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements MainCallbacks {

    FragmentTransaction ft;
    FragmentBlue blueFragment;
    FragmentRed redFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment);
        ft.commit();

        // create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("");
        ft.replace(R.id.main_holder_red, redFragment);
        ft.commit();

    }

    @Override
    public void onMsgFromFragToMain(Student student, String msg) {
        redFragment.onMsgFromMainToFragment(student, msg);
    }


    @Override
    public void onCommandFromFragToMain(String str) {
        blueFragment.onCommandFromMainToFragment(str);
    }
}