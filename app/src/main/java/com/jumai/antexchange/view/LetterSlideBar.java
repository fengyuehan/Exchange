package com.jumai.antexchange.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.jumai.antexchange.R;


/**
 * 自定义字母索引
 */

public class LetterSlideBar extends View {
    private Context mContext;
    private Paint mPaint,mCirclePaint;
    private String[] mLetters = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //手指当前触摸的字母
    private String mTouchLetter;
    //手指是否触摸
    private boolean mTouch;
    // 设置触摸监听
    private SlideBarTouchListener mTouchListener;
    private Rect mRect;
    private int mNormalTextSize;
    private int mNormalTextColor;
    private int mTouchTextColor;
    private int mBgColor;
    private int mRadius;
    private int mCircleRadius;

    public LetterSlideBar(Context context) {
        this(context, null);
        init(context);
    }


    public LetterSlideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public LetterSlideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mCirclePaint = new Paint();
        mPaint.setAntiAlias(true);
        mNormalTextSize = CommonUtil.sp2px(context, 11);
        mCircleRadius = CommonUtil.sp2px(context,10);
        mRadius = CommonUtil.dp2px(context,15);
        mBgColor = getResources().getColor(R.color.tab_selected);
        mNormalTextColor = getResources().getColor(R.color.color_222F4D);
        mTouchTextColor = getResources().getColor(R.color.white);
        mPaint.setTextSize(mNormalTextSize);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(mBgColor);
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //每一个字母的高度
        float singleHeight = ((float) getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        //不断循环把绘制字母
        for (int i = 0; i < mLetters.length; i++) {
            String letter = mLetters[i];
            //获取字体的宽度
            mPaint.getTextBounds(letter, 0, letter.length(), mRect);
            float x = getPaddingLeft() + (getMeasuredWidth() / 2f - mRect.width() / 2f);
            //画字母，后面onTouch的时候需要处理高亮
            if (mLetters[i].equals(mTouchLetter) && mTouch) {
                mPaint.setTextSize(mNormalTextSize);
                mPaint.setColor(mTouchTextColor);
                float baseLine = singleHeight / 2f + (singleHeight * i) + mPaint.getTextSize() / 2f - mPaint.getFontMetrics().descent;
                canvas.drawCircle(x+10,baseLine-10,mCircleRadius,mCirclePaint);
                if ("I".equals(letter)){
                    canvas.drawText(letter, x + 5, baseLine, mPaint);
                }else if ("M".equals(letter)){
                    canvas.drawText(letter, (float) (x -3), baseLine, mPaint);
                }else {
                    canvas.drawText(letter, x, baseLine, mPaint);
                }
            } else {
                mPaint.setTextSize(mNormalTextSize);
                mPaint.setColor(mNormalTextColor);
                float baseLine = singleHeight / 2f + (singleHeight * i) + mPaint.getTextSize() / 2f - mPaint.getFontMetrics().descent;
                canvas.drawText(letter, x, baseLine, mPaint);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算出当前触摸的字母，获取当前的位置
                float currentMoveY = (int) event.getY();
                if (currentMoveY > getHeight() - getPaddingBottom() || currentMoveY < getPaddingTop()) {
                    mTouch = false;
                    if (mTouchListener != null) {
                        mTouchListener.onTouch(mTouchLetter, false);
                    }
                } else {
                    //每个字母栏的高度
                    int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
                    //当前触摸的位置边界
                    int touchPosition = (int) currentMoveY / itemHeight;
                    if (touchPosition < 0) {
                        touchPosition = 0;
                    }
                    if (touchPosition > mLetters.length - 1) {
                        touchPosition = mLetters.length - 1;
                    }
                    mTouchLetter = mLetters[touchPosition];
                    mTouch = true;
                    if (mTouchListener != null) {
                        mTouchListener.onTouch(mTouchLetter, true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTouch = false;
                if (mTouchListener != null) {
                    mTouchListener.onTouch(mTouchLetter, false);
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setOnSideBarTouchListener(SlideBarTouchListener touchListener) {
        mTouchListener = touchListener;
    }
}
