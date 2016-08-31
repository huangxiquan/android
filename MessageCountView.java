package com.dtcj.hugo.android.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangxiquan on 16/7/15.
 */

public class MessageCountView extends View {

    private Paint mPaint;

    private  int mCount = 0;

    public void setCount(int count) {
        mCount = count;
        invalidate();
    }

    public MessageCountView(Context context) {
        super(context);
        init();
    }

    public MessageCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG| Paint.FILTER_BITMAP_FLAG));
        canvas.translate(width / 2,height / 2);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        setVisibility(View.VISIBLE);
        if(mCount <= 0) {
            setVisibility(View.GONE);
        }else if(mCount < 10) {
            canvas.drawCircle(0, 0, width / 2, mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(width / 2f);
            canvas.drawText(mCount + "", -width / 8, height / 6, mPaint);
        }else if(mCount < 99) {
            RectF rectF = new RectF(-width / 2.0f,-width / 2.5f, width / 2.0f, width / 2.5f);
            canvas.drawOval(rectF,mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(width / 2f);
            canvas.drawText(mCount + "", -width / 4f, height / 6f, mPaint);
        }else {
            RectF rectF = new RectF(-width / 2.0f,-width / 2.5f, width / 2.0f, width / 2.5f);
            canvas.drawOval(rectF,mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(width / 2f);
            canvas.drawText("99", -width / 3f, height / 5f, mPaint);
            canvas.drawText("+",width / 6, 0,mPaint);
        }
    }

    private void init() {
        mPaint = new Paint();
    }
}
