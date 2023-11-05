package com.hoangtuan.nhht.w03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {


    TextView userName, passWord, birthDate, gender, hobbies;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userName=(TextView) findViewById(R.id.usernameText);
        passWord=(TextView) findViewById(R.id.passwordText);
        birthDate=(TextView) findViewById(R.id.birthdateText);
        gender=(TextView) findViewById(R.id.genderText);
        hobbies=(TextView)findViewById(R.id.hobbiesText);

        btnExit=(Button)findViewById(R.id.btnExit);


        Intent intent=getIntent();
        if(intent!=null){
            String usernameReceived=intent.getStringExtra("username");
            String passwordReceived=intent.getStringExtra("password");
            String birthdateReceived=intent.getStringExtra("birthdate");
            String genderReceived = intent.getStringExtra("gender");
            String hobbiesReceived = intent.getStringExtra("hobbies");
            String temp = passwordReceived;
            passwordReceived = temp.replaceAll(".","*");

            if(usernameReceived!=null){
                userName.setText(userName.getText()+usernameReceived);
                passWord.setText(passWord.getText()+passwordReceived);
                birthDate.setText(birthDate.getText()+birthdateReceived);
                gender.setText(gender.getText()+genderReceived);
                hobbies.setText(hobbies.getText()+hobbiesReceived);
            }
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }
}