package com.jumai.antexchange.ui.market.edit;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.EditCoinBean;

import java.util.List;

/**
 * @ClassName CoinEditView
 * @Description TODO
 * @Author user
 * @Date 2019/9/25
 * @Version 1.0
 */
public interface CoinEditView extends IBaseView {
    void getEditCoinData(List<EditCoinBean> mList);
}
