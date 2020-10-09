package com.jumai.antexchange.view;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

/**
 * @author yf
 * @date 2019/10/8/008
 * 描述：
 */
public class CardTransformer implements ViewPager.PageTransformer {
    private int mMaxTranslateOffsetX;
    private float mScaleRate;
    private ViewPager mViewPager;

    public CardTransformer(int maxOffset, float scaleRate) {
        mMaxTranslateOffsetX = maxOffset;
        mScaleRate = scaleRate;
    }


    @Override
    public void transformPage(@NotNull View page, float position) {
        if (mViewPager == null) {
            mViewPager = (ViewPager) page.getParent();
        }
        int leftInScreen = page.getLeft() - mViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * mScaleRate / mViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);

        if (scaleFactor > 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setTranslationX(-mMaxTranslateOffsetX * offsetRate);
        }
    }
}
