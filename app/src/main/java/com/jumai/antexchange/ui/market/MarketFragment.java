package com.jumai.antexchange.ui.market;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseFragment;
import com.jumai.antexchange.bean.QuotationBean;
import com.jumai.antexchange.ui.market.adapter.QuotationAdapter;
import com.jumai.antexchange.ui.market.adapter.QuotationRvAdapter;
import com.jumai.antexchange.ui.market.search.CoinSearchActivity;
import com.jumai.antexchange.ui.market.edit.CoinEditActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/9/20/020
 * 描述：
 */
public class MarketFragment extends BaseFragment<MarketView, MarketPresenter> implements MarketView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_right)
    ImageView ivRight;

    private QuotationAdapter mQuotationAdapter;
    private String[] mTitle = {"自选", "USDT", "BTC", "ETH", "HT"};
    private List<QuotationBean> mData;
    private QuotationRvAdapter mQuotationRvAdapter;
    private int mPosition;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quotation;
    }

    @Override
    protected void inject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView() {
        tvTitle.setText("行情");
        initViewPager();
        initTabLayout();
        mPosition = 1;
        vp.setCurrentItem(mPosition);
        tablayout.getTabAt(mPosition).select();
    }

    private void initTabLayout() {
        tablayout.setupWithViewPager(vp);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPosition = tab.getPosition();
                vp.setCurrentItem(mPosition);
                switch (mPosition) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initViewPager() {
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QuotationBean quotationBean = new QuotationBean();
            quotationBean.setAmount("1" + i);
            quotationBean.setCoinName("2" + i);
            quotationBean.setCoinOtherName("3" + i);
            quotationBean.setFee("4" + i);
            quotationBean.setMoney("5" + i);
            quotationBean.setPrice("6" + i);
            mData.add(quotationBean);
        }
        mQuotationAdapter = new QuotationAdapter<QuotationBean>(getActivity(), mTitle, mData, R.layout.viewpager_quotation_tailer) {
            private RecyclerView mRv;

            @Override
            public void bindView(View view, QuotationBean data, int position) {
                mRv = view.findViewById(R.id.rv);
                mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                mQuotationRvAdapter = new QuotationRvAdapter(mData);
                mRv.setAdapter(mQuotationRvAdapter);
            }
        };
        vp.setAdapter(mQuotationAdapter);
        vp.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
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

    @OnClick({R.id.iv_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(getActivity(), CoinEditActivity.class));
                break;
            case R.id.iv_right:
                startActivity(new Intent(getActivity(), CoinSearchActivity.class));
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("zzf","-----onHiddenChanged-----");
        if (hidden){
            if (mPosition != 1){
                mPosition = 1;
            }
        }else {
            mPosition = 1;
            vp.setCurrentItem(mPosition);
            tablayout.getTabAt(mPosition).select();
        }
    }
}
