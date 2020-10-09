package com.jumai.antexchange.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jumai.antexchange.R;
import com.jumai.antexchange.base.BaseActivity;
import com.jumai.antexchange.ui.asset.AssetFragment;
import com.jumai.antexchange.ui.home.HomeFragment;
import com.jumai.antexchange.ui.market.MarketFragment;
import com.jumai.antexchange.ui.mine.MineFragment;
import com.jumai.antexchange.ui.transcation.TransactionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yf
 * @date 2019/6/29/029
 * 描述：导航栏
 */
public class NavigationBar extends LinearLayout {
    @BindView(R.id.tab_home)
    TabView tabHome;
    @BindView(R.id.tab_market)
    TabView tabMarket;
    @BindView(R.id.tab_transaction)
    TabView tabTransaction;
    @BindView(R.id.tab_asset)
    TabView tabAsset;
    @BindView(R.id.tab_mine)
    TabView tabMine;
    @BindView(R.id.v_shadow)
    View vShadow;
    private OnTabSelectedListener mOnTabSelectedListener;
    private int mLastSelectedPosition = -1;
    private FragmentManager mFragmentManager;
    private int mContainerViewId;
    private BaseActivity mActivity;

    private static final int POSITION_TAB_HOME = 0;
    private static final int POSITION_TAB_MARKET = 1;
    private static final int POSITION_TAB_TRANSACTION = 2;
    private static final int POSITION_TAB_ASSET = 3;
    private static final int POSITION_TAB_MINE = 4;

    public NavigationBar(Context context) {
        super(context);
        init(context);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mActivity = (BaseActivity) context;
        setOrientation(VERTICAL);
        View navigationBar = View.inflate(context, R.layout.l_navigation_bar, this);
        ButterKnife.bind(this, navigationBar);
        mFragmentManager = mActivity.getSupportFragmentManager();
        tabHome.init(HomeFragment.class);
        tabMarket.init(MarketFragment.class);
        tabTransaction.init(TransactionFragment.class);
        tabAsset.init(AssetFragment.class);
        tabMine.init(MineFragment.class);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        mOnTabSelectedListener = onTabSelectedListener;
    }

    @OnClick({R.id.tab_home, R.id.tab_market, R.id.tab_transaction, R.id.tab_asset, R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                onSelected(POSITION_TAB_HOME);
                break;
            case R.id.tab_market:
                onSelected(POSITION_TAB_MARKET);
                break;
            case R.id.tab_transaction:
                onSelected(POSITION_TAB_TRANSACTION);
                break;
            case R.id.tab_asset:
                onSelected(POSITION_TAB_ASSET);
                break;
            case R.id.tab_mine:
                onSelected(POSITION_TAB_MINE);
                break;
        }
    }

    private void onSelected(int position) {
        vShadow.setVisibility(position == POSITION_TAB_TRANSACTION ? View.INVISIBLE : VISIBLE);
        switchTab(position);
    }

    public void init(@IdRes int containerViewId) {
        mContainerViewId = containerViewId;
        switchTab(POSITION_TAB_HOME);
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int position);

        void onTabReSelected(int position);
    }

    private void switchTab(int position) {
        if (position == mLastSelectedPosition) {
            if (mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabReSelected(position);
            }
            return;
        }
        TabView tabView = getTabView(position);
        Fragment targetFragment = tabView.getFragment();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (targetFragment == null) {
            //targetFragment = Fragment.instantiate(getContext(), tabView.getFragmentClazz().getName(), null);
            targetFragment = mFragmentManager.getFragmentFactory().instantiate(mActivity.getClassLoader(), tabView.getFragmentClazz().getName());
            transaction.add(mContainerViewId, targetFragment, targetFragment.getTag());
            tabView.setFragment(targetFragment);
            if (mLastSelectedPosition != -1) {
                TabView preTabView = getTabView(mLastSelectedPosition);
                transaction.hide(preTabView.getFragment());
                preTabView.setSelected(false);
            }
        } else {
            TabView preTabView = getTabView(mLastSelectedPosition);
            transaction.show(targetFragment).hide(preTabView.getFragment());
            preTabView.setSelected(false);
        }
        tabView.setSelected(true);
        mLastSelectedPosition = position;
        transaction.commit();
        if (mOnTabSelectedListener != null) {
            mOnTabSelectedListener.onTabSelected(position);
        }
    }

    private TabView getTabView(int position) {
        switch (position) {
            case POSITION_TAB_MARKET:
            default:
                return tabMarket;
            case POSITION_TAB_HOME:
                return tabHome;
            case POSITION_TAB_TRANSACTION:
                return tabTransaction;
            case POSITION_TAB_ASSET:
                return tabAsset;
            case POSITION_TAB_MINE:
                return tabMine;
        }
    }
}
