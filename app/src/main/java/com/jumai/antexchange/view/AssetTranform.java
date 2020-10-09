package com.jumai.antexchange.view;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

/**
 * @ClassName AssetTranform
 * @Description TODO
 * @Author user
 * @Date 2019/9/23
 * @Version 1.0
 */
public class AssetTranform implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.95f;
    public static final float DEFAULT_CENTER = 0.5f;
    private float mMinScale = DEFAULT_MIN_SCALE;


    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        page.setPivotY(pageHeight / 2);
        page.setPivotX(pageWidth / 2);

        if (position < -1) {
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
            page.setPivotX(pageWidth);
        } else if (position <= 1) {
            if (position < 0) {
                float scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale;
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));
            } else {
                float scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale;
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
            }
        } else { // (1,+Infinity]
            page.setPivotX(0);
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
        }
    }
}
