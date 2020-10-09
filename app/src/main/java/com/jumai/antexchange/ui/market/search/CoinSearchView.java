package com.jumai.antexchange.ui.market.search;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.HotCoinBean;

import java.util.List;

/**
 * @ClassName CoinSearchView
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public interface CoinSearchView extends IBaseView {
    void getCollectionData(List<HotCoinBean> data);
}
