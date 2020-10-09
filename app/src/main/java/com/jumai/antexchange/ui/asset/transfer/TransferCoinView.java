package com.jumai.antexchange.ui.asset.transfer;

import com.jumai.antexchange.base.IBaseView;

/**
 * @ClassName TransferCoinView
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public interface TransferCoinView extends IBaseView {
    void transferSuccess();
    void transferFail(String msg);
}
