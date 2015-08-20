package com.example.yorkyan.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private List<View> viewList = new ArrayList<>();
    private MyPageAdapter myPageAdapter;
    private RadioGroup indicatorGroup;
    private View start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        start = findViewById(R.id.start);
        indicatorGroup = (RadioGroup) findViewById(R.id.indicator_group);
        myPageAdapter = new MyPageAdapter(viewList);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.addOnPageChangeListener(this);
        initViews();
    }

    private void initViews() {
        int images[] = {R.drawable.page_1, R.drawable.page_2, R.drawable.page_3};
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images[i]);
            viewList.add(imageView);

            RadioButton indicator = new RadioButton(this);
            indicator.setButtonDrawable(R.drawable.indicator);
            indicator.setId(i);
            indicatorGroup.addView(indicator);
        }

        indicatorGroup.check(0);
        myPageAdapter.notifyDataSetChanged();
    }

    public void onStartClickListener(View view) {
        SharedPreferences preferences = getSharedPreferences(SplashActivity.START_PREFERENCE, MODE_PRIVATE);
        preferences.edit().putBoolean(SplashActivity.IS_FIRST_IN, false).apply();

        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indicatorGroup.check(position);

        if (position == viewList.size() - 1) {
            start.setVisibility(View.VISIBLE);
        } else {
            start.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPageAdapter extends PagerAdapter {
        List<View> viewList;

        public MyPageAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }
}
