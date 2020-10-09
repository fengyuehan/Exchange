package com.jumai.antexchange.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName TransferView
 * @Description TODO
 * @Author user
 * @Date 2019/9/29
 * @Version 1.0
 */
public class TransferView extends View {
    private Paint mBluePaint;
    private Paint mGrayPaint;
    private Paint mRedPaint;
    private Context mContext;
    private int mSmallRaduis = 5;
    private int mBigRaduis = 10;
    private int mHeight;
    private int mWeidth;

    public TransferView(Context context) {
        this(context,null);
        this.mContext = context;
    }

    public TransferView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        this.mContext = context;
    }

    public TransferView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }


    private void initView() {
        mBluePaint = new Paint();
        mBluePaint.setAntiAlias(true);
        mBluePaint.setStrokeWidth(8);
        mBluePaint.setColor(Color.parseColor("#00D8B3"));
        mBluePaint.setStyle(Paint.Style.STROKE);
        mGrayPaint = new Paint();
        mGrayPaint.setAntiAlias(true);
        mGrayPaint.setStrokeWidth(5);
        mGrayPaint.setColor(Color.parseColor("#DFDFE0"));
        mGrayPaint.setStyle(Paint.Style.FILL);
        mRedPaint = new Paint();
        mRedPaint.setAntiAlias(true);
        mRedPaint.setStrokeWidth(8);
        mRedPaint.setColor(Color.parseColor("#F0486F"));
        mRedPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWeidth = w;
        mHeight = h;
        Log.e("zzf","mWeidth = " + mWeidth + "mHeight = " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWeidth/2 ,mHeight/2-70,mBigRaduis,mBluePaint);
        canvas.drawCircle(mWeidth/2,mHeight/2-30,mSmallRaduis,mGrayPaint);
        canvas.drawCircle(mWeidth/2,mHeight/2,mSmallRaduis,mGrayPaint);
        canvas.drawCircle(mWeidth/2,mHeight/2+30,mSmallRaduis,mGrayPaint);
        canvas.drawCircle(mWeidth/2,mHeight/2+70,mBigRaduis,mRedPaint);
    }
}
