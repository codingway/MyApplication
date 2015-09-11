package com.example.yorkyan.myapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.yorkyan.myapplication.R;
import com.example.yorkyan.myapplication.util.NetWorkUtil;
import com.example.yorkyan.myapplication.volley.VolleyErrorListener;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NetWorkUtil.isConn(this)) {
            if (NetWorkUtil.isWifiConn(this)) {
                Log.d(TAG, "is wifi connected");
            } else if (NetWorkUtil.isMobileConn(this)) {
                Log.d(TAG, "is mobile connected");
            }
        }

        getRequestQueue().add(new StringRequest("http://www.baidu.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new VolleyErrorListener(TAG)));
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void hideActionBar() {
        if (getActionBar() != null) {
            getActionBar().hide();
        } else if (getSupportActionBar() != null) {
            // for compat support theme
            getSupportActionBar().hide();
        }
    }

    /**
     * should be called onResume method
     */
    public void hideStatusBar() {
        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            hideActionBar();
        }
    }

    public void dimStatusAndNaviagationBars() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void unDimStatusAndNaviagationBars() {
        View decorView = getWindow().getDecorView();
        // Calling setSystemUiVisibility() with a value of 0 clears all flags.
        decorView.setSystemUiVisibility(0);
    }
}
