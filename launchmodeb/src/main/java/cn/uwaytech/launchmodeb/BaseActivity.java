package cn.uwaytech.launchmodeb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getLocalClassName(), getTaskId() + "");
    }

    public void onStartActivityAClickListener(View view) {
        Intent intent = new Intent(this, ActivityA.class);
        startActivity(intent);
    }

    public void onStartActivityBClickListener(View view) {
        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent);
    }
}
