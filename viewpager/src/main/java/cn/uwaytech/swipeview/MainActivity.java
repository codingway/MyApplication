package cn.uwaytech.swipeview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String[] fragments = {"A", "B", "C", "D", "E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            View view = getLayoutInflater().inflate(R.layout.layout_test, null);
            ((TextView)view.findViewById(R.id.content)).setText("View " + i);
            viewList.add(view);
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewList);
        FewFragmentsPageAdapter fewFragmentsPageAdapter = new FewFragmentsPageAdapter(getSupportFragmentManager(), fragments);
        MoreFragmentsPageAdapter moreFragmentsPageAdapter = new MoreFragmentsPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);
    }

    public class ViewPagerAdapter extends PagerAdapter {
        List<View> viewList;

        public ViewPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));

            return viewList.get(position);
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }

    @SuppressLint("ValidFragment")
    public class TestFragment extends Fragment {
        private String title;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            title = getArguments().getString("title", "");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.layout_test, container, false);
            TextView name = (TextView) rootView.findViewById(R.id.content);
            name.setText(title);
            return rootView;
        }
    }

    /**
     * This is best when navigating between sibling screens representing a fixed, small number of pages.
     */
    public class FewFragmentsPageAdapter extends FragmentPagerAdapter {
        private String[] fragments;

        public FewFragmentsPageAdapter(FragmentManager fm, String[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            TestFragment testFragment = new TestFragment();
            Bundle args = new Bundle();
            args.putString("title", fragments[position]);
            testFragment.setArguments(args);
            return testFragment;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("getPageTitle", position + "");
            return "Fragment " + fragments[position];
        }
    }

    /**
     * This is best for paging across a collection of objects for which the number of pages is undetermined.
     * It destroys fragments as the user navigates to other pages, minimizing memory usage.
     */
    public class MoreFragmentsPageAdapter extends FragmentStatePagerAdapter {
        private String[] fragments;

        public MoreFragmentsPageAdapter(FragmentManager fm, String[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            TestFragment testFragment = new TestFragment();
            Bundle args = new Bundle();
            args.putString("title", fragments[position]);
            testFragment.setArguments(args);
            return testFragment;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("getPageTitle", position + "");
            return "Fragment " + fragments[position];
        }
    }
}
