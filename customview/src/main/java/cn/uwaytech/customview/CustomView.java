package cn.uwaytech.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private int bgColor;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            bgColor = typedArray.getColor(R.styleable.CustomView_bgColor, 0);
        } finally {
            typedArray.recycle();
        }
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(bgColor);
    }
}
