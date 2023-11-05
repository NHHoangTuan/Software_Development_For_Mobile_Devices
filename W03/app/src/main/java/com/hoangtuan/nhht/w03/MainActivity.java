package com.hoangtuan.nhht.w03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnSelect, btnReset, btnSignup;
    EditText txtUsername,txtPassword,txtRetype, txtBirthdate;
    RadioButton btnMale, btnFemale;
    RadioGroup grBtnSex;
    CheckBox boxTenis,boxFutbal,boxOthers;

    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsername=(EditText) findViewById(R.id.txtUserName);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        txtRetype=(EditText) findViewById(R.id.txtRetype);
        txtBirthdate=(EditText) findViewById(R.id.txtBirthdate);

        btnReset=(Button) findViewById(R.id.btnReset);
        btnSignup=(Button)findViewById(R.id.btnSignup);
        btnSelect=(Button)findViewById(R.id.btnSelect);
        btnMale=(RadioButton)findViewById(R.id.btnMale);
        btnFemale=(RadioButton) findViewById(R.id.btnFemale);
        grBtnSex=(RadioGroup) findViewById(R.id.groupBtnSex);

        boxFutbal=(CheckBox)findViewById(R.id.boxFutbal);
        boxOthers=(CheckBox)findViewById(R.id.boxOthers);
        boxTenis=(CheckBox)findViewById(R.id.boxTenis);

        calendar=Calendar.getInstance();

        // Su kien click nut Select
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // Su kien click nut Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPassword.setText("");
                txtBirthdate.setText("");
                txtRetype.setText("");
                txtUsername.setText("");
                btnMale.setChecked(false);
                btnFemale.setChecked(false);
                boxFutbal.setChecked(false);
                boxOthers.setChecked(false);
                boxTenis.setChecked(false);
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean invalid = true;

                // Lay noi dung tu edit text password va retype
                String passwordStr=txtPassword.getText().toString();
                String retypeStr=txtRetype.getText().toString();
                // Lay noi dung tu edit text birthdate
                String birthdateStr=txtBirthdate.getText().toString();


                // kiem tra xem retype co giong password khong
                if(passwordStr.equals(retypeStr) == false){
                    invalid=false;
                    Toast.makeText(MainActivity.this,"Password không khớp, vui lòng nhập lại",
                            Toast.LENGTH_SHORT).show();
                }

                if(birthdateStr.isEmpty()||birthdateStr==null||passwordStr.isEmpty()||
                passwordStr==null){
                    invalid=false;
                }

                // Kiem tra xem co dung dinh dang dd/mm/yyyy hay khong
                if(!birthdateStr.isEmpty()) {
                    if (!isValidDate(birthdateStr)) {
                        invalid=false;
                        Toast.makeText(MainActivity.this, "Ngày tháng không hợp lệ, vui lòng nhập lại",
                                Toast.LENGTH_SHORT).show();
                    }
                }


                if (invalid){
                    String usernameStr=txtUsername.getText().toString();

                    int gender = grBtnSex.getCheckedRadioButtonId();
                    RadioButton btnGender = (RadioButton)findViewById(gender);
                    String genderStr = btnGender.getText().toString();
                    String hobbiesStr = checkHobbies();

                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);


                    intent.putExtra("username",usernameStr);
                    intent.putExtra("password",passwordStr);
                    intent.putExtra("birthdate",birthdateStr);
                    intent.putExtra("gender",genderStr);
                    intent.putExtra("hobbies",hobbiesStr);


                    startActivity(intent);
                }
            }
        });

    }

    // Kiem tra cac checkbox Hobbies
    private String checkHobbies(){

        String hobbieStr = "";

        if(boxTenis.isChecked()){
            hobbieStr=boxTenis.getText().toString();
        }

        if(boxFutbal.isChecked()){
            if(hobbieStr==null){
                hobbieStr=boxFutbal.getText().toString();
            }
            else{
                hobbieStr+=", "+boxFutbal.getText().toString();
            }
        }

        if(boxOthers.isChecked()){
            if(hobbieStr==null){
                hobbieStr=boxOthers.getText().toString();
            }
            else{
                hobbieStr+=", "+boxOthers.getText().toString();
            }
        }

        return hobbieStr;
    }


    // Kiem tra ngay hop le
    private boolean isValidDate(String dateStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Hien thi ban chon ngay thang nam
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                        String selectedDate = dateFormat.format(calendar.getTime());

                        txtBirthdate.setText(selectedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}