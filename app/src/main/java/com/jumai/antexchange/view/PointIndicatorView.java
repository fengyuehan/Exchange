package com.jumai.antexchange.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.jumai.antexchange.R;

/**
 * @author yf
 * @date 2019/2/25
 * 描述：
 */
public class PointIndicatorView extends LinearLayout {
    private ImageView[] mIndicators;
    private int mNormalResId;
    private int mSelectedResId;
    private float mIndicatorPadding;
    private int size = 0;
    private int mLastPosition = -1;

    public PointIndicatorView(Context context) {
        super(context);
    }

    public PointIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public PointIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray ta = getResources().obtainAttributes(attrs, R.styleable.PointIndicatorView);
        mSelectedResId = ta.getResourceId(R.styleable.PointIndicatorView_selectedResId, 0);
        mNormalResId = ta.getResourceId(R.styleable.PointIndicatorView_normalResId, 0);
        mIndicatorPadding = ta.getDimension(R.styleable.PointIndicatorView_indicatorPadding, 0);
        ta.recycle();
    }

    public void setIndicator(@DrawableRes int mSelectedResId, @DrawableRes int mNormalResId, int mIndicatorPadding, int size, ViewPager viewPager) {
        this.mSelectedResId = mSelectedResId;
        this.mNormalResId = mNormalResId;
        this.mIndicatorPadding = mIndicatorPadding;
        initView(3);
        bindViewPager(viewPager);
    }

    public void setIndicator(int size, ViewPager viewPager) {
        if (size <= 1) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        initView(size);
        bindViewPager(viewPager);
    }

    private void bindViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setCurrentSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void initView(int size) {
        removeAllViews();
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        this.size = size;
        mIndicators = new ImageView[size];

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < size; i++) {
            mIndicators[i] = new ImageView(getContext());
            layoutParams.setMargins(i == 0 ? 0 : (int) mIndicatorPadding, 10, 0, 0);
            mIndicators[i].setLayoutParams(layoutParams);
            mIndicators[i].setBackgroundResource(mNormalResId);
            addView(mIndicators[i]);
        }
        setCurrentSelected(0);
    }

    public void setCurrentSelected(int position) {
        if (position < 0 || position > size - 1) {
            return;
        }
        if (mLastPosition != -1) {
            mIndicators[mLastPosition].setBackgroundResource(mNormalResId);
        }

        mIndicators[position].setBackgroundResource(mSelectedResId);
        mLastPosition = position;
    }
}
