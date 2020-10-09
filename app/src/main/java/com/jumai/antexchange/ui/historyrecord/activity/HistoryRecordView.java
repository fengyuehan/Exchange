package com.jumai.antexchange.ui.historyrecord.activity;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.AllDelegateBean;
import com.jumai.antexchange.bean.HistoryRecordBean;

import java.util.List;

/**
 * @ClassName HistoryRecordView
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public interface HistoryRecordView extends IBaseView {
    void getAllDelegateDataSuccess(List<AllDelegateBean> list);
    void getHistoryRecordDataSuccess(List<HistoryRecordBean> list);
    void onError(String msg);

}
