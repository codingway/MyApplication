package cn.uwaytech.launchmodea;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.application).setOnClickListener(this);
        Log.d("A", getTaskId() + "");
        ((TextView)findViewById(R.id.test)).setText(getText(this, R.string.test2, "hello"));
    }

    public static CharSequence getText(Context context, int id, Object... args) {
        for(int i = 0; i < args.length; ++i)
            args[i] = args[i] instanceof String? TextUtils.htmlEncode((String) args[i]) : args[i];
        return Html.fromHtml(String.format(Html.toHtml(new SpannedString(context.getText(id))), args));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("cn.uwaytech.launchmodeb", "cn.uwaytech.launchmodeb.MainActivity");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void onStartActivityAClickListener(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("cn.uwaytech.launchmodeb", "cn.uwaytech.launchmodeb.ActivityA");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void onStartActivityBClickListener(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("cn.uwaytech.launchmodeb", "cn.uwaytech.launchmodeb.ActivityB");
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
