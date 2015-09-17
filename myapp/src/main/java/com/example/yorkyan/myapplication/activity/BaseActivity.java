package com.example.yorkyan.myapplication.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.yorkyan.myapplication.BuildConfig;
import com.example.yorkyan.myapplication.application.BaseApplication;
import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends AppCompatActivity {
    public String TAG;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init TAG
        TAG = getLocalClassName();

        // set orientation to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // umeng resume
        if (!BuildConfig.DEBUG) {
            MobclickAgent.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // umeng pause
        if (!BuildConfig.DEBUG) {
            MobclickAgent.onPause(this);
        }
    }

    public RequestQueue getRequestQueue() {
        return ((BaseApplication) getApplication()).getRequestQueue();
    }

    public ImageLoader getImageLoader() {
        return ((BaseApplication) getApplication()).getImageLoader();
    }

    public void show(String text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public void show(String text, int duration) {
        // default Gravity value is 81
        show(text, duration, 81);
    }

    public void show(String text, int duration, int gravity) {
        if (toast == null) {
            toast = Toast.makeText(this, text, duration);
        } else {
            if (toast.getGravity() != gravity) {
                toast.cancel();
                toast = Toast.makeText(this, text, duration);
                toast.setGravity(gravity, 0, 0);
            } else {
                toast.setText(text);
                toast.setDuration(duration);
            }
        }

        toast.show();
    }
}
