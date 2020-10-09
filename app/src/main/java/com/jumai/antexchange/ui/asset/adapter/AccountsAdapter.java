package com.jumai.antexchange.ui.asset.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.jumai.antexchange.ui.widget.CoinAccountView;
import com.jumai.antexchange.ui.widget.LegalCoinAccountView;

import java.util.ArrayList;

/**
 * @author yf
 * @date 2019/12/18/018
 * 描述：资产->账户
 */
public class AccountsAdapter extends PagerAdapter {
    private final ArrayList<View> mItemViews;
    private final CoinAccountView mCoinAccountView;
    private final LegalCoinAccountView mLegalCoinAccountView;

    public AccountsAdapter(Context context) {
        super();
        mItemViews = new ArrayList<>();
        mCoinAccountView = new CoinAccountView(context);
        mLegalCoinAccountView = new LegalCoinAccountView(context);
        mItemViews.add(mCoinAccountView);
        mItemViews.add(mLegalCoinAccountView);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mItemViews.get(position));
        return mItemViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mItemViews.get(position));
    }

    public void setAssetShowHideListener(AssetShowHideListener assetShowHideListener) {
        mCoinAccountView.setAccountsShowHideListener(assetShowHideListener);
        mLegalCoinAccountView.setAccountsShowHideListener(assetShowHideListener);
    }


    public interface AssetShowHideListener {
        void onCoinAccountShowHide(boolean var);

        void onLegalCoinAccountShowHide(boolean var);
    }
}
