package com.hoangtuan.nhht.w04;

import android.app.Activity;
import android.support.v4.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    private final int MAX_ROWS_PER_PAGE = 5;
    private ListView list;
    private TextView txtViewChoose;
    private ArrayList<Contact> data;
    private ArrayList<Contact> dataPerPage;
    private Spinner spinner;
    private int totalItems;
    private EditText genEditText;
    private Button genButton;
    private int pageNumbers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list=(ListView) findViewById(R.id.list);
        spinner=(Spinner)findViewById(R.id.page);
        genEditText=(EditText)findViewById(R.id.genEditText);
        genButton=(Button)findViewById(R.id.genButton);
        txtViewChoose=(TextView) findViewById(R.id.txtViewChoose);

        data = new ArrayList<Contact>();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateListView(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                txtViewChoose.setText("You choose: "+dataPerPage.get(i).getFullname());
            }
        });

        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPage();
                txtViewChoose.setText("You choose: ");
            }
        });

    }

    private void createPage(){
        String input = genEditText.getText().toString();
        try{
            totalItems = Integer.parseInt(input);
        }catch (NumberFormatException ex){
            return;
        }
        data.clear();

        pageNumbers = totalItems/5;
        if(totalItems % 5 != 0) {
            pageNumbers++;
        }

        ArrayList<String> page = new ArrayList<>();

        for (int i = 0; i < pageNumbers; i++) {
            page.add(String.valueOf(i+1));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, page);
        //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();


        for (int i = 0; i < totalItems; i++) {
            data.add(new Contact(RandomName(),randomPhoneNumber(),randomIcons()));
        }

        updateListView(0);


    }
    private void updateListView(int pageNumber){
        if(totalItems <= 0){
            data.clear();
            return;
        }

        int startIndex = MAX_ROWS_PER_PAGE * pageNumber;
        int endIndex = Math.min(startIndex+MAX_ROWS_PER_PAGE,totalItems);

        dataPerPage = new ArrayList<>();

        for (int i=startIndex;i<endIndex;i++){
            dataPerPage.add(data.get(i));
        }

        CustomeAdapter adapter = new CustomeAdapter(this,dataPerPage);
        list.setAdapter(adapter);

    }


    private String RandomName() {
        String[] firstNameList = {
                "Nguyen", "Tran", "Le", "Pham", "Huynh", "Ha", "Vo", "Dinh",
                "Bui", "Vu", "Phan", "Dang", "Ho", "Nong"
        };

        String[] MaleNameList = {
                "An", "Binh", "Cuong", "Dung", "Hung", "Khoa", "Linh", "Nam",
                "Quang", "Son", "Thanh", "Trung", "Viet", "Xuan"
        };

        String[] middleNameList = {
                "Van", "Thi", "Thu", "Tien", "Xuan", "Anh"
        };

        String[] FemaleNameList = {
                "Anh", "Bao", "Cam", "Dung", "Hanh", "Lan", "Mai", "Nga",
                "Phuong", "Quyen", "Thao", "Trang", "Uyen", "Xinh"
        };

        Random random = new Random();


        String firstName = firstNameList[random.nextInt(firstNameList.length)];
        String middleName = middleNameList[random.nextInt(middleNameList.length)];
        String lastName;
        if (random.nextBoolean()) {
            lastName = MaleNameList[random.nextInt(MaleNameList.length)];
        } else {
            lastName = FemaleNameList[random.nextInt(FemaleNameList.length)];
        }
        return firstName+" "+middleName+" "+lastName;

    }

    private String randomPhoneNumber(){
        Random random = new Random();

        StringBuilder phoneNumber = new StringBuilder("0"); // Bat dau voi so 0
        for (int i = 0; i < 9; i++) {
            int digit=random.nextInt(10);
            phoneNumber.append(digit);
        }

        return phoneNumber.toString();
    }

    private int randomIcons(){
        Random random = new Random();

        int[] icons = {
             R.drawable.word01, R.drawable.word02, R.drawable.word03,
                R.drawable.word04, R.drawable.word05, R.drawable.word06,
                R.drawable.word07, R.drawable.word08, R.drawable.word09,
                R.drawable.word10, R.drawable.word11, R.drawable.word12,
                R.drawable.word13, R.drawable.word14, R.drawable.word15
        };

        return icons[random.nextInt(icons.length)];
    }
}