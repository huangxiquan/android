package com.example.huangxiquan.spiderweb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangxiquan on 16/6/23.
 */

public class SpiderView extends View {

    private int mSideCount = 5;
    private int mRadius = 400;



    public SpiderView(Context context) {
        super(context);
    }

    public SpiderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpiderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        canvas.translate(screenWidth / 2 , screenHeight / 2);
        canvas.scale(1,-1);

        double angle = Math.PI * 2 / mSideCount;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        for(int i = 0 ; i < mSideCount ; i++) {
            int radius = (mRadius / mSideCount) * (i + 1);
            Path path = new Path();
            path.moveTo(0,radius);
            path.lineTo((float) (radius * Math.sin(angle)),(float) (radius * Math.cos(angle)));
            path.lineTo((float)(radius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(radius * Math.cos(Math.PI - 2 * angle)));
            path.lineTo(-1 * (float)(radius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(radius * Math.cos(Math.PI - 2 * angle)));
            path.lineTo(-1 * (float) (radius * Math.sin(angle)),(float) (radius * Math.cos(angle)));
            path.close();
            canvas.drawPath(path,paint);
        }

        Path path = new Path();
        path.lineTo(0,mRadius);
        canvas.drawPath(path,paint);
        path.setLastPoint(0,0);
        path.lineTo((float) (mRadius * Math.sin(angle)),(float) (mRadius * Math.cos(angle)));
        canvas.drawPath(path,paint);
        path.setLastPoint(0,0);
        path.lineTo((float)(mRadius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(mRadius * Math.cos(Math.PI - 2 * angle)));
        canvas.drawPath(path,paint);
        path.setLastPoint(0,0);
        path.lineTo(-1 * (float)(mRadius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(mRadius * Math.cos(Math.PI - 2 * angle)));
        canvas.drawPath(path,paint);
        path.setLastPoint(0,0);
        path.lineTo(-1 * (float) (mRadius * Math.sin(angle)),(float) (mRadius * Math.cos(angle)));
        canvas.drawPath(path,paint);

        //画五角星
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        double starRadius = (mRadius * Math.sin((Math.PI - 2 * angle) / 2)) / Math.cos(angle / 2);
        Path starPath = new Path();
        starPath.moveTo(0,-1 * (float) (starRadius));
        starPath.lineTo((float)(mRadius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(mRadius * Math.cos(Math.PI - 2 * angle)));
        canvas.drawPath(starPath,paint);
        starPath.setLastPoint(0,-1 * (float) (starRadius));
        starPath.lineTo(-1 * (float)(mRadius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(mRadius * Math.cos(Math.PI - 2 * angle)));
        canvas.drawPath(starPath,paint);

        starPath.moveTo(-1 * (float)(starRadius * Math.sin(angle)),-1 * (float)(starRadius * Math.cos(angle)));
        starPath.lineTo(-1 * (float)(mRadius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(mRadius * Math.cos(Math.PI - 2 * angle)));
        canvas.drawPath(starPath,paint);
        starPath.moveTo(-1 * (float)(starRadius * Math.sin(angle)),-1 * (float)(starRadius * Math.cos(angle)));
        starPath.lineTo(-1 * (float) (mRadius * Math.sin(angle)),(float) (mRadius * Math.cos(angle)));
        canvas.drawPath(starPath,paint);

        starPath.moveTo(-1 * (float)(starRadius * Math.sin(angle / 2)),(float)(starRadius * Math.cos(angle / 2)));
        starPath.lineTo(-1 * (float) (mRadius * Math.sin(angle)),(float) (mRadius * Math.cos(angle)));
        canvas.drawPath(starPath,paint);
        starPath.moveTo(-1 * (float)(starRadius * Math.sin(angle / 2)),(float)(starRadius * Math.cos(angle / 2)));
        starPath.lineTo(0,mRadius);
        canvas.drawPath(starPath,paint);

        starPath.moveTo((float)(starRadius * Math.sin(angle / 2)),(float)(starRadius * Math.cos(angle / 2)));
        starPath.lineTo(0,mRadius);
        canvas.drawPath(starPath,paint);
        starPath.moveTo((float)(starRadius * Math.sin(angle / 2)),(float)(starRadius * Math.cos(angle / 2)));
        starPath.lineTo((float) (mRadius * Math.sin(angle)),(float) (mRadius * Math.cos(angle)));
        canvas.drawPath(starPath,paint);

        starPath.moveTo((float)(starRadius * Math.sin(angle)),-1 * (float)(starRadius * Math.cos(angle)));
        starPath.lineTo((float) (mRadius * Math.sin(angle)),(float) (mRadius * Math.cos(angle)));
        canvas.drawPath(starPath,paint);
        starPath.moveTo((float)(starRadius * Math.sin(angle)),-1 * (float)(starRadius * Math.cos(angle)));
        starPath.lineTo((float)(mRadius * Math.sin(Math.PI - 2 * angle)),-1 * (float)(mRadius * Math.cos(Math.PI - 2 * angle)));
        canvas.drawPath(starPath,paint);



    }
}
