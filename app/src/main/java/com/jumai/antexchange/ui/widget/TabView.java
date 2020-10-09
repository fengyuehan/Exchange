package com.jumai.antexchange.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jumai.antexchange.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yf
 * @date 2019/6/29/029
 * 描述：底部导航栏item
 */
public class TabView extends LinearLayout {
    @BindView(R.id.iv_tab_img)
    ImageView ivTabImg;
    @BindView(R.id.tv_tab_name)
    TextView tvTabName;
    private int mTabImg;
    private String mTabName;
    private Fragment mFragment;
    private Class<?> mClazz;
    private String mTag;

    public TabView(Context context) {
        super(context);
        init(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View tabView = View.inflate(context, R.layout.l_tab_view, this);
        ButterKnife.bind(this, tabView);
        getAttrs(attrs);
        setView();
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray ta = getResources().obtainAttributes(attrs, R.styleable.TabView);
        mTabImg = ta.getResourceId(R.styleable.TabView_src, -1);
        mTabName = ta.getString(R.styleable.TabView_text);
        ta.recycle();
    }

    public void init(Class<?> clazz) {
        mClazz = clazz;
        mTag = clazz.getName();

        /*if (clazz == TransactionFragment.class) {
            setFragment(new TransactionFragment());
        }*/
    }

    private void setView() {
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        if (mTabImg == -1) {
            ivTabImg.setImageResource(R.drawable.icon_placeholder);
        } else {
            ivTabImg.setImageResource(mTabImg);
        }
        tvTabName.setText(mTabName);
    }

    public void setSelected(boolean isSelected) {
        ivTabImg.setSelected(isSelected);
        tvTabName.setSelected(isSelected);
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public Class<?> getFragmentClazz() {
        return mClazz;
    }

    public String getTag() {
        return mTag;
    }
}
