package com.jumai.antexchange.ui.transcation;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.github.fujianlian.klinechart.DataHelper;
import com.github.fujianlian.klinechart.KLineChartAdapter;
import com.github.fujianlian.klinechart.KLineChartView;
import com.github.fujianlian.klinechart.KLineEntity;
import com.github.fujianlian.klinechart.draw.MainDraw;
import com.github.fujianlian.klinechart.formatter.DateFormatter;
import com.google.android.material.tabs.TabLayout;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.model.adapter.TxDepthAdapter;
import com.jumai.antexchange.view.NestedViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/26/026
 * 描述：
 */
public class ChartActivity extends BaseActivity<ChartView, ChartPresenter> implements ChartView {
    @BindView(R.id.kcv)
    KLineChartView kcv;
    @BindView(R.id.tv_symbol)
    TextView tvSymbol;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.tv_currency_price)
    TextView tvCurrencyPrice;
    @BindView(R.id.tv_currency_cny)
    TextView tvCurrencyCny;
    @BindView(R.id.tv_high_price)
    TextView tvHighPrice;
    @BindView(R.id.tv_low_price)
    TextView tvLowPrice;
    @BindView(R.id.tv_24h_price)
    TextView tv24hPrice;
    @BindView(R.id.tbl)
    TabLayout tbl;
    @BindView(R.id.tv_quota)
    TextView tvQuota;
    @BindView(R.id.tbl1)
    TabLayout tbl1;
    @BindView(R.id.vp)
    NestedViewPager vp;
    @BindView(R.id.bt_buy)
    Button btBuy;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    private List<KLineEntity> mData;
    private KLineChartAdapter mKLineChartAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initChartTime();
        initKLineChart();
        initTxAndDepth();
    }

    @Override
    protected void initData() {
        kcv.justShowLoading();
        mData = mPresenter.getALL(this).subList(0, 500);
        DataHelper.calculate(mData);
        mKLineChartAdapter.addFooterData(mData);
        mKLineChartAdapter.notifyDataSetChanged();
        kcv.startAnimation();
        kcv.refreshEnd();
    }

    private void initKLineChart() {
        mKLineChartAdapter = new KLineChartAdapter();
        DateFormatter dateFormatter = new DateFormatter();
        kcv.setAdapter(mKLineChartAdapter);
        kcv.setDateTimeFormatter(dateFormatter);
        kcv.setGridRows(4);
        kcv.setGridColumns(4);
        kcv.setChildDraw(0);
    }

    private void initChartTime() {
        String[] titles = {"分时", "5分", "15分", "30分", "1小时", "4小时", "1天", "1周", "1月"};
        for (int i = 0; i < titles.length; i++) {
            tbl.addTab(tbl.newTab().setText(titles[i]), i == 1);
        }
    }

    private void initTxAndDepth() {
        TxDepthAdapter txDepthAdapter = new TxDepthAdapter(this);
        vp.setAdapter(txDepthAdapter);
        tbl1.setupWithViewPager(vp);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initListener() {
        super.initListener();
        kcv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    nsv.requestDisallowInterceptTouchEvent(true);
                } else {
                    nsv.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        tbl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    ((MainDraw) kcv.getMainDraw()).setLine(true);
                    kcv.justShowLoading();
                    kcv.notifyChanged();
                }else {
                    ((MainDraw) kcv.getMainDraw()).setLine(false);
                    kcv.justShowLoading();
                    kcv.notifyChanged();
                }

                kcv.refreshEnd();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @OnClick({R.id.iv_back, R.id.ll_title, R.id.iv_like, R.id.bt_buy, R.id.bt_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_title:
                break;
            case R.id.iv_like:
                break;
            case R.id.bt_buy:
                break;
            case R.id.bt_confirm:
                break;
        }
    }
}
