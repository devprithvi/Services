package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class LocalServices extends Service {
    MediaPlayer myPlayer;

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
        myPlayer = MediaPlayer.create(this, R.raw.music);
        myPlayer.setLooping(true);
    }


    /**
     * Both codes are only relevant when the phone
     * runs out of memory and kills the service before
     * it finishes executing. START_STICKY tells the OS
     * to recreate the service after it has enough memory
     * and call onStartCommand() again with a null intent.
     * START_NOT_STICKY tells
     * the OS to not bother recreating the service again.
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Services Started", Toast.LENGTH_SHORT).show();
        myPlayer.start();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Services Stoped", Toast.LENGTH_SHORT).show();
        myPlayer.stop();

    }
}

