package com.jumai.antexchange.ui.historyrecord.fragment;

import com.jumai.antexchange.base.IBaseView;
import com.jumai.antexchange.bean.AllDelegateBean;

import java.util.List;

/**
 * @ClassName AllDelegateView
 * @Description TODO
 * @Author user
 * @Date 2019/9/24
 * @Version 1.0
 */
public interface AllDelegateView extends IBaseView {
    void getAllDelegateData(List<AllDelegateBean> list);
    void getStatusDataSucces(List<AllDelegateBean> list);
    void onError(String msg);
}
