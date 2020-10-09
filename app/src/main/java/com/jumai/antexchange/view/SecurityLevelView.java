package com.jumai.antexchange.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.jumai.antexchange.R;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：安全级别
 */
public class SecurityLevelView extends View {

    private Paint mPaint;
    private int mLevel;
    private int mSecondColor;
    private int mFirstColor;
    public static final int LEVEL_LOW = 0;
    public static final int LEVEL_MEDIUM = 1;
    public static final int LEVEL_HIGH = 2;

    public SecurityLevelView(Context context) {
        this(context, null);
    }

    public SecurityLevelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecurityLevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondColor = ContextCompat.getColor(context, R.color.color_EDEEF0);
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray ta = getResources().obtainAttributes(attrs, R.styleable.SecurityLevelView);
        mLevel = ta.getInt(R.styleable.SecurityLevelView_level, 0);
        ta.recycle();
        switch (mLevel) {
            default:
            case LEVEL_LOW:
                mFirstColor = ContextCompat.getColor(getContext(), R.color.color_F0486F);
                break;
            case LEVEL_MEDIUM:
                mFirstColor = ContextCompat.getColor(getContext(), R.color.color_FFB521);
                break;
            case LEVEL_HIGH:
                mFirstColor = ContextCompat.getColor(getContext(), R.color.color_16B887);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() - getPaddingStart() - getPaddingEnd();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        mPaint.setStrokeWidth(height);
        mPaint.setColor(mFirstColor);
        switch (mLevel) {
            default:
            case 0:
                canvas.drawLine(getPaddingStart(), getPaddingTop(), getPaddingStart() + width / 3f, 0, mPaint);
                mPaint.setColor(mSecondColor);
                canvas.drawLine(getPaddingStart() + width / 3f, getPaddingTop(), getPaddingStart() + width, 0, mPaint);
                break;
            case 1:
                canvas.drawLine(getPaddingStart(), getPaddingTop(), getPaddingStart() + width / 3f * 2, 0, mPaint);
                mPaint.setColor(mSecondColor);
                canvas.drawLine(getPaddingStart() + width / 3f * 2, getPaddingTop(), getPaddingStart() + width, 0, mPaint);
                break;
            case 2:
                canvas.drawLine(getPaddingStart(), getPaddingTop(), getPaddingStart() + width, 0, mPaint);
                break;
        }
    }

    public void setLevel(int level) {
        mLevel = level;
        invalidate();
    }
}
