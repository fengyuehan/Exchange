package com.jumai.antexchange.ui.historyrecord.fragment;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.HistoryRecordBean;

import java.util.List;

/**
 * @ClassName HistoryRecortFragmentView
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public interface HistoryRecortFragmentView extends IBaseView {
    void getHistoryRecordData(List<HistoryRecordBean> list);
}
