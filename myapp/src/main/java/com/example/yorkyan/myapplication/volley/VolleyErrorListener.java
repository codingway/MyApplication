package com.example.yorkyan.myapplication.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class VolleyErrorListener implements Response.ErrorListener {
    private String tag;

    public VolleyErrorListener(String tag) {
        this.tag = tag;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (error instanceof AuthFailureError) {
            Log.e(tag, "AuthFailureError ---> " + error.getMessage());
        } else if (error instanceof NetworkError) {
            Log.e(tag, "NetworkError ---> " + error.getMessage());
        } else if (error instanceof ParseError) {
            Log.e(tag, "ParseError ---> " + error.getMessage());
        } else if (error instanceof ServerError) {
            Log.e(tag, "ServerError ---> " + error.getMessage());
        } else if (error instanceof TimeoutError) {
            Log.e(tag, "TimeoutError ---> " + error.getMessage());
        } else {
            Log.e(tag, "UnKnowError ---> " + error.getMessage());
        }
    }
}
