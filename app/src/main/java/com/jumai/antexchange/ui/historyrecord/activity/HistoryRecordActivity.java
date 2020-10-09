package com.jumai.antexchange.ui.historyrecord.activity;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.bean.AllDelegateBean;
import com.jumai.antexchange.bean.CoinUnitBean;
import com.jumai.antexchange.bean.HistoryRecordBean;
import com.jumai.antexchange.dialog.AllDelegateDialog;
import com.jumai.antexchange.dialog.HistoryRecordDialog;
import com.jumai.antexchange.ui.historyrecord.adapter.HistoryRecordViewPagerAdapter;
import com.jumai.antexchange.ui.historyrecord.fragment.AllDelegateFragment;
import com.jumai.antexchange.ui.historyrecord.fragment.HistoryRecordFragment;
import com.jumai.antexchange.view.AllDelegateCallBack;
import com.jumai.antexchange.view.HistoryRecordCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName HistoryRecordActivity
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public class HistoryRecordActivity extends BaseActivity<HistoryRecordView, HistoryRecordPresenter> implements HistoryRecordView {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;


    private String[] mTitle = {"全部委托", "历史记录"};
    private int mPosition = 0;
    private HistoryRecordViewPagerAdapter mHistoryRecordViewPagerAdapter;
    private List<Fragment> mFragmentList;
    private AllDelegateDialog mAllDelegateDialog;
    private HistoryRecordDialog mHistoryRecordDialog;
    private List<CoinUnitBean> mBeanList;
    private HistoryCallBack mHistoryCallBack;
    private DelegateCallBack mDelegateCallBack;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_record;
    }

    @Override
    protected void inject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mBeanList = new ArrayList<>();
        initFragment();
        initViewPager();
        initTabLayout();
        vp.setCurrentItem(0);
        TabLayout.Tab tab = tablayout.getTabAt(0);
        TextView mTextView = initTextView();
        mTextView.setText(tab.getText());
        tab.setCustomView(mTextView);
    }

    private TextView initTextView() {
        TextView textView = new TextView(HistoryRecordActivity.this);
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 18, getResources().getDisplayMetrics());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
        textView.setTextColor(getResources().getColor(R.color.color_222F4D));
        return textView;
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HistoryRecordFragment());
        mFragmentList.add(new AllDelegateFragment());
    }

    private void initViewPager() {
        mHistoryRecordViewPagerAdapter = new HistoryRecordViewPagerAdapter(getSupportFragmentManager(), mTitle, mFragmentList);
        vp.setAdapter(mHistoryRecordViewPagerAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLayout() {
        tablayout.setupWithViewPager(vp);
        tablayout.setSelectedTabIndicatorHeight(0);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
                mPosition = tab.getPosition();
                initTextView();
                TextView mTextView = initTextView();
                mTextView.setText(tab.getText());
                tab.setCustomView(mTextView);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.iv_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select:
                if (mPosition == 0) {
                    mAllDelegateDialog = new AllDelegateDialog(HistoryRecordActivity.this, mBeanList, new HistoryRecordCallBack() {
                        @Override
                        public void callBack(String coinType, String priceUnit, String status) {
                            //请求网络
                            mPresenter.getAllDelegateData(coinType, priceUnit, status);
                        }
                    });
                    mAllDelegateDialog.show();
                } else {
                    mHistoryRecordDialog = new HistoryRecordDialog(HistoryRecordActivity.this, mBeanList, new HistoryRecordCallBack() {
                        @Override
                        public void callBack(String coinType, String priceUnit, String status) {
                            //请求网络
                            mPresenter.getHistoryRecordData(coinType, priceUnit, status);
                        }
                    });
                    mHistoryRecordDialog.show();
                }
                break;
        }
    }

    @Override
    public void getAllDelegateDataSuccess(List<AllDelegateBean> list) {
        mDelegateCallBack.callBack(list);
    }

    @Override
    public void getHistoryRecordDataSuccess(List<HistoryRecordBean> list) {
        mHistoryCallBack.callBack(list);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public interface HistoryCallBack {
        void callBack(List<HistoryRecordBean> list);
    }

    public interface DelegateCallBack {
        void callBack(List<AllDelegateBean> list);
    }

}
