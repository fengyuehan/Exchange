package com.jumai.antexchange.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jumai.antexchange.view.CommonUtil;


/**
 * @author yf
 * @date 2019/8/12/012
 * 描述：填充状态栏高度view
 */
public class StatusView extends View {

    public StatusView(Context context) {
        super(context);
    }

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int statusHeight = CommonUtil.getStatusHeight();
        setMeasuredDimension(width, statusHeight);
    }
}
