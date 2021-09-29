package com.example.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadService extends IntentService {
    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = " com.example.services";

    public DownloadService() {
        super("DownloadService");
    }

    /**
     * will be called asynchronously by Android
     * An asynchronous task is defined by a computation that runs on
     * a background thread and whose result is published on the UI thread.
     */

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String urlPath = intent.getStringExtra(URL);
        String fileName = intent.getStringExtra(FILENAME);

        File output = new File(Environment.getExternalStorageDirectory()
                , fileName);
        //checking ...

        if (output.exists()) {
            output.delete();
        }

        InputStream stream = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);

            fos = new FileOutputStream(output.getPath());
            int next = -1;

            while ((next = reader.read()) != -1) {
                fos.write(next);
            }

            result = Activity.RESULT_OK;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        publishResult(output.getAbsolutePath(), result);
    }


    private void publishResult(String outPath, int Result) {

        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);

    }
}
