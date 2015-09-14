package com.example.yorkyan.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.yorkyan.myapplication.R;

public class SplashActivity extends BaseActivity {
    public static final String START_PREFERENCE = "start_preference";
    public static final String IS_FIRST_IN = "is_first_in";
    private static final int GO_GUIDE = 0;
    private static final int GO_MAIN = 1;
    private static final long SPLASH_DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        init();
    }

    private void init() {
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case GO_MAIN:
                        goMain();
                        break;
                    case GO_GUIDE:
                        goGuide();
                        break;
                }

                return true;
            }
        });

        SharedPreferences preferences = getSharedPreferences(START_PREFERENCE, MODE_PRIVATE);
        boolean isFirstIn = preferences.getBoolean(IS_FIRST_IN, true);
        if (!isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_MAIN, SPLASH_DELAY_MILLIS);
        } else {
            handler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }
    }

    private void goMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}