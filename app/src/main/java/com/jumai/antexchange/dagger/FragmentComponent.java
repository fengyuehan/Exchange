package com.jumai.antexchange.dagger;

import com.jumai.antexchange.ui.asset.AssetFragment;
import com.jumai.antexchange.ui.historyrecord.fragment.AllDelegateFragment;
import com.jumai.antexchange.ui.historyrecord.fragment.HistoryRecordFragment;
import com.jumai.antexchange.ui.home.HomeFragment;
import com.jumai.antexchange.ui.market.MarketFragment;
import com.jumai.antexchange.ui.mine.MineFragment;
import com.jumai.antexchange.ui.transcation.TransactionFragment;

import dagger.Component;

/**
 * @author yf
 * @date 2019/9/18/018
 * 描述：
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);

    void inject(MarketFragment marketFragment);

    void inject(TransactionFragment transactionFragment);

    void inject(AssetFragment assetFragment);

    void inject(MineFragment mineFragment);

    void inject(AllDelegateFragment allDelegateFragment);

    void inject(HistoryRecordFragment historyRecordFragment);
}
