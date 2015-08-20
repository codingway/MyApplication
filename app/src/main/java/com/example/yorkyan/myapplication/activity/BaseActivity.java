package com.example.yorkyan.myapplication.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.yorkyan.myapplication.application.BaseApplication;

public class BaseActivity extends AppCompatActivity{
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init TAG
        TAG = getLocalClassName();

        // set orientation to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public RequestQueue getRequestQueue() {
        return ((BaseApplication) getApplication()).getRequestQueue();
    }

    public ImageLoader getImageLoader () {
        return ((BaseApplication) getApplication()).getImageLoader();
    }
}