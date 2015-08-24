package com.example.yorkyan.myapplication.activity;

import android.os.Bundle;
import android.util.Log;

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
            } else if(NetWorkUtil.isMobileConn(this)) {
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
}
