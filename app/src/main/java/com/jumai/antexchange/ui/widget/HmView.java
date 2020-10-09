package com.jumai.antexchange.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.github.fujianlian.klinechart.DataHelper;
import com.github.fujianlian.klinechart.KLineChartAdapter;
import com.github.fujianlian.klinechart.KLineChartView;
import com.github.fujianlian.klinechart.KLineEntity;
import com.github.fujianlian.klinechart.draw.MainDraw;
import com.github.fujianlian.klinechart.formatter.DateFormatter;
import com.jumai.antexchange.R;
import com.jumai.antexchange.ui.transcation.ChartPresenter;
import com.jumai.antexchange.utils.ImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/25/025
 * 描述：
 */
public class HmView extends LinearLayout {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;
    @BindView(R.id.kcv)
    KLineChartView kcv;

    private boolean mCollapse = true;
    private FrameLayout.LayoutParams mLp;
    private int marginBottom;
    private ObjectAnimator mExpandAnimator;
    private int mChatHeight;
    private List<KLineEntity> mData;
    private KLineChartAdapter mKLineChartAdapter;
    private ChartPresenter mPresenter;

    public HmView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public HmView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HmView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.l_hm_view, this);
        ButterKnife.bind(this);
        mChatHeight = -ConvertUtils.dp2px(166);
        initKLineChart();
    }

    private void initKLineChart() {
        mPresenter = new ChartPresenter(null);
        mKLineChartAdapter = new KLineChartAdapter();
        DateFormatter dateFormatter = new DateFormatter();
        kcv.setAdapter(mKLineChartAdapter);
        kcv.setDateTimeFormatter(dateFormatter);
        kcv.setGridRows(4);
        kcv.setGridColumns(4);
        ((MainDraw) kcv.getMainDraw()).setLine(true);
    }

    private void initData() {
        kcv.justShowLoading();
        mData = mPresenter.getALL(getContext()).subList(0, 500);
        DataHelper.calculate(mData);
        mKLineChartAdapter.addFooterData(mData);
        mKLineChartAdapter.notifyDataSetChanged();
        kcv.startAnimation();
        kcv.refreshEnd();
    }

    @OnClick(R.id.tv_switch)
    public void onViewClicked() {
        mLp = (FrameLayout.LayoutParams) getLayoutParams();
        if (mCollapse) {
            tvSwitch.setText("收起");
        } else {
            tvSwitch.setText("展开");
        }
        int start = mCollapse ? mChatHeight : 0;
        int end = mCollapse ? 0 : mChatHeight;

        ImageUtil.setDrawableRight(getContext(), tvSwitch, mCollapse ? R.drawable.icon_triangle_down : R.drawable.icon_triangle_up);
        if (mExpandAnimator == null) {
            mExpandAnimator = ObjectAnimator.ofInt(this, "marginBottom", start, end);
            mExpandAnimator.setDuration(600);
        } else {
            mExpandAnimator.setIntValues(start, end);
        }
        mExpandAnimator.removeAllListeners();
        mExpandAnimator.addListener(mAnimatorListenerAdapter);
        mExpandAnimator.start();
    }

    private AnimatorListenerAdapter mAnimatorListenerAdapter = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            mCollapse = !mCollapse;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            if (mCollapse){
                initData();
            }
        }
    };


    public void setMarginBottom(int marginBottom) {
        mLp.setMargins(0, 0, 0, marginBottom);
        setLayoutParams(mLp);
    }
}
