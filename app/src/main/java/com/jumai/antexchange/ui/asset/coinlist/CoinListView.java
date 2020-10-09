package com.jumai.antexchange.ui.asset.coinlist;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.HotCoinBean;

import java.util.List;

/**
 * @ClassName CoinListView
 * @Description TODO
 * @Author user
 * @Date 2019/10/8
 * @Version 1.0
 */
public interface CoinListView extends IBaseView {
    void onError(String message);
    void getCoinListSuccess(List<HotCoinBean> list);
    void getSearchListDataSuccess(List<HotCoinBean> list);
}
