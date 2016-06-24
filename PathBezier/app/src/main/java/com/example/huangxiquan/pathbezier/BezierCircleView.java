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

public class BezierCircleView extends View {

    private Point mCenterPoint;
    private int mRadius = 60;
    private static final float C = 0.551915024494f;
    private float mDifference = mRadius * C;
    private int mRightOffset = 0;
    private int mLeftOfset = 0;
    private int mRightAdd = 3;
    private int mLeftAdd = 0;
    private int mHorizonalAdd = 20;
    private int mOriginX = 200;
    private boolean isEnd = false;
    private Paint mPaint;

    private float[] mDataPt = new float[8];
    private float[] mCtrlPt = new float[16];

    public BezierCircleView(Context context) {
        this(context,null);
    }

    public BezierCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        mCtrlPt[4] = mDataPt[2];
        mCtrlPt[5] = mDataPt[3] - mDifference;
        mCtrlPt[6] = mDataPt[4] + mDifference;
        mCtrlPt[7] = mDataPt[5];

        mCtrlPt[8] = mDataPt[4] - mDifference;
        mCtrlPt[9] = mDataPt[5];
        mCtrlPt[10] = mDataPt[6];
        mCtrlPt[11] = mDataPt[7] - mDifference;

        mCtrlPt[12] = mDataPt[6];
        mCtrlPt[13] = mDataPt[7] + mDifference;
        mCtrlPt[14] = mDataPt[0] - mDifference;
        mCtrlPt[15] = mDataPt[1];

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

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
        canvas.translate(mOriginX += mHorizonalAdd,mCenterPoint.y);
        canvas.scale(1,-1);

        mRightOffset += mRightAdd;
        mLeftOfset += mLeftAdd;
        if(mLeftAdd == 0 && mRightAdd == 0 && !isEnd) {
            mLeftAdd = 3;
        }else if(mLeftOfset == 0 && mHorizonalAdd ==0) {
            mLeftAdd = 0;
            isEnd = true;
        }else if(mRightOffset > 30 && mLeftAdd == 0) {
            mLeftAdd = 3;
            mRightAdd = 0;
        }else if(mRightOffset < 0 && mLeftAdd == 0) {
            mRightAdd = 0;
            mLeftAdd = -3;
        }else if(mLeftOfset > 30 && mRightAdd == 0 && mHorizonalAdd != 0) {
            mLeftAdd = 0;
            mRightAdd = -3;
        }else if(mLeftOfset < -15) {
            mLeftAdd = 0;
            mRightAdd = 0;
            mHorizonalAdd = 0;
        }

        Path path = new Path();
        path.moveTo(mDataPt[0],mDataPt[1]);
        path.cubicTo(mCtrlPt[0],mCtrlPt[1],mCtrlPt[2] + mRightOffset,mCtrlPt[3],mDataPt[2] + mRightOffset,mDataPt[3]);
        path.cubicTo(mCtrlPt[4] + mRightOffset,mCtrlPt[5],mCtrlPt[6],mCtrlPt[7],mDataPt[4],mDataPt[5]);
        path.cubicTo(mCtrlPt[8],mCtrlPt[9],mCtrlPt[10] - mLeftOfset,mCtrlPt[11],mDataPt[6] - mLeftOfset,mDataPt[7]);
        path.cubicTo(mCtrlPt[12] - mLeftOfset,mCtrlPt[13],mCtrlPt[14],mCtrlPt[15],mDataPt[0],mDataPt[1]);
        canvas.drawPath(path,mPaint);
        postInvalidateDelayed(100);
    }

    public void reStart() {
        mRightOffset = 0;
        mLeftOfset = 0;
        mRightAdd = 3;
        mLeftAdd = 0;
        mHorizonalAdd = 20;
        mOriginX = 200;
        isEnd = false;
        invalidate();
    }
}
