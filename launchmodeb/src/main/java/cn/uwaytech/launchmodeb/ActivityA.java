package cn.uwaytech.launchmodeb;

import android.os.Bundle;

public class ActivityA extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getLocalClassName());
    }
}
