package com.jumai.antexchange.ui.asset.transferrecord;

import com.jumai.antexchange.base.IBaseRefreshView;
import com.jumai.antexchange.bean.TransferCoinRecordBean;

import java.util.List;

/**
 * @ClassName TransferCoinRecordView
 * @Description TODO
 * @Author user
 * @Date 2019/10/9
 * @Version 1.0
 */
public interface TransferCoinRecordView extends IBaseRefreshView {
    void getCoinRecordDataSuccess(List<TransferCoinRecordBean> list);
}
