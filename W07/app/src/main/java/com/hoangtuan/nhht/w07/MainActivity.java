package com.hoangtuan.nhht.w07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText editText;
    private TextView textView;
    private ProgressBar progressBar;
    private Button button;
private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText1);
        textView = (TextView) findViewById(R.id.label1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        button = (Button) findViewById(R.id.btn1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartProgressBar();
            }
        });
    }

    private void doStartProgressBar(){
        final int MAX = Integer.parseInt(editText.getText().toString());
        progressBar.setMax(MAX);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        button.setEnabled(false);
                        button.setAlpha(0.5f);
                    }
                });

                for (int i = 0; i < MAX; i++) {
                    final int progress = i + 1;
                    SystemClock.sleep(1);

                    //update interface
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            int percent = (progress * 100) / MAX;

                            textView.setText(percent + "%");
                            if (progress == MAX){
                                button.setEnabled(true);
                                button.setAlpha(1f);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

}