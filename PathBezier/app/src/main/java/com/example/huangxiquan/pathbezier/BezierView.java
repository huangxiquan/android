package com.example.huangxiquan.pathbezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangxiquan on 16/6/24.
 */

public class BezierView extends View {

    private Point mCenterPoint;
    private int mRadius = 200;
    private static final float C = 0.551915024494f;
    private float mDifference = mRadius * C;

    private float[] mDataPt = new float[8];
    private float[] mCtrlPt = new float[16];

    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化绘制点和控制点
        mDataPt[0] = 0;
        mDataPt[1] = mRadius;
        mDataPt[2] = mRadius;
        mDataPt[3] = 0;
        mDataPt[4] = 0;
        mDataPt[5] = -1 * mRadius;
        mDataPt[6] = -1 * mRadius;
        mDataPt[7] = 0;

        mCtrlPt[0] = mDataPt[0] + mDifference;
        mCtrlPt[1] = mDataPt[1];
        mCtrlPt[2] = mDataPt[2];
        mCtrlPt[3] = mDataPt[3] + mDifference;

        mCtrlPt[4] = mDataPt[2] - 20;
        mCtrlPt[5] = mDataPt[3] - mDifference;
        mCtrlPt[6] = mDataPt[4] + mDifference;
        mCtrlPt[7] = mDataPt[5] + 80;

        mCtrlPt[8] = mDataPt[4] - mDifference;
        mCtrlPt[9] = mDataPt[5] + 80;
        mCtrlPt[10] = mDataPt[6] + 20;
        mCtrlPt[11] = mDataPt[7] - mDifference;

        mCtrlPt[12] = mDataPt[6];
        mCtrlPt[13] = mDataPt[7] + mDifference;
        mCtrlPt[14] = mDataPt[0] - mDifference;
        mCtrlPt[15] = mDataPt[1];

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("hxq","w:" + w + " h:" + h);
        mCenterPoint = new Point();
        mCenterPoint.x = w / 2;
        mCenterPoint.y = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mCenterPoint.x,mCenterPoint.y);
        canvas.scale(1,-1);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        Path path = new Path();
        path.moveTo(mDataPt[0],mDataPt[1] - 120);
        path.cubicTo(mCtrlPt[0],mCtrlPt[1],mCtrlPt[2],mCtrlPt[3],mDataPt[2],mDataPt[3]);
        path.cubicTo(mCtrlPt[4],mCtrlPt[5],mCtrlPt[6],mCtrlPt[7],mDataPt[4],mDataPt[5]);
        path.cubicTo(mCtrlPt[8],mCtrlPt[9],mCtrlPt[10],mCtrlPt[11],mDataPt[6],mDataPt[7]);
        path.cubicTo(mCtrlPt[12],mCtrlPt[13],mCtrlPt[14],mCtrlPt[15],mDataPt[0],mDataPt[1] - 120);
        canvas.drawPath(path,paint);


        Paint textPaint = new Paint();
        textPaint.setTextSize(60);
        textPaint.setColor(Color.RED);
        canvas.scale(1,-1);
        canvas.drawText("哈", -140, -50, textPaint);
        canvas.drawText("哈", -140, 10,textPaint);
        canvas.drawText("哈", -140, 70,textPaint);
        canvas.drawText("哈", 80, -50, textPaint);
        canvas.drawText("哈", 80, 70,textPaint);

//        drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint)
    }
}
