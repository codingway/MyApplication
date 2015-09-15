package cn.uwaytech.materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private int count = 0;
    private List<Integer> integers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // LinearLayout manager
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // vertical for default
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // horizontal
        // linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        // GridLayout manager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        // set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        // set item animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // set adapter
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(integers);
        recyclerView.setAdapter(recyclerViewAdapter);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        integers.add(0, count++);
                        recyclerViewAdapter.notifyItemInserted(0);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }
}
