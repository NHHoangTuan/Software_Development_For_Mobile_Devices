package com.hoangtuan.nhht.w10;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService4 extends Service {

    public static boolean boollsServiceCreated = false;
    MediaPlayer mediaPlayer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "MyService4 Created", Toast.LENGTH_LONG).show();
        Log.e("MyService4","onCreate");
        boollsServiceCreated = true;
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.lethergo);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"MyService4 Stopped", Toast.LENGTH_LONG).show();
        Log.e("MyService4", "onDestroy");
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if(mediaPlayer.isPlaying()){
            Toast.makeText(this,"MyService4 Already Started" + startId, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"MyService4 Started" +  startId, Toast.LENGTH_LONG).show();
        }
        Log.e("MyService4", "onStart");
        mediaPlayer.start();
    }
}
