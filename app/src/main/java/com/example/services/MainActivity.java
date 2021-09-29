package com.example.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
 * A Service is an application component that can perform
 * long-running operations in the background.
 * For example,>> a service can handle network transactions,
 * play music, perform file I/O, or interact with a content provider,
 * all from the background.
 * *
 * * * A service runs in the main thread of its hosting process.
 * >Foreground
 * >Background
 * >Bound  : A bound service offers a client-server interface.
 * Also Update the Maniufest File..
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart, buttonStop, buttonNext;
    private LocalServices mBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.buttonStrat);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStrat:

                startService(new Intent(this, LocalServices.class));
                break;

            case R.id.buttonStop:

                stopService(new Intent(this, LocalServices.class));
                break;
            case R.id.buttonNext:
                Intent intent = new Intent(this, NextActivity.class);
                startActivity(intent);
                break;

        }
    }
}