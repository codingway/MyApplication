package cn.uwaytech.launchmodeb;

import android.os.Bundle;

public class ActivityB extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getLocalClassName());
    }
}
