package com.hoangtuan.nhht.w05;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRed extends Fragment implements FragmentCallbacks {

    MainActivity main;
    TextView txtRed;
    TextView txtFullName;
    TextView txtClass;
    TextView txtAVG;
    Button btnFirst;
    Button btnPrevious;
    Button btnNext;
    Button btnLast;
    int currentPos;

    public static FragmentRed newInstance(String strArg1){
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("arg1",strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)){
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }

        main = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(R.layout.layout_red,null);
        txtRed = (TextView) view_layout_red.findViewById(R.id.textView1Red);
        txtFullName = (TextView) view_layout_red.findViewById(R.id.txtFullName);
        txtClass = (TextView) view_layout_red.findViewById(R.id.txtClass);
        txtAVG = (TextView) view_layout_red.findViewById(R.id.txtAVG);
        btnFirst = (Button) view_layout_red.findViewById(R.id.btnFirst);
        btnLast = (Button) view_layout_red.findViewById(R.id.btnLast);
        btnNext = (Button) view_layout_red.findViewById(R.id.btnNext);
        btnPrevious = (Button) view_layout_red.findViewById(R.id.btnPrevious);

        try{
            Bundle arguments = getArguments();
            txtRed.setText(arguments.getString("arg1",""));

        }catch (Exception ex){
            Log.e("RED BUNDLE ERROR – ", "" + ex.getMessage());
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onCommandFromFragToMain("next");
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onCommandFromFragToMain("previous");
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onCommandFromFragToMain("first");
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onCommandFromFragToMain("last");
            }
        });

        return view_layout_red;
    }


    @Override
    public void onMsgFromMainToFragment(Student student, String msg) {
        txtRed.setText(student.getId());
        txtFullName.setText("Ho ten: " + student.getFullName());
        txtClass.setText("Lop: " + student.getClassName());
        txtAVG.setText("Diem trung binh: " + student.getAvg());

        if (msg.equals("bottom")){
            btnNext.setAlpha(0.2f);
            btnLast.setAlpha(0.2f);
            btnFirst.setAlpha(1.0f);
            btnPrevious.setAlpha(1.0f);

            btnLast.setClickable(false);
            btnNext.setClickable(false);
            btnFirst.setClickable(true);
            btnPrevious.setClickable(true);
            return;
        }

        if (msg.equals("top")){
            btnFirst.setAlpha(0.2f);
            btnPrevious.setAlpha(0.2f);
            btnNext.setAlpha(1.0f);
            btnLast.setAlpha(1.0f);

            btnFirst.setClickable(false);
            btnPrevious.setClickable(false);
            btnLast.setClickable(true);
            btnNext.setClickable(true);
            return;
        }

        btnNext.setAlpha(1.0f);
        btnLast.setAlpha(1.0f);
        btnFirst.setAlpha(1.0f);
        btnPrevious.setAlpha(1.0f);

        btnLast.setClickable(true);
        btnNext.setClickable(true);
        btnFirst.setClickable(true);
        btnPrevious.setClickable(true);
    }

    @Override
    public void onCommandFromMainToFragment(String str) {


    }
}
