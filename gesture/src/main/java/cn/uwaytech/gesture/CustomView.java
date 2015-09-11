package cn.uwaytech.gesture;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements GestureDetector.OnGestureListener {
    public static final String TAG = "CustomView";
    private GestureDetector gestureDetector;

    public CustomView(Context context) {
        super(context);

        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event)
        Log.d(TAG, "onTouchEvent");
        gestureDetector.onTouchEvent(event);

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown");

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // does not depend on onDown method
        Log.d(TAG, "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp");

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll");

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // does not depend on onDown method
        Log.d(TAG, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling");
        return false;
    }
}
