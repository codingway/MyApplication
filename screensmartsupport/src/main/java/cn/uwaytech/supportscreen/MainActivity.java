package cn.uwaytech.supportscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnMenuItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onMenuSelect(int position) {
        if (getResources().getBoolean(R.bool.has_two_panes)) {
            ((ContentFragment) getSupportFragmentManager().findFragmentById(R.id.content)).showContent(position);
        } else {
            ContentFragment contentFragment = new ContentFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            contentFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, contentFragment).commit();
        }
    }
}
